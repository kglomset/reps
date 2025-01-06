package com.kwg.reps.ui.trainingprogram

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.kwg.reps.R

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
        return inflater.inflate(R.layout.fragment_training_plan_settings, container, false)
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

}