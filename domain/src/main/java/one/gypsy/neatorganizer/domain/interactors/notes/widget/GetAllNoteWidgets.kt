package one.gypsy.neatorganizer.domain.interactors.notes.widget

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntryDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.repositories.notes.NoteWidgetsRepository

class GetAllNoteWidgets(private val noteWidgetsRepository: NoteWidgetsRepository) :
    BaseUseCase<LiveData<List<NoteWidgetEntryDto>>, Unit>() {

    override suspend fun run(params: Unit): Either<Failure, LiveData<List<NoteWidgetEntryDto>>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(noteWidgetsRepository.getAllNoteWidgets())
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
