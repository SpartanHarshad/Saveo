package com.harshad.movieapp.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harshad.movieapp.R
import com.harshad.movieapp.adapter.InfiniteMoviePosterAdapter
import com.harshad.movieapp.adapter.MovieShowAdapter
import com.harshad.movieapp.clicklistener.PostClickListener
import com.harshad.movieapp.data.model.ResponseItem
import com.harshad.movieapp.viewmodel.MovieApplication
import com.harshad.movieapp.viewmodel.MovieShowFactory
import com.harshad.movieapp.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), PostClickListener {
    lateinit var viewModel: MovieViewModel
    var movieShowList = mutableListOf<ResponseItem>()
    var moviePosterList = mutableListOf<ResponseItem>()
    lateinit var movieShowAdapter: MovieShowAdapter
    lateinit var infiniteMoviePosterAdapter: InfiniteMoviePosterAdapter
    var dataLoader = true
    var previousVisibleItems = 0
    var visibleItemCount = 0
    var totalVisibleItems = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        setRecyclerView()
        callApi(1)
    }

    private fun setRecyclerView() {
        var pageNo = 2
        movieShowAdapter = MovieShowAdapter(movieShowList)
        RvHorizontal.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        RvHorizontal.adapter = movieShowAdapter
        //infinite scroll view
        var gridLayoutManager = GridLayoutManager(this, 3)
        infiniteMoviePosterAdapter = InfiniteMoviePosterAdapter(moviePosterList, this)
        RvGridInfinite.layoutManager = gridLayoutManager
        RvGridInfinite.adapter = infiniteMoviePosterAdapter
        RvGridInfinite.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = gridLayoutManager.getChildCount()
                    totalVisibleItems = gridLayoutManager.getItemCount()
                    previousVisibleItems = gridLayoutManager.findFirstVisibleItemPosition()
                    if (dataLoader) {
                        if (visibleItemCount + previousVisibleItems >= totalVisibleItems) {
                            dataLoader = false
                            if (++pageNo <= 5) {
                                if (checkInternetConnectivity(applicationContext)) {
                                    loadMoviePosterList(pageNo)
                                }
                            }
                            dataLoader = true
                        }
                    }
                }
            }
        })
    }

    private fun loadMoviePosterList(pageNo: Int) {
        viewModel.fetchMovieShowList("$pageNo").observe(this@MainActivity, Observer {
            moviePosterList.clear()
            moviePosterList.addAll(it)
            infiniteMoviePosterAdapter.notifyDataSetChanged()
        })
    }

    private fun callApi(pageNo: Int) {
        if (checkInternetConnectivity(this)) {
            viewModel.fetchMovieShowList("1").observe(this, Observer {
                movieShowList.clear()
                movieShowList.addAll(it)
                movieShowAdapter.notifyDataSetChanged()
            })
            viewModel.fetchMovieShowList("$pageNo").observe(this, Observer {
                moviePosterList.clear()
                moviePosterList.addAll(it)
                infiniteMoviePosterAdapter.notifyDataSetChanged()
            })
            Toast.makeText(this, "Internet Is connected", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkInternetConnectivity(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        var isConnected = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

    private fun initViewModel() {
        val movieApplication = application as MovieApplication
        val repo = movieApplication.movieRepo
        val movieFactory = MovieShowFactory(repo)
        viewModel = ViewModelProviders.of(this, movieFactory)
            .get(MovieViewModel::class.java)
    }

    override fun onPosterClickListener(responseItem: ResponseItem) {
        val intent = Intent(this@MainActivity, MovieDetailsActivity::class.java)
        intent.putExtra("MovieName", responseItem.name)
        intent.putExtra("MoviePoster", responseItem.image.original)
        intent.putExtra("MovieTime", responseItem.schedule.time)
        intent.putExtra("MovieDate", responseItem.premiered)
        intent.putExtra("MovieSummary", responseItem.summary)
        startActivity(intent)
    }
}


