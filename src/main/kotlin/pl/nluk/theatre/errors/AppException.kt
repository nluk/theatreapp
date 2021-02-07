package pl.nluk.theatre.errors

class AppException(val errorType : ErrorType, private vararg val args : Any) : Throwable() {
    override val message : String
        get() = errorType.message(*args)
}