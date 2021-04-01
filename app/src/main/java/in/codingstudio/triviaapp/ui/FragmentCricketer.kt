package `in`.codingstudio.triviaapp.ui

import `in`.codingstudio.triviaapp.R
import `in`.codingstudio.triviaapp.data.Constants
import `in`.codingstudio.triviaapp.data.PersonViewModel
import `in`.codingstudio.triviaapp.databinding.FragmentAddNameBinding
import `in`.codingstudio.triviaapp.databinding.FragmentBestCricketerBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

class FragmentCricketer : Fragment() {

    private var _binding: FragmentBestCricketerBinding?= null
    private val binding get() = _binding!!
    private val viewModel : PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBestCricketerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.setDisplayShowHomeEnabled(false)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(false)

        binding.buttonCricketer.setOnClickListener {

            val id = binding.radioGroupCricketer.checkedRadioButtonId
            val cricketer : String

            cricketer = when(id){
                R.id.radioButton_option_A -> "Sachin Tendulkar"
                R.id.radioButton_option_B -> "Virat Kolli"
                R.id.radioButton_option_C -> "Adam Gilchirst"
                R.id.radioButton_option_D -> "Jacques Kallis"
                else -> ""
            }

            if (cricketer.trim().isNotEmpty()){

                lifecycleScope.launch {
                    viewModel.savePreferences(Constants.DATA_STORE_CRICKETER, cricketer)
                }
                findNavController().navigate(R.id.action_fragmentCricketer_to_fragmentFlag)
            }else{
                Toast.makeText(activity?.applicationContext, "Select the best cricketer", Toast.LENGTH_LONG).show()
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}