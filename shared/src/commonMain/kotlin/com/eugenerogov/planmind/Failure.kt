package com.eugenerogov.planmind

sealed class Failure {
    data object NetworkConnection : Failure()

    class ServerError(val message: String) : Failure()

    class UnknownHostException() : Failure()

    class SocketTimeoutException() : Failure()

    class ConnectException() : Failure()

    class Unauthorized() : Failure()

    class InvalidGrant() : Failure()

    class NotFound() : Failure()

    class Throwable(val message: String) : Failure()

    class NetworkError(val message: String) : Failure()

    data object NoContent : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}
