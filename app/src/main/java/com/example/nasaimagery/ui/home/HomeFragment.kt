package com.example.nasaimagery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.nasaimagery.R
import com.example.nasaimagery.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

    val viewModel: HomeViewModel by viewModels()
    lateinit var date: String

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root
    return root
  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.image.observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                _binding?.title?.text= result.title
                Glide.with(requireContext())
                    .load(result.url)
                    .into(binding.image)
            }

        })
        viewModel.selectedDate.observe(viewLifecycleOwner, Observer { _selectedDate ->
            _selectedDate?.let {
                date = _selectedDate
            }
        })
        binding.button.setOnClickListener{
                viewModel.getResult("DEMO_KEY", date)

        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}