package one.gypsy.neatorganizer.domain.interactors.notes.widget

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.notes.NoteWidgetsRepository
import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntry
import one.gypsy.neatorganizer.domain.interactors.notes.widget.UpdateNoteWidget.Params
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class UpdateNoteWidget(private val noteWidgetsRepository: NoteWidgetsRepository) :
    BaseUseCase<Unit, Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(noteWidgetsRepository.updateNoteWidget(params.noteWidget))
            }
        } catch (exp: Exception) {
            Either.Left(
                UpdateNoteWidgetFailure(
                    exp
                )
            )
        }
    }

    data class Params(val noteWidget: NoteWidgetEntry)
    data class UpdateNoteWidgetFailure(val error: Exception) : Failure.FeatureFailure(error)
}
