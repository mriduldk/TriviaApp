package `in`.codingstudio.triviaapp.ui

import `in`.codingstudio.triviaapp.R
import `in`.codingstudio.triviaapp.data.Constants
import `in`.codingstudio.triviaapp.data.Person
import `in`.codingstudio.triviaapp.data.PersonViewModel
import `in`.codingstudio.triviaapp.databinding.FragmentBestCricketerBinding
import `in`.codingstudio.triviaapp.databinding.FragmentFlagBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class FragmentFlag : Fragment() {

    private var _binding: FragmentFlagBinding?= null
    private val binding get() = _binding!!
    private val viewModel : PersonViewModel by activityViewModels()
    private var listFlag : StringBuilder = StringBuilder()
    private val TAG = "FragmentFlag"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFlagBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFlag.setOnClickListener {

            val white = binding.checkboxOptionA.isChecked
            val yellow = binding.checkboxOptionB.isChecked
            val orange = binding.checkboxOptionC.isChecked
            val green = binding.checkboxOptionD.isChecked

            if (white){ listFlag.append( "White,")}
            if (yellow){ listFlag.append( "Yellow,") }
            if (orange){ listFlag.append( "Orange,") }
            if (green){ listFlag.append( "Green") }

            var id : Long = -1

            if(!white && !yellow && !orange && !green){
                Toast.makeText(activity?.applicationContext, "Select at least one color", Toast.LENGTH_LONG).show()
            }else{

                lifecycleScope.launch {
                    val cricketer = viewModel.readPreferences(Constants.DATA_STORE_CRICKETER)?: "No data Found"
                    val personName = viewModel.readPreferences(Constants.DATA_STORE_PERSON_NAME)?: "No data Found"

                    id = viewModel.insert(Person(0, personName, cricketer, listFlag.toString() ))

                    viewModel.saveId(id)
                    findNavController().navigate(R.id.action_fragmentFlag_to_fragmentSummery)
                }

            }
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}