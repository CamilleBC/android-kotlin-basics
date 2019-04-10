package me.camillebc.basics.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import me.camillebc.basics.R
import me.camillebc.basics.view.fragment.DogEditorFragment
import me.camillebc.basics.view.fragment.DogListFragment

/**
 * The parent activity must extend the fragment's listeners.
 */
class MainActivity :
    AppCompatActivity(),
    DogEditorFragment.OnAddClickListener,
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
     * This is the implementation of the [DogEditorFragment.OnAddClickListener].
     */
    override fun onDogEditorAddClick() {
        toast("Not implemented")
        supportFragmentManager.popBackStack()
    }

    /**
     * This is the implementation of the [DogListFragment.OnAddClickListener].
     */
    override fun onDogListAddClick() {
        val dogEditorFragment = DogEditorFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.constraintLayout_main_fragmentContainer, dogEditorFragment)
            .addToBackStack(null)
            .commit()
    }

}

/**
 * Small extension function for activities. It simply Toast a message
 */
fun AppCompatActivity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
