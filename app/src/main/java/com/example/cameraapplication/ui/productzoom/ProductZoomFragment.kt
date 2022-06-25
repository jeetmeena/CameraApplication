package com.example.cameraapplication.ui.productzoom

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cameraapplication.R
import com.example.cameraapplication.main.MainViewModel
import com.example.cameraapplication.viewmodelfactory.MainViewModelFactory


class ProductZoomFragment() :
    Fragment() {

    lateinit var viewModel: MainViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val viewModelProviderFactory = MainViewModelFactory(context)
        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelProviderFactory
        )[MainViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_zoom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val carouselView = ProductZoomView(view)
        if (arguments?.getString("albumName").isNullOrEmpty()) {
            viewModel.getAllImages().observe(viewLifecycleOwner, carouselView)
        } else {
            arguments?.getString("albumName")?.let {
                viewModel.getImagesByAlbumName(it)
                viewModel.albumImages.observe(viewLifecycleOwner, carouselView)
            }
        }


    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearAlbumImages()
    }


}