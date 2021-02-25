package one.gypsy.neatorganizer.domain.interactors.notes

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.notes.DeleteNoteById.Params
import one.gypsy.neatorganizer.domain.repositories.notes.NotesRepository

class DeleteNoteById(
    private val notesRepository: NotesRepository
) : BaseUseCase<Unit, Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(notesRepository.deleteNoteById(params.noteId))
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
