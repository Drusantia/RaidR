package net.drusantia.raidr.data.persistence
//
//import android.content.Context
//import androidx.room.*
//import net.drusantia.raidr.BuildConfig
//import net.drusantia.raidr.data.model.character.*
//import net.drusantia.raidr.data.persistence.dao.PlayerCharacterDao
//
//@Database(
//    entities = [
//        PlayerCharacter::class,
//        MythicPlusRun::class,
//        MythicPlusScores::class,
//        MythicPlusRankingLevel::class,
//        RaidProgression::class
//    ],
//    version = 1)
//abstract class RaidrDatabase : RoomDatabase() {
//    companion object {
//        @Volatile
//        private var instance: RaidrDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: buildDatabase(context).also { instance = it }
//        }
//
//        private fun buildDatabase(context: Context) = Room
//            .databaseBuilder(
//                context,
//                RaidrDatabase::class.java,
//                BuildConfig.ROOM_DB_NAME)
//            .build()
//    }
//
//    abstract fun characterProfileDao(): PlayerCharacterDao
//}