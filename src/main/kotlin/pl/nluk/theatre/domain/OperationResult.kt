package pl.nluk.theatre.domain

import java.util.*

interface OperationResult<T>

class Success<T : Any>(val data : T) : OperationResult<T>

class Error<T : Any>(val error : String) : OperationResult<T>{
    var timestamp : Date = Date()
}