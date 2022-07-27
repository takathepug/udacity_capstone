package com.example.udacity_capstone.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "unit")
data class LearningUnitDB(@PrimaryKey val uid: Int, val name: String)

@Entity(tableName = "activity")
data class LearningActivityDB(@PrimaryKey val uid: Int, val name: String)
