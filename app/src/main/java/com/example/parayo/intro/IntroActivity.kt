package com.example.parayo.intro

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import com.example.parayo.api.ParayoApi
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.setContentView

class IntroActivity : Activity() {
    // TODO:
    val hostAddress by lazy {
        val packageName = applicationContext.packageName
        val appInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        appInfo.metaData.getString("HOST_ADDRESS")!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = IntroActivityUI()
        ui.setContentView(this)

        runBlocking {
            try {
                val response = ParayoApi.instance.hello()
                Log.d("IntroActivity", response.data!!)
            } catch (e: Exception) {
                Log.e("IntroActivity", "Hello API 호출 오류", e)
            }
        }
    }
}