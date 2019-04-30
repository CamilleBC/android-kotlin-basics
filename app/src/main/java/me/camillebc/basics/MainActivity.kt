package me.camillebc.basics

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.camillebc.basics.fragment.DogListFragment

/**
 * Step 2
 *
 * 1- To communicate between the fragment and the activity, we need to add:
 *  a- A listener interface in the fragment
 *  b- The implementation of the interface: a callback in the activity
 *
 * 2- We need to attach the actual callback to the button's onClickListener:
 *  a- Attach the reference of the activity that implements the listener to the fragment
 *  b- Add the activity's callback to the button through [View.setOnClickListener]
 *
 */
class MainActivity :
    AppCompatActivity(),
    // The parent activity must extend the fragment's listener
    DogListFragment.OnAddClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dogListFragment = DogListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.constraintLayout_main_fragmentContainer, dogListFragment)
            .commit()
    }

    /**
     * 1.a - Callback
     *
     * This is the implementation of the [DogListFragment.OnAddClickListener].
     */
    override fun onDogListAddClick() {
        toast("Button clicked")
    }

}

/**
 * Small extension function for activities. It simply Toast a message
 */
fun AppCompatActivity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
