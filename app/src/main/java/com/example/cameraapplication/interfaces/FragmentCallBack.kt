package com.example.cameraapplication.interfaces

import android.os.Bundle

interface FragmentCallBack {
    fun executeFunction(funId:String){}
    fun executePage(pageId: String,attributes:Bundle){}
}