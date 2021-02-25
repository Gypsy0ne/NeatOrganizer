package one.gypsy.neatorganizer.database

import androidx.room.Room
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            OrganizerDatabase::class.java,
            "NeatOrganizer.db"
        ).build()
    }

    factory { get<OrganizerDatabase>().routineTasksDao() }
    factory { get<OrganizerDatabase>().routinesDao() }
    factory { get<OrganizerDatabase>().routinesSchedulesDao() }
    single { get<OrganizerDatabase>().singleTaskGroupsDao() }
    single { get<OrganizerDatabase>().singleTasksDao() }
    factory { get<OrganizerDatabase>().routineSnapshotsDao() }
    factory { get<OrganizerDatabase>().taskWidgetDao() }
    factory { get<OrganizerDatabase>().notesDao() }
    factory { get<OrganizerDatabase>().noteWidgetDao() }
}
