package one.gypsy.neatorganizer.domain.interactors.notes

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.notes.NotesRepository
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class GetAllNoteEntries(
    private val notesRepository: NotesRepository
) : BaseUseCase<LiveData<List<NoteEntry>>, Unit>() {

    override suspend fun run(params: Unit): Either<Failure, LiveData<List<NoteEntry>>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(notesRepository.getAllNoteEntriesObservable())
            }
        } catch (exp: Exception) {
            Either.Left(
                AddRoutineFailure(
                    exp
                )
            )
        }
    }

    data class AddRoutineFailure(val error: Exception) : Failure.FeatureFailure(error)
}
