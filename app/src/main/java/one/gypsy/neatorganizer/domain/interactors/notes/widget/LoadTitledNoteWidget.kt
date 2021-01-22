package one.gypsy.neatorganizer.domain.interactors.notes.widget

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.notes.NoteWidgetsRepository
import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntry
import one.gypsy.neatorganizer.domain.dto.notes.TitledNoteWidgetEntry
import one.gypsy.neatorganizer.domain.interactors.notes.widget.LoadTitledNoteWidget.Params
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class LoadTitledNoteWidget(private val noteWidgetsRepository: NoteWidgetsRepository) :
    BaseUseCase<TitledNoteWidgetEntry, Params>() {

    override suspend fun run(params: Params): Either<Failure, TitledNoteWidgetEntry> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(noteWidgetsRepository.getTitledNoteWidget(params.noteWidgetId))
            }
        } catch (exp: Exception) {
            Either.Left(
                LoadTitledNoteWidgetFailure(
                    exp
                )
            )
        }
    }

    data class Params(val noteWidgetId: Int)
    data class LoadTitledNoteWidgetFailure(val error: Exception) : Failure.FeatureFailure(error)
}
