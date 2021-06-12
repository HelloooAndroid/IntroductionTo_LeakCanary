package com.example.leakcanarydemo

import android.content.Context
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LeakCanaryActivity : AppCompatActivity() {
    /* LeakCanary automatically detects leaks for the following objects:
        destroyed Activity instances
        destroyed Fragment instances
        destroyed fragment View instances
        cleared ViewModel instances*/

    var mContext: Context? = null
    var asyncTask: MyAsyncTask? = null
    val TAG = "TAG_LeakCanaryActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = this

        var clickMe: Button = findViewById(R.id.clickMe);
        clickMe.setOnClickListener {

            if (asyncTask != null) {
                finish()
            }
            asyncTask = MyAsyncTask(mContext as LeakCanaryActivity);
            asyncTask!!.execute();

            Log.d(TAG, "onCreate: EXECUTED")

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        /*solution here*/
        //asyncTask?.cancel(true)
        Log.d(TAG, "onDestroy:")
    }


    class MyAsyncTask(context: Context) : AsyncTask<Void?, Void?, Void?>() {
        var sContext = context


        override fun doInBackground(vararg params: Void?): Void? {
            Log.d("TAG_LeakCanaryActivity", "doInBackground: STARTED")
            var bitmap =
                BitmapFactory.decodeResource(sContext.resources, R.drawable.ic_launcher_background)
            try {
                Thread.sleep(5000)
            } catch (e: Exception) {
            }

            Log.d("TAG_LeakCanaryActivity", "doInBackground: ENDED")
            return null
        }

    }
}