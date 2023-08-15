package com.example.busschedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.busschedule.databinding.FragmentEditStudentBinding
import com.example.busschedule.viewmodels.BusScheduleViewModel
import com.example.busschedule.viewmodels.BusScheduleViewModelFactory
import kotlinx.coroutines.launch


class AddStudentFragment : Fragment() {

    private val viewModel: BusScheduleViewModel by activityViewModels {
        BusScheduleViewModelFactory(
            (activity?.application as BusScheduleApplication).database.scheduleDao()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentEditStudentBinding.inflate(inflater, container, false)
        binding.addButton.setOnClickListener {
            val firstName = binding.firstName.editText!!.text.toString()
            val lastName = binding.lastName.editText!!.text.toString()
            val birthday = binding.birthday.editText!!.text.toString()
            if (firstName != "" && lastName != "") {
                val navController = this.findNavController()
                val grupId = arguments?.getInt("grupId", 0)!!
                lifecycle.coroutineScope.launch {
                    viewModel.addStudent(firstName, lastName, birthday, grupId)
                    navController.popBackStack()
                }
            }
        }
        return binding.root
    }


}