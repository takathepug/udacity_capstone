package com.example.udacity_capstone.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query


@Dao
interface ImageDao {
    @Query("SELECT * FROM image")
    fun getAll(): LiveData<List<ImageDB>>
}