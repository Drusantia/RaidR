@file:Suppress("KDocUnresolvedReference")

package net.drusantia.raidr.utils.extensions

import android.content.Context
import android.graphics.PorterDuff
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import net.drusantia.raidr.utils.SimpleTextWatcher

/**
 * Input Validator data for EditText validations.
 * The validator lambda must contain the valid case, like: { input -> isValidEmail(input) }.
 * If the isValidEmail(input) returns false, than the validation fails with the given error message. */
data class InputValidator(
        val validator: (String) -> Boolean,
        val errorMessage: String = "Invalid input"
)

/**
 * Validates an EditText (and descendants, like AppCompatEditText) with the given validator.
 * Sets the EditText's own error property with the given message if the input is not valid.
 * Note: this extension is capable of handling one single validation. To use multiple ones, see [multiValidate].
 * Returns [true] is all validation passed, false if there is a validation fail, null if no validators was given or not just triggering, but adding validators. */
fun EditText.validate(
        validator: (String) -> Boolean,
        message: String,
        justTriggerValidation: Boolean = false
): Boolean? {
    val validate: () -> Boolean = {
        //val validationResult = "$text".isValid(validator, message)
        //error = validationResult.second
        //validationResult.first
        "$text".isValid(validator, message).run {
            error = second
            return@run first
        }
    }

    return if (justTriggerValidation) validate()
    else {
        addTextChangedListener(SimpleTextWatcher(afterChanged = { validate() }))
        null
    }
}

/**
 * Validates an EditText (and descendants, like AppCompatEditText) with the given validators.
 * Sets the EditText's own error property with the given message if the input is not valid.
 * Note: Stops validating at the first validation error, so the order of [InputValidator]s in the parameter matters.
 * Note: To add a single validation (which is simpler), see [validate].
 * Returns [true] is all validation passed, false if there is a validation fail, null if no validators was given or not just triggering, but adding validators.
 */
fun EditText.multiValidate(
        validators: Array<InputValidator>,
        justTriggerValidation: Boolean = false
): Boolean? {
    if (validators.isEmpty()) return null
    val validate = {
        var hasError = false
        for (iv in validators) {
            val validationResult = "$text".isValid(iv.validator)
            if (!validationResult.first) {
                error = iv.errorMessage
                hasError = true
                break
            } else error = null
        }
        !hasError
    }

    return if (justTriggerValidation) validate()
    else {
        addTextChangedListener(SimpleTextWatcher(afterChanged = { validate() }))
        null
    }
}

/**
 * Validates an EditText (and descendants, like AppCompatEditText) with the given validator.
 * Sets the given [TextView]'s text (error message or empty) and visibility (visible or gone) with the given message if the input is not valid.
 * Note: this extension is capable of handling one single validation. To use multiple ones, see [multiValidateWithErrorTextView].
 * Returns [true] is all validation passed, false if there is a validation fail, null if no validators was given or not just triggering, but adding validators. */
fun EditText.validateWithErrorTextView(
        validator: (String) -> Boolean,
        errorMessage: String,
        errorView: View? = null,
        justTriggerValidation: Boolean = false
): Boolean? {
    val validate: () -> Boolean = {
        val validationResult = "$text".isValid(validator)
        if (!validationResult.first) {
            when {
                errorView != null -> {
                    when (errorView) {
                        is TextView -> {
                            errorView._visible()
                            errorView.text = errorMessage
                        }
                        is TextInputLayout -> errorView.error = errorMessage
                    }
                }
                parent is TextInputLayout -> (parent as TextInputLayout).error = errorMessage
                parent.parent is TextInputLayout -> (parent.parent as TextInputLayout).error = errorMessage
            }
        } else when {
            errorView != null -> {
                when (errorView) {
                    is TextView -> {
                        errorView._gone()
                        errorView.text = String.empty
                    }
                    is TextInputLayout -> errorView.error = null
                }
            }
            parent is TextInputLayout -> (parent as TextInputLayout).error = null
            parent.parent is TextInputLayout -> (parent.parent as TextInputLayout).error = null
        }
        validationResult.first
    }

    return if (justTriggerValidation) validate()
    else {
        addTextChangedListener(SimpleTextWatcher(afterChanged = { validate() }))
        null
    }
}

/**
 * Validates an EditText (and descendants, like AppCompatEditText) with the given validator.
 * Sets the given [TextView]'s text (error message or empty) and visibility (visible or gone) with the given message if the input is not valid.
 * Note: Stops validating at the first validation error, so the order of [InputValidator]s in the parameter matters.
 * Note: To add a single validation (which is simpler), see [validateWithErrorTextView].
 * Returns [true] is all validation passed, false if there is a validation fail, null if no validators was given or not just triggering, but adding validators. */
fun EditText.multiValidateWithErrorTextView(
        validators: Array<InputValidator>,
        errorView: View? = null,
        justTriggerValidation: Boolean = false
): Boolean? {
    if (validators.isEmpty()) return null
    val validate: () -> Boolean = {
        var hasError = false
        for (iv in validators) {
            if (!"$text".isValid(iv.validator).first) {
                when {
                    errorView != null -> {
                        when (errorView) {
                            is TextView -> {
                                errorView._visible()
                                errorView.text = iv.errorMessage
                            }
                            is TextInputLayout -> errorView.error = iv.errorMessage
                        }
                    }
                    parent is TextInputLayout -> (parent as TextInputLayout).error = iv.errorMessage
                    parent.parent is TextInputLayout -> (parent.parent as TextInputLayout).error = iv.errorMessage
                }
                hasError = true
                break
            } else when {
                errorView != null -> when (errorView) {
                    is TextView -> {
                        errorView._gone()
                        errorView.text = String.empty
                    }
                    is TextInputLayout -> errorView.error = String.empty
                }
                parent is TextInputLayout -> (parent as TextInputLayout).error = null
                parent.parent is TextInputLayout -> (parent.parent as TextInputLayout).error = null
            }
        }
        !hasError
    }

    return if (justTriggerValidation) validate()
    else {
        addTextChangedListener(SimpleTextWatcher(afterChanged = { validate() }))
        null
    }
}

fun View.onEnterKey(body: () -> Unit) {
    setOnKeyListener { _, keyCode, _ ->
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            body()
            true
        } else false
    }
}

fun Context.coloredToast(
        message: String,
        backgroundColorId: Int? = null,
        textColorId: Int? = null,
        isLongToast: Boolean = true
) = Toast.makeText(
        this,
        message,
        if (isLongToast) Toast.LENGTH_LONG else Toast.LENGTH_LONG
).run {
    backgroundColorId?.let {
        view.background.setColorFilter(ContextCompat.getColor(this@coloredToast, it), PorterDuff.Mode.SRC_IN)
    }
    textColorId?.let {
        view.findViewById<TextView>(android.R.id.message).apply {
            setTextColor(ContextCompat.getColor(this@coloredToast, it))
        }
    }
    show()
}