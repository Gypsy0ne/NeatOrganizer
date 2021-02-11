package one.gypsy.neatorganizer.domain.interactors.notes.widget

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.interactors.notes.widget.DeleteNoteWidgetById.Params
import one.gypsy.neatorganizer.domain.repositories.notes.NoteWidgetsRepository
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class DeleteNoteWidgetById(private val noteWidgetsRepository: NoteWidgetsRepository) :
    BaseUseCase<Unit, Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(noteWidgetsRepository.deleteNoteWidgetById(params.noteWidgetId))
            }
        } catch (exp: Exception) {
            Either.Left(
                DeleteNoteWidgetByIdFailure(
                    exp
                )
            )
        }
    }

    data class Params(val noteWidgetId: Int)
    data class DeleteNoteWidgetByIdFailure(val error: Exception) : Failure.FeatureFailure(error)
}
