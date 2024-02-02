package com.example.nasaimagery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.nasaimagery.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding

    //val viewModel: HomeViewModel by viewModels()
    var date: String = ""

    override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding!!.root
    return root
  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                _binding!!.title.text= result.title
                Glide.with(requireContext())
                    .load(result.url)
                    .into(_binding!!.image)
            }

        })
        viewModel.selectedDate.observe(viewLifecycleOwner, Observer { _selectedDate ->
            _selectedDate?.let {
                date = _selectedDate
            }
        })

        _binding!!.button.setOnClickListener{
            if(date.isNotEmpty()){
                viewModel.getResult("DEMO_KEY", date)
            }
            else viewModel.getResultTodaysDate("DEMO_KEY")
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}