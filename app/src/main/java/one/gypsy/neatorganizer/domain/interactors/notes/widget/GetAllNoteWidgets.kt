package one.gypsy.neatorganizer.domain.interactors.notes.widget

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.notes.NoteWidgetsRepository
import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class GetAllNoteWidgets(private val noteWidgetsRepository: NoteWidgetsRepository) :
    BaseUseCase<LiveData<List<NoteWidgetEntry>>, Unit>() {

    override suspend fun run(params: Unit): Either<Failure, LiveData<List<NoteWidgetEntry>>> {
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
