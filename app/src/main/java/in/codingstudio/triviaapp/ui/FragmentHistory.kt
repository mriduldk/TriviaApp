package `in`.codingstudio.triviaapp.ui

import `in`.codingstudio.triviaapp.R
import `in`.codingstudio.triviaapp.data.Constants
import `in`.codingstudio.triviaapp.data.PersonViewModel
import `in`.codingstudio.triviaapp.databinding.FragmentAddNameBinding
import `in`.codingstudio.triviaapp.databinding.FragmentBestCricketerBinding
import `in`.codingstudio.triviaapp.databinding.FragmentHistoryBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch

class FragmentHistory : Fragment() {

    private var _binding: FragmentHistoryBinding?= null
    private val binding get() = _binding!!
    private val viewModel : PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewHistory.layoutManager = LinearLayoutManager(this.context)
        val adapter = RecyclerViewAdapter(context)
        binding.recyclerViewHistory.adapter = adapter

        viewModel.allPersons.observe(viewLifecycleOwner, Observer { newList ->
            newList?.let {
                adapter.updateList(newList)
            }
        })

        binding.buttonGotoFirstPage.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentHistory_to_fragmentName)
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}