package net.drusantia.raidr.utils

import net.drusantia.raidr.utils.extensions.InputValidator
import net.drusantia.raidr.utils.extensions.empty
import java.util.regex.Pattern

object Validator {
    private const val PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,20}\$"
    private const val EMAIL_REGEX = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@" +
        "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
        "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." +
        "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
        "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|" +
        "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,})$"

    private val PASSWORD_PATTERN by lazy { Pattern.compile(PASSWORD_REGEX) }
    private val EMAIL_PATTERN by lazy { Pattern.compile(EMAIL_REGEX) }

    val notEmptyValidator: (String) -> Boolean = { s -> s.isNotEmpty() }
    fun minLength(string: String, min: Int) = string.trim().length >= min
    fun maxLength(string: String, max: Int) = string.trim().length <= max

    fun hasOnlyDigits(string: String, allowEmpty: Boolean = true): Boolean {
        val isAllDigit = string.all { it.isDigit() }
        return if (allowEmpty)
            string.isNotEmpty() && isAllDigit
        else string.isNotEmpty() && isAllDigit
    }

    fun isValidPassword(password: String) = PASSWORD_PATTERN
        .matcher(password.trim())
        .matches()

    fun isValidEmail(email: String) = EMAIL_PATTERN
        .matcher(email)
        .matches()

    fun getNotEmptyValidator(
        toValidate: () -> String = { String.empty },
        errorMessage: String = String.empty
    ) = InputValidator({ notEmptyValidator(toValidate()) }, errorMessage)

    fun getGreaterThanValueValidator(
        minValue: Double = 0.0,
        toValidate: () -> Double = { minValue },
        errorMessage: String = String.empty
    ) = arrayOf(InputValidator({ toValidate() > minValue }, errorMessage))

    fun getGreaterThanOrEqualValueValidator(
        minValue: Double = 0.0,
        toValidate: () -> Double = { minValue },
        errorMessage: String = String.empty
    ) = arrayOf(InputValidator({ toValidate() >= minValue }, errorMessage))

    fun getLessThanValueValidator(
        maxValue: Double = 0.0,
        toValidate: () -> Double = { maxValue },
        errorMessage: String = String.empty
    ) = arrayOf(InputValidator({ toValidate() < maxValue }, errorMessage))

    fun getLessThanOrEqualValueValidator(
        maxValue: Double = 0.0,
        toValidate: () -> Double = { maxValue },
        errorMessage: String = String.empty
    ) = arrayOf(InputValidator({ toValidate() <= maxValue }, errorMessage))
}
