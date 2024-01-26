package com.example.nasaimagery.ui.imagedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nasaimagery.databinding.FragmentImageDetailsBinding
import com.example.nasaimagery.ui.home.HomeViewModel

class ImageDetailsFragment : Fragment() {

private var _binding: FragmentImageDetailsBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    lateinit var date: String

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

        viewModel.image.observe(viewLifecycleOwner, Observer{result ->
            result.let {
                binding.titleContent.text = result.title
                binding.descriptionContent.text = result.explanation
                binding.dateContent.text = result.date
            }
        })
        viewModel.selectedDate.observe(viewLifecycleOwner, Observer { _selectedDate ->
            _selectedDate?.let {
                date = _selectedDate
            }
        })

            viewModel.getResult("DEMO_KEY", date)
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}