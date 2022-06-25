package com.example.cameraapplication.ui.album

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.cameraapplication.databinding.FragmentAlbumBinding
import com.example.cameraapplication.interfaces.FragmentCallBack
import com.example.cameraapplication.main.model.PageId


class ItemRecyclerViewAdapter(
    private val values: List<String>,
    private val fragmentCallBack: FragmentCallBack
) : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item
        holder.itemView.setOnClickListener {
            val bundle =Bundle()
            bundle.putString("albumName",item)
            fragmentCallBack.executePage(PageId.IMAGE_PREVIEW.pageId,bundle)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}