package me.camillebc.basics

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import me.camillebc.basics.fragment.DogEditorFragment
import me.camillebc.basics.fragment.DogListFragment

/**
 * 0.2.1 - ViewModel
 *
 * An important concept to understand is that of lifecycle. In Android, components transition between different states
 * depending on various things: the activity could be in the foreground, background, in memory, killed by the OS, etc.
 * The data owned by a component cannot be relied upon in another component, as we don't know its state.
 * This is where ViewModel comes in.
 *
 * 1- We declare a class by extending the ViewModel class.
 * 2- We instantiate our class and tie it to a lifecycle owner.
 * 3- We instantiate the ViewModel in the components where we need it, specifying its owner: this way, if the ViewModel
 *    has already been instantiated for this owner, we get the existing instance instead of anew one.
 *
 * @property dogViewModel ViewModel instance, owned by the [MainActivity]
 */
class MainActivity :
    AppCompatActivity(),
    DogEditorFragment.OnAddClickListener,
    DogListFragment.OnAddClickListener {

    private lateinit var dogViewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 2- We instantiate a DogViewModel, and set its lifecycle owner to the activity
        dogViewModel = ViewModelProviders.of(this ).get(DogViewModel::class.java)
        // inflate the DogListFragment on application start
        val dogListFragment = DogListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.constraintLayout_main_fragmentContainer, dogListFragment)
            .commit()
    }

    /**
     * This is the implementation of the [DogEditorFragment.OnAddClickListener].
     */
    override fun onDogEditorAddClick(name: String, breed: String, subBreed: String) {
        // create a Dog item from the user input
        val dog = Dog(name, breed, subBreed)
        // if the data is valid, add the item to the list
        if ( dog.isValid() ) {
            dogViewModel.addDog(dog)
            // create a new instance of the DogListFragment with the new data
            val dogListFragment = DogListFragment()
            // replace the DogEditorFragment with DogListFragment ith the new data
            supportFragmentManager.beginTransaction()
                .replace(R.id.constraintLayout_main_fragmentContainer, dogListFragment)
                .addToBackStack(null)
                .commit()
        } else {
            toast("Invalid dog.\nName and breed cannot be empty.")
        }
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
fun AppCompatActivity.toast(message: String, long: Boolean = false) {
    val length = if (long) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    Toast.makeText(this, message, length).show()
}
