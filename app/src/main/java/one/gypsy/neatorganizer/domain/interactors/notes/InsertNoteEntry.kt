package one.gypsy.neatorganizer.domain.interactors.notes

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.data.repositories.notes.NotesRepository
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry
import one.gypsy.neatorganizer.domain.interactors.notes.InsertNoteEntry.Params
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class InsertNoteEntry(private val notesRepository: NotesRepository) :
    BaseUseCase<Long, Params>() {

    override suspend fun run(params: Params): Either<Failure, Long> {
        return try {
            withContext(Dispatchers.IO) {
                Either.Right(notesRepository.insertNoteEntry(params.noteEntry))
            }
        } catch (exp: Exception) {
            Either.Left(
                AddRoutineFailure(
                    exp
                )
            )
        }
    }

    data class Params(val noteEntry: NoteEntry)
    data class AddRoutineFailure(val error: Exception) : Failure.FeatureFailure(error)
}
