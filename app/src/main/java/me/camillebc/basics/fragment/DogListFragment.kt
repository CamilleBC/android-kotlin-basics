package me.camillebc.basics.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_dog_list.*
import me.camillebc.basics.Dog
import me.camillebc.basics.DogViewModel
import me.camillebc.basics.R

/**
 * A simple [Fragment] subclass.
 */
class DogListFragment : Fragment() {
    private var onAddClickListener: OnAddClickListener? = null
    private lateinit var dogViewModel: DogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dog_list, container, false)
    }

    /**
     * 4- We set an [Observer] on the [LiveData]. When the underlying data is changed, and the [Observer] is registered
     *    and notified, the adapter will be changed.
     *
     * We could use a custom adapter and add a setData function to avoid recreating the whole adapter, but that is not
     * the point here.
     *
     * [onActivityCreated] is called after [onCreateView]. The button's view is already inflated.
     * [onActivityCreated] is called after the parent's activity is fully functional.
     *
     * We can attach the callback safely here, as the activity is fully functional.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            dogViewModel = ViewModelProviders.of(this.activity!!).get(DogViewModel::class.java)
            button_dogList_add.setOnClickListener{ onAddClickListener?.onDogListAddClick() }
        }
        context?.let { context ->
            val adapter = ArrayAdapter<Dog>(context, android.R.layout.simple_list_item_1, dogViewModel.dogList.value!!)
            listView_dogList.adapter = adapter
            // Comment these lines if you want to see the effect of registering the observer or not
            dogViewModel.dogList.observe(this@DogListFragment, Observer {
                adapter.notifyDataSetChanged()
            })
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
        // Attach the parent's activity listener to the fragment
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
     * Interface for the button click listener. The callback needs to be implemented in the parent's activity
     */
    interface OnAddClickListener {
        fun onDogListAddClick()
    }
}
