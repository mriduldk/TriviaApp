package `in`.codingstudio.triviaapp.ui

import `in`.codingstudio.triviaapp.R
import `in`.codingstudio.triviaapp.data.Constants
import `in`.codingstudio.triviaapp.data.PersonViewModel
import `in`.codingstudio.triviaapp.databinding.FragmentAddNameBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

class FragmentName : Fragment() {

    private var _binding: FragmentAddNameBinding ?= null
    private val binding get() = _binding!!
    private val viewModel : PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonName.setOnClickListener {
            val personName = binding.editTextName.text.toString().trim()

            if (personName.isNotEmpty()){
                lifecycleScope.launch {
                    viewModel.savePreferences(Constants.DATA_STORE_PERSON_NAME, personName)
                }
                findNavController().navigate(R.id.action_fragmentName_to_fragmentCricketer)
            }else{
                Toast.makeText(activity?.applicationContext, "Enter your name please", Toast.LENGTH_LONG).show()
            }
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}