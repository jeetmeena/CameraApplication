package com.example.cameraapplication.ui.productzoom

import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cameraapplication.data.database.schemas.ImageSchema
import com.example.cameraapplication.databinding.CarouselZoomImageItemBinding
import com.example.cameraapplication.interfaces.FragmentCallBack
import com.example.cameraapplication.ui.camera.CameraView
import com.example.cameraapplication.ui.camera.ImageCon
import java.io.File
import java.io.FileNotFoundException



class CarouselZoomImageAdapter(

 ) : RecyclerView.Adapter<CarouselZoomImageAdapter.ViewHolder>() {
    private var values: List<ImageSchema>?  =  null
    fun setImages( values: List<ImageSchema>,){
        this.values = values
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            CarouselZoomImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values?.get(position)
        try {
            holder.iView.setImageURI(Uri.parse(item?.let { getImageUri(it).toString() }))
        } catch (e: FileNotFoundException) {
            Log.e("TEST", e.toString())
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int = values?.size?:0

    inner class ViewHolder(binding: CarouselZoomImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val iView: ImageView = binding.sdvFullscreenImage
    }

    private fun getImageUri(imageS: ImageSchema): File {
        val efile = Environment.getExternalStorageDirectory()
        return File(
            efile,
            "/" + ImageCon.Path_Suffix.value + imageS.albumName + "/${imageS.name}.jpg"
        )
    }
}