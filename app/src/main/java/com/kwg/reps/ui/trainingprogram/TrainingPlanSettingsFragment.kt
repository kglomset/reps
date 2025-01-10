package com.kwg.reps.ui.trainingprogram

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.kwg.reps.R
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TrainingPlanSettingsFragment : Fragment() {

    private lateinit var slider: Slider
    private lateinit var valueLabel: TextView
    companion object {
        fun newInstance() = TrainingPlanSettingsFragment()
    }

    private lateinit var viewModel: TrainingPlanSettingsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_training_plan_settings, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Find the slider and TextView
        slider = view.findViewById(R.id.discreteSlider)
        valueLabel = view.findViewById(R.id.selectedValueLabel)

        // Set up the slider
        slider.addOnChangeListener { slider, value, fromUser ->
            // Update the TextView with the current slider value
            valueLabel.text = "Workout Days: ${value.toInt()}"
        }

        // Find views
        val dateInputField: TextInputEditText = view.findViewById(R.id.startDateInputField)
        val textInputLayout: TextInputLayout = view.findViewById(R.id.startDateLayout)

        // Initialize a calendar instance
        val calendar = Calendar.getInstance()

        // Set up the DatePickerDialog with a listener to handle date selection
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                // Populate the selected date into the field
                calendar.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(calendar.time)
                Log.d("DatePicker", "Selected date: $formattedDate")
                dateInputField.setText(formattedDate) // Update the TextInputEditText
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

// Show DatePickerDialog when the end icon is clicked
        textInputLayout.setEndIconOnClickListener {
            Log.d("DatePicker", "End icon clicked")
            try {
                datePickerDialog.show()
            } catch (e: Exception) {
                Log.e("DatePicker", "Error showing dialog: ${e.message}")
            }
        }

        // Set listener for the end icon click
        textInputLayout.setEndIconOnClickListener {
            datePickerDialog.show()
        }

        // Enable manual date input
        dateInputField.isFocusableInTouchMode = true
        dateInputField.isFocusable = true

        // Optional: Add validation for manual input
        dateInputField.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val inputDate = dateInputField.text.toString()
                if (!isValidDate(inputDate)) {
                    dateInputField.error = "Invalid date format. Use yyyy-MM-dd."
                } else {
                    dateInputField.error = null
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Show the ActionBar
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            show() // Make the ActionBar visible
            setDisplayHomeAsUpEnabled(true) // Enable the back button
            setHomeButtonEnabled(true) // Ensure home button is clickable
            title = "Training Plan Settings" // Optional: Set a title
        }
    }

    override fun onPause() {
        super.onPause()
        // Hide the ActionBar when leaving this fragment
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TrainingPlanSettingsViewModel::class.java)
        // TODO: Use the ViewModel

    }

    private fun isValidDate(date:String):Boolean{
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateFormat.isLenient = false
            dateFormat.parse(date)
            true
        } catch (e: Exception) {
            false
        }
    }

}