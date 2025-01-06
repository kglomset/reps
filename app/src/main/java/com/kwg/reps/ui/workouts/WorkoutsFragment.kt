package com.kwg.reps.ui.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kwg.reps.R
import com.kwg.reps.databinding.FragmentWorkoutsBinding
import com.kwg.reps.ui.trainingprogram.TrainingPlanSettingsFragment

class WorkoutsFragment : Fragment() {

    private var _binding: FragmentWorkoutsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val workoutsViewModel =
            ViewModelProvider(this).get(WorkoutsViewModel::class.java)

        _binding = FragmentWorkoutsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_workouts, container, false)

        // Find the button and set its click listener
        val openTrainingPlanButton: Button = view.findViewById(R.id.createProgram)
        openTrainingPlanButton.setOnClickListener {
            openTrainingPlanSettingsFragment()
        }

        val textView: TextView = binding.textDashboard
        workoutsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    private fun openTrainingPlanSettingsFragment() {
        val fragment = TrainingPlanSettingsFragment()

        // Replace the current fragment with TrainingPlanSettingsFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(null) // Optional: Enables back navigation
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}