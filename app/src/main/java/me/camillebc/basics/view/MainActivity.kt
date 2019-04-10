package me.camillebc.basics.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.camillebc.basics.R
import me.camillebc.basics.view.fragment.DogListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dogListFragment = DogListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.constraintLayout_main_fragmentContainer, dogListFragment)
            .commit()
    }
}
