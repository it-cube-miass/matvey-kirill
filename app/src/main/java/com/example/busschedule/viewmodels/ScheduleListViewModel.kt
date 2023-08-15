package com.example.busschedule.viewmodels
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

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.busschedule.database.schedule.Grup
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao
import com.example.busschedule.database.schedule.Student
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BusScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel() {

    fun grups(): Flow<List<Grup>> = scheduleDao.getGrups()

    fun students(grupId: Int): Flow<List<Student>> = scheduleDao.getStudents(grupId)

    fun fullSchedule(): Flow<List<Schedule>> = scheduleDao.getAll()

    fun scheduleForStopName(name: String): Flow<List<Schedule>> = scheduleDao.getByStopName(name)

    suspend fun addGrup(name: String, faculty: String) {
        scheduleDao.insertGrup(name, faculty)
    }

    suspend fun removeGrup(id: Int) {
        scheduleDao.deleteGrup(id)
    }

    suspend fun addStudent(firstName: String, lastName: String, birthday: String, grupId: Int) {
        scheduleDao.insertStudent(firstName, lastName, grupId, birthday)
    }

    suspend fun getStudentCount(grupId: Int): Int {
        return scheduleDao.getCount(grupId)
    }

    suspend fun removeStudent(id: Int) {
        scheduleDao.deleteStudent(id)
    }
}

class BusScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
