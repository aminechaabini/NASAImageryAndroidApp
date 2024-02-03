package com.example.nasaimagery.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nasaimagery.databinding.FragmentSettingsBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

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


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        _binding?.calendarView?.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
        }
        binding.button.setOnClickListener {
            if (selectedDate != null) {
                if(viewModel.dateIsAfterCurrentDate(selectedDate!!)){
                    Toast.makeText(getActivity(),
                        "This date is in the future. The image does not exist!",
                        Toast.LENGTH_LONG).show();
                }
                else{
                    viewModel.setDate(selectedDate!!)
                }

            }
        }
    }


override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}