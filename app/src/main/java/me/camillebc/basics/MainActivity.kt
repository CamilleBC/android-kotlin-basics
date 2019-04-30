package me.camillebc.basics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import me.camillebc.basics.fragment.DogListFragment

/**
 * Step 1
 *
 * 1- We add a container (here a ConstraintLayout but it could be any kind of layout) to the activity_main.xml layout
 * 2- We create an empty fragment named [DogListFragment] and its related fragment_dog_list.xml layout
 * 3- In the [MainActivity.onCreate], we instantiate and inflate our [DogListFragment]
 *
 */
class MainActivity : AppCompatActivity() {

    /**
     * 1- We instantiate a [DogListFragment]
     * 2- We call the [FragmentManager] to begin a transaction
     * 3- We add the [DogListFragment] instance and the container's id to the transaction
     * 4- We commit the transaction to execute it
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dogListFragment = DogListFragment()                                     //1
        supportFragmentManager.beginTransaction()                                   //2
            .add(R.id.constraintLayout_main_fragmentContainer, dogListFragment)     //3
            .commit()                                                               //4
    }
}
