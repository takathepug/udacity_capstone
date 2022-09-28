package com.example.udacity_capstone.data.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LearningMaterialsDaoTest {
    private lateinit var learningMaterialsDao: LearningMaterialsDao
    private lateinit var db: LearningDatabase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, LearningDatabase::class.java
        ).build()

        learningMaterialsDao = db.learningMaterialsDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGet() {
        val materials: LearningMaterialsDB = LearningMaterialsDB(
            null,
            "ABCD1234",
            "2020-01-01",
            "The description"
        )

        val insertedId = learningMaterialsDao.insert(materials)

        val allMaterials = learningMaterialsDao.getAll()

        assert(1 == allMaterials.size)

        materials.materialsId = insertedId
        Assert.assertEquals(materials, allMaterials[0])


    }

}