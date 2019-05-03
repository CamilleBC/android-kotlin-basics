package me.camillebc.basics

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import me.camillebc.basics.fragment.DogEditorFragment
import me.camillebc.basics.fragment.DogListFragment

/**
 * 0.2.2 - LiveData
 *
 *
 * Right now the activity manages the Dog data on [DogEditorFragment.OnAddClickListener]
 * We would like each activity fragment to be as decoupled as we can.
 *
 * The [MainActivity] role should be only to manage the fragments: launching and popping them.
 * The [DogListFragment] should only be in charge of displaying  list of data.
 * The [DogEditorFragment] should only take the user input and
 *
 * 1- We change our dogList type to [LiveData] and [MutableLiveData]
 * 2- We remove all the data related code in the [MainActivity] and only pop the BackStack in the [MainActivity]
 * 3- We set up all the data related code in a local listener in [DogEditorFragment]
 * 4- We register an [Observer] to the [LiveData]in the [DogListFragment]. It will update the adapter on change.
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
     * This is the implementation of the [DogListFragment.OnAddClickListener].
     */
    override fun onDogListAddClick() {
        val dogEditorFragment = DogEditorFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.constraintLayout_main_fragmentContainer, dogEditorFragment)
            .addToBackStack(null)
            .commit()
    }

    /**
     * 2- We just removed all data related operation and pop the last entry in the BackStack to go back to the
     *    [DogListFragment].
     *
     * This is the implementation of the [DogEditorFragment.OnAddClickListener].
     */
    override fun onDogEditorAddClick() {
        supportFragmentManager.popBackStack()
    }
}

