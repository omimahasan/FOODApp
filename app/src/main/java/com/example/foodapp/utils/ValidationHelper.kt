package com.example.foodapp.utils

object ValidationHelper {

    fun validateName(name: String): String? {
        return when {
            name.isEmpty() -> "Name cannot be empty"
            name.length < 2 -> "Name must be at least 2 characters"
            !name.matches(Regex("^[a-zA-Z\\s]+$")) -> "Name can contain only letters and spaces"
            else -> null
        }
    }

    fun validateEmail(email: String): String? {
        return when {
            email.isEmpty() -> "Email cannot be empty"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Invalid email format"
            else -> null
        }
    }

    fun validatePassword(password: String): String? {
        return when {
            password.isEmpty() -> "Password cannot be empty"
            password.length < 6 -> "Password must be at least 6 characters"
            !password.matches(Regex(".*[a-z].*")) -> "Password must contain at least one lowercase letter"
            !password.matches(Regex(".*[A-Z].*")) -> "Password must contain at least one uppercase letter"
            !password.matches(Regex(".*\\d.*")) -> "Password must contain at least one digit"
            else -> null
        }
    }
}
