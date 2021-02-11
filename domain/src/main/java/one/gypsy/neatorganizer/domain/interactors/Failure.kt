package one.gypsy.neatorganizer.domain.interactors

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure(val exception: Exception = Exception("Failure")) {

    /** * Extend this class for feature specific failures.*/
    open class FeatureFailure(featureException: Exception = Exception("Feature failure")) : Failure(featureException)

    override fun equals(other: Any?): Boolean {
        return other is Failure
    }
}
