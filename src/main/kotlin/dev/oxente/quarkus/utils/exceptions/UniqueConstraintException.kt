package dev.oxente.quarkus.utils.exceptions

class UniqueConstraintException(val fieldName: String) : Exception()
