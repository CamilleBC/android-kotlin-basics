package me.camillebc.basics.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_dog_editor.*
import me.camillebc.basics.R

/**
 * A simple [Fragment] subclass. See [DogListFragment] to understand the implementation
 *
 */
class DogEditorFragment : Fragment() {
    private var onAddClickListener: OnAddClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog_editor, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Attach the callback to the onClickListener
        activity?.let {
            button_dogEditor_add.setOnClickListener { onAddClickListener?.onDogEditorAddClick() }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Attach a reference to the listener's implement in the parent's activity
        if (context is OnAddClickListener)
            onAddClickListener = context
        else
            throw RuntimeException("$context must implement OnAddClickListener")
    }

    override fun onDetach() {
        super.onDetach()
        onAddClickListener = null
    }

    interface OnAddClickListener {
        fun onDogEditorAddClick()
    }

}
