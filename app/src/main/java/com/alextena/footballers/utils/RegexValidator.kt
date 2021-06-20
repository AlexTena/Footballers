package com.alextena.footballers.utils

import java.util.regex.Pattern

class RegexValidator {

    companion object {
        //Email pattern
        private val EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        //Password pattern
        private val PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*).{8,20}"
        )

        fun isValidEmail(email: CharSequence): Boolean {
            return EMAIL_PATTERN.matcher(email).matches()
        }

        fun isValidPassword(password: CharSequence): Boolean {
            return PASSWORD_PATTERN.matcher(password).matches()
        }
    }
}