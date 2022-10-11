package com.example.udacity_capstone.data.room

import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import kotlin.random.Random
import java.util.*
import java.util.concurrent.ThreadLocalRandom


class Util {
    companion object {
        private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE
        
        fun newLearningMaterials(materialsId: Long? = null): LearningMaterialsDB {
            return LearningMaterialsDB(
                materialsId,
                UUID.randomUUID().toString(),
                randomDate(),
                "The description ${Random.nextInt(0, 100)}"
            )
        }

        fun newLearningActivity(activityId: Long? = null,
                                materialsId: Long? = null): LearningActivityDB {
            return LearningActivityDB(
                activityId,
                materialsId,
                UUID.randomUUID().toString(),
                "The name ${Random.nextInt(0, 100)}"
            )
        }

        private fun newLearningActivities(materialsId: Long? = null): List<LearningActivityDB> {
            var activities = List<LearningActivityDB?>(Random.nextInt(1, 5)){ null }

            // generate random number of random activities
            activities = activities.map { newLearningActivity(materialsId = materialsId) }

            return activities
        }

        fun newQuestion(questionId: Long? = null,
                        activityId: Long? = null): QuestionDB {
            return QuestionDB(
                questionId,
                activityId,
                listOf("a", "b", "c"),
                1,
                "image${Random.nextInt(0, 100)}.png"
            )
        }

        private fun newQuestions(activityId: Long? = null): List<QuestionDB> {
            var questions = List<QuestionDB?>(Random.nextInt(1, 5)){ null }

            questions = questions.map { newQuestion(activityId = activityId) }

            return questions
        }

        fun newDetailedLearningMaterials(): DetailedLearningMaterialsDB {
            // generate random number of random activities
            val activities: List<LearningActivityDB> = newLearningActivities()
            val activitiesWithQuestions = activities.map { a ->
                DetailedLearningActivityDB(a, newQuestions())
            }

            // generate random materials
            return DetailedLearningMaterialsDB(
                newLearningMaterials(),
                activitiesWithQuestions
            )
        }

        // https://www.baeldung.com/java-random-dates
        private fun randomDate(
            startInclusive: LocalDate = LocalDate.of(2020, Month.JANUARY, 1),
            endExclusive: LocalDate = LocalDate.now()): String {

            val startEpochDay: Long = startInclusive.toEpochDay()
            val endEpochDay: Long = endExclusive.toEpochDay()
            val randomDay: Long = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay)

            return LocalDate.ofEpochDay(randomDay).format(DATE_FORMATTER)
        }
    }

}