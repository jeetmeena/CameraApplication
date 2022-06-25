package com.example.cameraapplication.camera

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.example.cameraapplication.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CameraViewTest {
    @get:Rule
    val permissionRule = GrantPermissionRule.grant(
        Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Mock
    lateinit var cameraProviderFuture: ProcessCameraProvider
    lateinit var processCameraProvider: ProcessCameraProvider
    private lateinit var mockContext: Context

    private lateinit var testActivity: ActivityScenario<MainActivity>
    @Before
    fun setUp() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(""),
            InstrumentationRegistry.getInstrumentation().targetContext,
            MainActivity::class.java
        )
        testActivity = ActivityScenario.launch(intent)
        Thread.sleep(3000)
    }
    @Test
    fun arePageElementsDisplayed() {

     }


}