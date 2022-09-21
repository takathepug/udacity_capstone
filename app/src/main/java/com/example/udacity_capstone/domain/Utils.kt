package com.example.udacity_capstone.domain

/**
 * Wraps results from operations
 */
sealed class Result<T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Failure<T>(val throwable: Throwable) : Result<T>()
}