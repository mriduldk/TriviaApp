package `in`.codingstudio.triviaapp.ui

import `in`.codingstudio.triviaapp.R
import `in`.codingstudio.triviaapp.data.Constants
import `in`.codingstudio.triviaapp.data.Person
import `in`.codingstudio.triviaapp.data.PersonViewModel
import `in`.codingstudio.triviaapp.databinding.FragmentFlagBinding
import `in`.codingstudio.triviaapp.databinding.FragmentSummeryBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

class FragmentSummery : Fragment() {

    private var _binding: FragmentSummeryBinding?= null
    private val binding get() = _binding!!
    private val viewModel : PersonViewModel by activityViewModels()
    private val TAG = "FragmentSummery"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSummeryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.personId.observe(viewLifecycleOwner, Observer {personId ->

            lifecycleScope.launch {

                Log.e(TAG,"personId $personId")

                if (personId != 0.toLong()) {
                    val person: Person = viewModel.getPerson(personId)

                    binding.textViewAnsName.text = "Hello, ${person.name}"
                    binding.textViewAnsCricketer.text = "Answer :  ${person.cricketer}"

                    //TODO
                    binding.textViewAnsFlag.text = "Answer : ${person.flag}"
                }
            }
        })


        binding.buttonFlag.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSummery_to_fragmentName)
        }

        binding.buttonHistory.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSummery_to_fragmentHistory)
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}