/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.busschedule

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.busschedule.database.schedule.Grup
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.Student
import com.example.busschedule.databinding.BusStopItemBinding
import com.example.busschedule.databinding.GrupItemBinding
import com.example.busschedule.databinding.StudentItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class StudentAdapter(
    private val onDeleteClicked: (Student) -> Unit,
) : ListAdapter<Student, StudentAdapter.BusStopViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val viewHolder = BusStopViewHolder(
            StudentItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.binding.delete.setOnClickListener {
            val position = viewHolder.adapterPosition
            onDeleteClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BusStopViewHolder(
        var binding: StudentItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Student) {
            binding.firstName.text = data.firstName
            binding.lastName.text = data.lastName
            binding.birthday.text = data.birthday
        }
    }
}
