package com.example.busschedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.busschedule.databinding.FragmentAddGrupBinding
import com.example.busschedule.databinding.GrupFragmentBinding
import com.example.busschedule.viewmodels.BusScheduleViewModel
import com.example.busschedule.viewmodels.BusScheduleViewModelFactory
import kotlinx.coroutines.launch


class AddGrupFragment : Fragment() {

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
        val binding = FragmentAddGrupBinding.inflate(inflater, container, false)
        binding.addButton.setOnClickListener {
            val name = binding.grupName.editText!!.text.toString()
            val faculty = binding.grupFaculty.editText!!.text.toString()
            if (name != "" && faculty != "") {
                val navController = this.findNavController()
                lifecycle.coroutineScope.launch {
                    viewModel.addGrup(name, faculty)
                    navController.popBackStack()
                }
            }
        }
        return binding.root
    }


}