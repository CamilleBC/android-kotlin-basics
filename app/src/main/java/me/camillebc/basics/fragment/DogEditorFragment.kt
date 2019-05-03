package me.camillebc.basics.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_dog_editor.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.camillebc.basics.Dog
import me.camillebc.basics.DogViewModel
import me.camillebc.basics.R

/**
 * A simple [Fragment] subclass. See [DogListFragment] to understand the implementation
 */
class DogEditorFragment : Fragment() {
    private lateinit var dogViewModel: DogViewModel
    private var onAddClickListener: OnAddClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog_editor, container, false)
    }

    /**
     * [onActivityCreated] is called after [onCreateView]. The button's view is already inflated.
     * [onActivityCreated] is called after the parent's activity is fully functional.
     *
     * We can attach the callback safely here, as the activity is fully functional.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Attach the callback to the onClickListener
        activity?.let {
            dogViewModel = ViewModelProviders.of(this.activity!!).get(DogViewModel::class.java)
            button_dogEditor_add.setOnClickListener {
                localClickListener()
            }
        }

    }

    /**
     * [onAttach] is called when the activity is created.
     *
     * WARNING: [onAttach] holds only a *reference* to the parent's activity. It does *not* mean that the parent's
     * activity is fully functional.
     *
     * We can attach the listener safely, as we only need the reference to check that the parent's activity implements
     * the listener.
     * We CANNOT attach the listener's callback itself, as it the implementation uses [Toast] which relies on the
     * activity to be fully functional.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Attach a reference to the listener's implement in the parent's activity
        if (context is OnAddClickListener)
            onAddClickListener = context
        else
            throw RuntimeException("$context must implement OnAddClickListener")
    }

    /**
     * We detach the listener when the fragment is detached from the parent's activity
     */
    override fun onDetach() {
        super.onDetach()
        onAddClickListener = null
    }

    /**
     * 3- All our data manipulation code is here:
     *      a- We get our Dog item from the user input in a try/catch block
     *      b- We launch an asynchronous coroutine to update the data
     *      c- We call the activity callback to pop the last entry in the BackStack
     */
    private fun localClickListener() {
        // We are doing our validation in the data class constructor and catching the exception here
        try {
            val dog = getDogFromUserInput()
            // We are using a Kotlin coroutine. The goal is not really to learn how to use them. Just know that the
            // function will be run asynchronously, and because the LiveData is observed, it will be updated as soon
            // as the coroutine finishes executing.
            GlobalScope.launch {
                delay(2000)
                dogViewModel.addDog(dog)
            }
            // Call the activity callback to pop the BackStack
            onAddClickListener?.onDogEditorAddClick()
        } catch (e: IllegalArgumentException) {
            activity?.let {
                Toast.makeText(it, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * This function calls the [Dog.newInstance] function with the user input.
     * Our custom constructor will throw an exception if the data is invalid.
     */
    private fun getDogFromUserInput(): Dog = Dog.newInstance(
            editText_dogEditor_name.text.toString(),
            editText_dogEditor_breed.text.toString(),
            editText_dogEditor_subBreed.text.toString()
        )

    interface OnAddClickListener {
        fun onDogEditorAddClick()
    }
}
