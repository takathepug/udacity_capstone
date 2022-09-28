package com.example.udacity_capstone.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [LearningMaterialsDB::class, LearningActivityDB::class, QuestionDB::class],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class LearningDatabase : RoomDatabase() {
    abstract val learningMaterialsDao: LearningMaterialsDao
    abstract val learningActivityDao: LearningActivityDao
    abstract val questionDao: QuestionDao

    companion object {
        private const val DATABASE_NAME = "learning_materials"

        @Volatile
        private var INSTANCE: LearningDatabase? = null

        fun getInstance(context: Context): LearningDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance  = Room.databaseBuilder(
                        context.applicationContext,
                        LearningDatabase::class.java,
                        DATABASE_NAME
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }

        }
    }
}