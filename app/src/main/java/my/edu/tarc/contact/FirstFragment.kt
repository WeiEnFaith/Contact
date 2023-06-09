package my.edu.tarc.contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import my.edu.tarc.contact.databinding.FragmentFirstBinding
import my.tarc.mycontact.Contact
import my.tarc.mycontact.ContactAdapter
import my.tarc.mycontact.ContactViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    //Refer to the ViewModel of the hosting activity
    private val contactViewModel: ContactViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Create an instance of adapter
        val adapter = ContactAdapter()

        //Insert data observer
        contactViewModel.contactList.observe(
            viewLifecycleOwner,
            Observer{
                if(it.isEmpty()){
                    binding.textViewCount.text = getString(R.string.no_record)
                } else {
                    binding.textViewCount.isVisible = false
                    adapter.setContact(it)
                }
            }) //end of observer
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}