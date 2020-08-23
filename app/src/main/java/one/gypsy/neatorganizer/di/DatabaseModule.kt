package one.gypsy.neatorganizer.di

import androidx.room.Room
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            OrganizerDatabase::class.java,
            "NeatOrganizer59.db"
        ).build()
    }

    factory { get<OrganizerDatabase>().interactionDao() }
    factory { get<OrganizerDatabase>().personDao() }
    factory { get<OrganizerDatabase>().routineTasksDao() }
    factory { get<OrganizerDatabase>().routinesDao() }
    factory { get<OrganizerDatabase>().routinesSchedulesDao() }
    factory { get<OrganizerDatabase>().singleTaskGroupsDao() }
    factory { get<OrganizerDatabase>().singleTasksDao() }
    factory { get<OrganizerDatabase>().routineSnapshotDaysDao() }
    factory { get<OrganizerDatabase>().routineSnapshotTasksDao() }
    factory { get<OrganizerDatabase>().routineSnapshotsDao() }
}