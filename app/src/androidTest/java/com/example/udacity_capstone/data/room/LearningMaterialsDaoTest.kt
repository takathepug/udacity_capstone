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
    fun insertLearningMaterialsAndGet() {
        val newMaterial: LearningMaterialsDB = LearningMaterialsDB(
            null,
            "ABCD1234",
            "2020-01-01",
            "The description"
        )

        val insertedId = learningMaterialsDao.insertMaterials(newMaterial)

        val allMaterials = learningMaterialsDao.getAllMaterials()

        assert(1 == allMaterials.size)

        newMaterial.materialsId = insertedId
        Assert.assertEquals(newMaterial, allMaterials[0])
    }

    @Test
    fun insertActivityAndGet() {
        // need LearningMaterials for FK
        val newMaterial: LearningMaterialsDB = LearningMaterialsDB(
            null,
            "ABCD1234",
            "2020-01-01",
            "The description"
        )
        val insertedMaterialsId = learningMaterialsDao.insertMaterials(newMaterial)

        val newActivity: LearningActivityDB = LearningActivityDB(
            null,
            insertedMaterialsId,
            "ABCD1234",
            "The name"
        )

        val insertedId = learningMaterialsDao.insertActivity(newActivity)

        val allActivities = learningMaterialsDao.getAllActivities()

        assert(1 == allActivities.size)

        newActivity.activityId = insertedId
        Assert.assertEquals(newActivity, allActivities[0])
    }

    @Test
    fun insertQuestionAndGet() {
        val newQuestion: QuestionDB = QuestionDB(
            null,
            12,
            listOf("a", "b", "c"),
            1,
            "image.png"
        )

        val insertedId = learningMaterialsDao.insertQuestion(newQuestion)

        val allQuestions = learningMaterialsDao.getAllQuestions()

        assert(1 == allQuestions.size)

        newQuestion.questionId = insertedId
        Assert.assertEquals(newQuestion, allQuestions[0])
    }
}