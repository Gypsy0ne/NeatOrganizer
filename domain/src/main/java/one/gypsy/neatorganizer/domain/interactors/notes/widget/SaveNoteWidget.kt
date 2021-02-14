package one.gypsy.neatorganizer.domain.interactors.notes.widget

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntry
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.notes.widget.SaveNoteWidget.Params
import one.gypsy.neatorganizer.domain.repositories.notes.NoteWidgetsRepository

class SaveNoteWidget(private val noteWidgetsRepository: NoteWidgetsRepository) :
    BaseUseCase<Unit, Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(noteWidgetsRepository.insertNoteWidget(params.noteWidget))
            }
        } catch (exp: Exception) {
            Either.Left(
                SaveNoteWidgetFailure(
                    exp
                )
            )
        }
    }

    data class Params(val noteWidget: NoteWidgetEntry)
    data class SaveNoteWidgetFailure(val error: Exception) : Failure.FeatureFailure(error)
}
