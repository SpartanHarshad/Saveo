package com.harshad.movieapp.view

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.harshad.movieapp.R
import com.harshad.movieapp.adapter.MovieShowAdapter
import com.harshad.movieapp.data.model.ResponseItem
import com.harshad.movieapp.viewmodel.MovieApplication
import com.harshad.movieapp.viewmodel.MovieShowFactory
import com.harshad.movieapp.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MovieViewModel
    var movieShowList = mutableListOf<ResponseItem>()
    var isConnected: Boolean = false;
    lateinit var movieShowAdapter: MovieShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        setRecyclerView()
        callApi()
    }

    private fun setRecyclerView() {
        movieShowAdapter = MovieShowAdapter(movieShowList)
        RvHorizontal.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        RvHorizontal.adapter = movieShowAdapter
    }

    private fun callApi() {
        if (checkInternetConnectivity(this)) {
            viewModel.fetchMovieShowList("1").observe(this, Observer {
                movieShowList.clear()
                movieShowList.addAll(it)
                Log.d("Res","$it")
                movieShowAdapter.notifyDataSetChanged()
            })
            Toast.makeText(this, "Internet Is connected", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkInternetConnectivity(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        isConnected = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

    private fun initViewModel() {
        val movieApplication = application as MovieApplication
        val repo = movieApplication.movieRepo
        val movieFactory = MovieShowFactory(repo)
        viewModel = ViewModelProviders.of(this, movieFactory)
            .get(MovieViewModel::class.java)
    }
}