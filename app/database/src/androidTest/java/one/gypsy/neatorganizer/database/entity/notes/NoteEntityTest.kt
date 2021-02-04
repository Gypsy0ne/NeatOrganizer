package one.gypsy.neatorganizer.database.entity.notes

import one.gypsy.neatorganizer.database.DatabaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class NoteEntityTest : DatabaseTest() {

    private lateinit var notesDao: one.gypsy.neatorganizer.database.dao.notes.NotesDao

    @Before
    override fun setup() {
        super.setup()
        notesDao = database.notesDao()
    }

    @Test
    fun shouldInsertNote() {
        // given
        val noteId = 1L
        val note = one.gypsy.neatorganizer.database.entity.notes.NoteEntity(
            id = noteId,
            title = "elo",
            content = "really short content",
            createdAt = System.currentTimeMillis(),
            color = 123123
        )

        // when
        notesDao.insert(note)
        val fetchedNotes = notesDao.getAllNotes()

        // then
        assertThat(fetchedNotes).containsOnly(note)
    }

    @Test
    fun shouldUpdateNote() {
        // given
        val noteId = 1L
        val note = one.gypsy.neatorganizer.database.entity.notes.NoteEntity(
            id = noteId,
            title = "elo",
            content = "really short content",
            createdAt = System.currentTimeMillis(),
            color = 123123
        )
        val updatedNote = note.copy(title = "updated one", content = "updated story")

        // when
        notesDao.insert(note)
        notesDao.update(updatedNote)
        val fetchedNotes = notesDao.getAllNotes()

        // then
        assertThat(fetchedNotes).containsOnly(updatedNote)
    }

    @Test
    fun shouldRemoveNote() {
        // given
        val noteId = 1L
        val note = one.gypsy.neatorganizer.database.entity.notes.NoteEntity(
            id = noteId,
            title = "elo",
            content = "really short content",
            createdAt = System.currentTimeMillis(),
            color = 123123
        )

        // when
        notesDao.insert(note)
        notesDao.delete(note)
        val fetchedNotes = notesDao.getAllNotes()

        // then
        assertThat(fetchedNotes).hasSize(0)
    }

    @Test
    fun shouldGetNoteById() {
        // given
        val noteId = 1L
        val note = one.gypsy.neatorganizer.database.entity.notes.NoteEntity(
            id = noteId,
            title = "elo",
            content = "really short content",
            createdAt = System.currentTimeMillis(),
            color = 123123
        )

        // when
        notesDao.insert(note)
        val fetchedNote = notesDao.getNoteByIdObservable(noteId)

        // then
        fetchedNote.observeForever {
            assertThat(it).isEqualToComparingFieldByField(note)
        }
    }

    @Test
    fun shouldGetAllNotesObservable() {
        // given
        val notes = arrayOf(
            one.gypsy.neatorganizer.database.entity.notes.NoteEntity(
                id = 1,
                title = "elo1",
                content = "really short content1",
                createdAt = System.currentTimeMillis(),
                color = 123123
            ),
            one.gypsy.neatorganizer.database.entity.notes.NoteEntity(
                id = 2,
                title = "elo2",
                content = "really short content2",
                createdAt = System.currentTimeMillis(),
                color = 123123
            ),
            one.gypsy.neatorganizer.database.entity.notes.NoteEntity(
                id = 3,
                title = "elo3",
                content = "really short content3",
                createdAt = System.currentTimeMillis(),
                color = 123123
            )
        )

        // when
        notesDao.insert(*notes)
        val fetchedNotesObservable = notesDao.getAllNotesObservable()

        // then
        fetchedNotesObservable.observeForever {
            assertThat(it).containsExactlyInAnyOrderElementsOf(notes.toList())
        }
    }

    @Test
    fun shouldGetAllNotes() {
        // given
        val notes = arrayOf(
            one.gypsy.neatorganizer.database.entity.notes.NoteEntity(
                id = 1,
                title = "elo1",
                content = "really short content1",
                createdAt = System.currentTimeMillis(),
                color = 123122
            ),
            one.gypsy.neatorganizer.database.entity.notes.NoteEntity(
                id = 2,
                title = "elo2",
                content = "really short content2",
                createdAt = System.currentTimeMillis(),
                color = 123111
            ),
            one.gypsy.neatorganizer.database.entity.notes.NoteEntity(
                id = 3,
                title = "elo3",
                content = "really short content3",
                createdAt = System.currentTimeMillis(),
                color = 1266654
            )
        )

        // when
        notesDao.insert(*notes)
        val fetchedNotes = notesDao.getAllNotes()

        // then
        assertThat(fetchedNotes).containsExactlyInAnyOrderElementsOf(notes.toList())
    }

    @Test
    fun shouldMapToNoteEntry() {
        //  given
        val noteEntity = one.gypsy.neatorganizer.database.entity.notes.NoteEntity(
            id = 1,
            title = "elo1",
            content = "really short content1",
            createdAt = System.currentTimeMillis(),
            color = 123123
        )

        // when
        val noteEntry = noteEntity.toNoteEntry()

        // then
        with(noteEntry) {
            assertThat(id).isEqualTo(noteEntity.id)
            assertThat(title).isEqualTo(noteEntity.title)
            assertThat(createdAt).isEqualTo(noteEntity.createdAt)
            assertThat(color).isEqualTo(noteEntity.color)
        }
    }

    @Test
    fun shouldMapToNote() {
        //  given
        val noteEntity = one.gypsy.neatorganizer.database.entity.notes.NoteEntity(
            id = 1,
            title = "elo1",
            content = "really short content1",
            createdAt = System.currentTimeMillis(),
            color = 123123
        )

        // when
        val note = noteEntity.toNote()

        // then
        with(note) {
            assertThat(id).isEqualTo(noteEntity.id)
            assertThat(content).isEqualTo(noteEntity.content)
            assertThat(title).isEqualTo(noteEntity.title)
            assertThat(createdAt).isEqualTo(noteEntity.createdAt)
            assertThat(color).isEqualTo(note.color)
        }
    }
}
