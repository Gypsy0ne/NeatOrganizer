package one.gypsy.neatorganizer.domain.interactors.notes

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.notes.NoteDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.notes.GetNoteById.Params
import one.gypsy.neatorganizer.domain.repositories.notes.NotesRepository

class GetNoteById(
    private val notesRepository: NotesRepository
) : BaseUseCase<LiveData<NoteDto>, Params>() {

    override suspend fun run(params: Params): Either<Failure, LiveData<NoteDto>> {
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
