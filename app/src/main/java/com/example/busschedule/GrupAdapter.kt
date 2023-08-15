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
import com.example.busschedule.databinding.BusStopItemBinding
import com.example.busschedule.databinding.GrupItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class GrupAdapter(
    private val onItemClicked: (Grup) -> Unit,
    private val onDeleteClicked: (Grup) -> Unit
) : ListAdapter<Grup, GrupAdapter.BusStopViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Grup>() {
            override fun areItemsTheSame(oldItem: Grup, newItem: Grup): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Grup, newItem: Grup): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val viewHolder = BusStopViewHolder(
            GrupItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.binding.delete.setOnClickListener {
            val position = viewHolder.adapterPosition
            onDeleteClicked(getItem(position))
        }
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BusStopViewHolder(
        public var binding: GrupItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(grup: Grup) {
            binding.grupName.text = grup.name
            binding.grupFaculty.text = grup.faculty
        }
    }
}
