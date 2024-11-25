package com.jihan.whatsapp.domain.utils

import android.util.Patterns

object HelperClass {

    fun validateUserCredentials(
        userName: String = "Default",
        email: String,
        password: String,
        confirmPassword:String? = null
    ): Pair<Boolean, String> {

        val result = Pair(true, "")

        return if (userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Pair(false, "Please Provide All Required Information")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Pair(false, "Invalid Email Address")
        } else if (password.length < 5) {
            Pair(false, "Password should be at least 5 characters long")
        } else if (confirmPassword != null && confirmPassword != password) {
            Pair(false, "Passwords do not match")
        }else {
            result
        }

    }

}