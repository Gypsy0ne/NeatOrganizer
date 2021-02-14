package one.gypsy.neatorganizer.domain.interactors.notes.widget

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.notes.NoteWidgetsRepository

class GetAllNoteWidgetIds(private val noteWidgetsRepository: NoteWidgetsRepository) :
    BaseUseCase<IntArray, Unit>() {

    override suspend fun run(params: Unit): Either<Failure, IntArray> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(noteWidgetsRepository.getAllWidgetIds())
            }
        } catch (exp: Exception) {
            Either.Left(
                GetAllTaskWidgetIdsFailure(
                    exp
                )
            )
        }
    }

    data class GetAllTaskWidgetIdsFailure(val error: Exception) :
        Failure.FeatureFailure(error)
}
