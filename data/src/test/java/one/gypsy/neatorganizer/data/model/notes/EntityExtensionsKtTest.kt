package one.gypsy.neatorganizer.data.model.notes

import one.gypsy.neatorganizer.database.entity.notes.NoteEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EntityExtensionsKtTest {

    @Test
    fun shouldMapToNoteEntry() {
        //  given
        val noteEntity = NoteEntity(
            id = 1,
            title = "test",
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
        val noteEntity = NoteEntity(
            id = 1,
            title = "test",
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
