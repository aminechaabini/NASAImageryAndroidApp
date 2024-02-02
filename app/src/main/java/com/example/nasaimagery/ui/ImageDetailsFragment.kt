package com.example.nasaimagery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nasaimagery.databinding.FragmentImageDetailsBinding

class ImageDetailsFragment : Fragment() {

private var _binding: FragmentImageDetailsBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
    val root: View = binding.root
    return root
  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.result.observe(viewLifecycleOwner, Observer{result ->
            result.let {
                binding.titleContent.text = result.title
                binding.descriptionContent.text = result.explanation
                binding.dateContent.text = result.date
            }
        })
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}