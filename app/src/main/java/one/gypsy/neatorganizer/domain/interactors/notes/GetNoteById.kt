package one.gypsy.neatorganizer.domain.interactors.notes

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.notes.NotesRepository
import one.gypsy.neatorganizer.domain.dto.notes.Note
import one.gypsy.neatorganizer.domain.interactors.notes.GetNoteById.Params
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class GetNoteById(private val notesRepository: NotesRepository) :
    BaseUseCase<LiveData<Note>, Params>() {

    override suspend fun run(params: Params): Either<Failure, LiveData<Note>> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(notesRepository.getNoteById(params.noteId))
            }
        } catch (exp: Exception) {
            Either.Left(
                AddRoutineFailure(
                    exp
                )
            )
        }
    }

    data class Params(val noteId: Long)
    data class AddRoutineFailure(val error: Exception) : Failure.FeatureFailure(error)
}
