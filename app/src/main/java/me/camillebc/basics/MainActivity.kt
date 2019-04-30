package me.camillebc.basics

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.camillebc.basics.fragment.DogEditorFragment
import me.camillebc.basics.fragment.DogListFragment

/**
 * Step 3
 *
 * 1- We just redo everything we did in the last step:
 *  a- Add a [DogEditorFragment] with its fragment_dog_editor.xml layout
 *  b- Add a listener interface in the fragment, and the callback's implementation in the parent activity
 *  c- Attach the listener's reference and the callback to the button's [View.onClickListener]
 *
 * 2- This time we also add a new transaction to the [FragmentManager].
 *  a- When we click the [DogEditorFragment]'s button, we replace, instead of adding, the [DogListFragment] in the
 *     activity_main.xml container.
 *  b- We add this transaction to the BackStack.
 *
 * 3- When we click on the [DogEditorFragment]'s button, we pop the last transaction, and go back to the [DogListFragment].
 *
 */
class MainActivity :
    AppCompatActivity(),
    // The parent activity must extend the fragment's listeners
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
     *
     * 3- We pop the last entry on the BackStack and go back to the [DogListFragment].
     */
    override fun onDogEditorAddClick() {
        toast("Not implemented")
        supportFragmentManager.popBackStack()
    }

    /**
     * This is the implementation of the [DogListFragment.OnAddClickListener].
     *
     * 2.a- We set a new transaction to the [FragmentManager] and replace the fragment in
     * 2.b- We add the transaction to the BackStack.
     */
    override fun onDogListAddClick() {
        val dogEditorFragment = DogEditorFragment()
        supportFragmentManager.beginTransaction()
            //2.a
            .replace(R.id.constraintLayout_main_fragmentContainer, dogEditorFragment)
            //2.b
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
