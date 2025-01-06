package com.kwg.reps.ui.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

        /*
        // Set up the button click listener using the binding
        binding.createProgram.setOnClickListener {
            openTrainingPlanSettingsFragment()
        }

         */

        binding.createProgram.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_workouts_to_trainingPlanSettingsFragment)
        }

        // Observe ViewModel for text updates
        workoutsViewModel.text.observe(viewLifecycleOwner) {
            binding.textDashboard.text = it
        }
        return binding.root
    }

    private fun openTrainingPlanSettingsFragment() {
            val navController = findNavController()
            navController.navigate(R.id.action_navigation_dashboard_to_trainingPlanSettingsFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}