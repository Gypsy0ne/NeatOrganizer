package one.gypsy.neatorganizer.database

import android.content.ContentValues
import androidx.room.OnConflictStrategy
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity
import one.gypsy.tutorial.TutorialTaskGroup

class ShowcaseDataProvider : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        db.createShowcaseTaskGroup()
    }

    private fun SupportSQLiteDatabase.createShowcaseTaskGroup() {
        val showcaseRecord = ContentValues().apply {
            put(SingleTaskGroupEntity.NAME_FIELD, TutorialTaskGroup.name)
            put(SingleTaskGroupEntity.CREATED_AT_FIELD, TutorialTaskGroup.creationTime)
        }
        insert(SingleTaskGroupEntity.TABLE_NAME, OnConflictStrategy.IGNORE, showcaseRecord)
    }
}
