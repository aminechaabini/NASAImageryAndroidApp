package com.example.nasaimagery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nasaimagery.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    var selectedDate: String? = null
    private var _binding: FragmentSettingsBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
    //val viewModel: SettingsViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentSettingsBinding.inflate(inflater, container, false)
    val root: View = _binding!!.root
      return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        _binding?.calendarView?.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
        }
        binding.button.setOnClickListener {
            // Vérifiez si une date a été sélectionnée
            if (selectedDate != null) {
                // Appelez la méthode dans le ViewModel du HomeFragment
                viewModel.setDate(selectedDate!!)
            }
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}