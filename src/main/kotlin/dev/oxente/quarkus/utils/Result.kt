package dev.oxente.quarkus.utils

import java.util.stream.Collectors

import javax.validation.ConstraintViolation

class Result {
    val message: String
    val isSuccess: Boolean

    constructor(message: String) {
        this.isSuccess = true
        this.message = message
    }

    constructor(violations: Set<ConstraintViolation<*>>) {
        this.isSuccess = false
        this.message = violations.stream()
                .map { cv -> cv.message }
                .collect(Collectors.joining(", "))
    }
}