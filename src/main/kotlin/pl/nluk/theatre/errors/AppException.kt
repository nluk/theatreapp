package pl.nluk.theatre.errors

class AppException(val errorType : ErrorType = ErrorType.UNKNOWN_ERROR, private vararg val args : Any) : Throwable() {
    override val message : String
        get() = errorType.message(*args)
}