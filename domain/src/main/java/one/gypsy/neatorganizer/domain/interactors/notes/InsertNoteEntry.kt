package one.gypsy.neatorganizer.domain.interactors.notes

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntryDto
import one.gypsy.neatorganizer.domain.interactors.BaseUseCase
import one.gypsy.neatorganizer.domain.interactors.Either
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.notes.InsertNoteEntry.Params
import one.gypsy.neatorganizer.domain.repositories.notes.NotesRepository

class InsertNoteEntry(
    private val notesRepository: NotesRepository
) : BaseUseCase<Unit, Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> {
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

    data class Params(val noteEntry: NoteEntryDto)
    data class AddRoutineFailure(val error: Exception) : Failure.FeatureFailure(error)
}
