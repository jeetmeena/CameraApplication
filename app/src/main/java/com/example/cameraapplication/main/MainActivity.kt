package com.example.cameraapplication.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.cameraapplication.R
import com.example.cameraapplication.databinding.MainActivityBinding
import com.example.cameraapplication.interfaces.FragmentCallBack
import com.example.cameraapplication.main.model.PageId
import com.example.cameraapplication.ui.album.AlbumFragment
import com.example.cameraapplication.ui.camera.CameraView
import com.example.cameraapplication.ui.productzoom.ProductZoomFragment

class MainActivity : AppCompatActivity(), FragmentCallBack {
    private lateinit var viewBinding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        // Request camera permissions
        if (allPermissionsGranted()) {
            //Take Storage Permission
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

        if (savedInstanceState == null) {
            executePage(PageId.ALBUM.pageId, Bundle())
        }


    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).toTypedArray()
    }

    override fun executeFunction(funId: String) {
        super.executeFunction(funId)
        when (funId) {

        }
    }

    override fun executePage(pageId: String, argument: Bundle) {
        super.executePage(pageId, argument)
        when (pageId) {
            PageId.ALBUM.pageId -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AlbumFragment.newInstance())
                    .commitNow()
            }
            PageId.CAMERA.pageId -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CameraView.newInstance()).addToBackStack("CameraView")
                    .commit()
            }
            PageId.IMAGE_PREVIEW.pageId -> {
                //ProductZoomFragment
                val productZoomFragment = ProductZoomFragment()
                productZoomFragment.arguments = argument
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, productZoomFragment).addToBackStack("CameraView")
                    .commit()

            }
            else -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CameraView.newInstance()).addToBackStack("CameraView")
                    .commit()
            }
        }
    }
}