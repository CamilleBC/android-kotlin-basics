package me.camillebc.basics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.camillebc.basics.R

/**
 * Step 0
 *
 * We simply have a [MainActivity] class that extends [AppCompatActivity] (I'm using this for compatibility reasons on
 * older phones, use whatever you need for your project).
 *
 */
class MainActivity : AppCompatActivity() {

    /**
     * This is the starting step for our application.
     * We simply have a `MainActivity` class that extends `AppCompatActivity` (I'm using this for compatibility reasons on older phones, use whatever you need for your project).
     * The activity inflates its XML layout in the [`MainActivity.onCreate`](https://developer.android.com/guide/components/activities/activity-lifecycle#oncreate)
     * function, which will then display the views on screen.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
