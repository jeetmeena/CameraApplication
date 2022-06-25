package com.example.cameraapplication.ui.productzoom

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.cameraapplication.data.database.schemas.ImageSchema
import com.example.cameraapplication.databinding.FragmentProductZoomBinding


class ProductZoomView(
    val view: View,
) : Observer<List<ImageSchema>> {
    private val viewBinding = FragmentProductZoomBinding.bind(view)
    private val carouselZoomImageAdapter = CarouselZoomImageAdapter()

    init {
        viewBinding.rvPdpZoom.adapter = carouselZoomImageAdapter
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(viewBinding.rvPdpZoom)
       viewBinding.rvPdpZoom. addOnScrollListener(
            SnapOnScrollListener(
                snapHelper
            )
        )

    }


    private fun bindData(
        imageItems: List<ImageSchema>
    ) {
        carouselZoomImageAdapter.setImages(imageItems)
        carouselZoomImageAdapter.notifyDataSetChanged()
    }

    override fun onChanged(t: List<ImageSchema>?) {
        t?.let { bindData(it) }
    }


}