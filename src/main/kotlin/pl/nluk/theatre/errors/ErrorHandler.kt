package pl.nluk.theatre.errors

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(AppException::class)
    protected fun handleError(fuelException: AppException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(fuelException.message, fuelException.errorType.status.name, fuelException.errorType.status.value(), Date())
        return ResponseEntity(errorMessage, fuelException.errorType.status)
    }

    protected data class ErrorMessage(
        val message : String,
        val statusCode : String,
        val status : Int,
        val timestamp : Date
    )
}