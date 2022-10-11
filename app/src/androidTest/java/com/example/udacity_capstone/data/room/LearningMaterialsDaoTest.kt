package com.example.udacity_capstone.data.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


// https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md
@ExperimentalCoroutinesApi
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
    fun insertLearningMaterialsAndGet() = runBlocking {
        val newMaterials = Util.newLearningMaterials()
        val insertedId = learningMaterialsDao.insertMaterials(newMaterials)

        val allMaterials = learningMaterialsDao.getAllMaterials()

        assert(1 == allMaterials.size)

        newMaterials.materialsId = insertedId
        Assert.assertEquals(newMaterials, allMaterials[0])
    }

    @Test
    fun insertActivityAndGet() = runBlocking {
        // need LearningMaterials for FK
        val newMaterials = Util.newLearningMaterials()
        val insertedMaterialsId = learningMaterialsDao.insertMaterials(newMaterials)

        val newActivity = Util.newLearningActivity(materialsId = insertedMaterialsId)
        val insertedActivityId = learningMaterialsDao.insertActivity(newActivity)

        val allActivities = learningMaterialsDao.getAllActivities()

        assert(1 == allActivities.size)

        newActivity.activityId = insertedActivityId
        Assert.assertEquals(newActivity, allActivities[0])
    }

    @Test
    fun insertQuestionAndGet() = runBlocking {
        // need LearningMaterials for FK
        val newMaterials = Util.newLearningMaterials()
        val insertedMaterialsId = learningMaterialsDao.insertMaterials(newMaterials)

        // need LearningActivity for FK
        val newActivity = Util.newLearningActivity(materialsId = insertedMaterialsId)
        val insertedActivityId = learningMaterialsDao.insertActivity(newActivity)

        val newQuestion = Util.newQuestion(activityId = insertedActivityId)
        val insertedQuestionId = learningMaterialsDao.insertQuestion(newQuestion)

        val allQuestions = learningMaterialsDao.getAllQuestions()

        assert(1 == allQuestions.size)

        newQuestion.questionId = insertedQuestionId
        Assert.assertEquals(newQuestion, allQuestions[0])
    }

    @Test
    fun insertLearningMaterialsWithActivitiesDBAndGet() = runBlocking {
        val detLearningMaterials = learningMaterialsDao.insertDetailedLearningMaterials(
            Util.newDetailedLearningMaterials()
        )

        val l = learningMaterialsDao.getMaterialsWithActivities(
            detLearningMaterials.learningMaterialsDB.materialsId!!)

        // TODO
        assert(l.size == 1)
    }
}