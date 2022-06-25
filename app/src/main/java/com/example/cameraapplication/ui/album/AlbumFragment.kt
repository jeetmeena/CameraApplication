package com.example.cameraapplication.ui.album

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cameraapplication.main.MainViewModel
import com.example.cameraapplication.R
import com.example.cameraapplication.databinding.FragmentAlbumListBinding
import com.example.cameraapplication.interfaces.FragmentCallBack
import com.example.cameraapplication.main.model.PageId
import com.example.cameraapplication.viewmodelfactory.MainViewModelFactory
import java.lang.Exception


class AlbumFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var viewBinding:FragmentAlbumListBinding
    lateinit var fragmentCallBack: FragmentCallBack
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            val viewModelProviderFactory = MainViewModelFactory(context)
            viewModel = ViewModelProvider(requireActivity(),viewModelProviderFactory)[MainViewModel::class.java]
            fragmentCallBack = context as FragmentCallBack

        }catch (e:Exception){}
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_album_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set the adapter
        viewBinding = FragmentAlbumListBinding.bind(view)
        viewBinding.floatingActionButton.setOnClickListener {
            fragmentCallBack.executePage(PageId.CAMERA.pageId, Bundle())
        }
        viewModel.albumNames.observe(viewLifecycleOwner, {
            it?.let {
                viewBinding.list.adapter = ItemRecyclerViewAdapter(it,fragmentCallBack)
            }
        })

    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance() =
            AlbumFragment()

    }
}