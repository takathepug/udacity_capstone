package com.example.udacity_capstone.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query


@Dao
public abstract class QuestionDao {
    @Query("SELECT * FROM question")
    abstract fun getAll(): LiveData<List<QuestionDB>>
}