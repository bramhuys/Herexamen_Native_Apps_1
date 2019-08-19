package be.hogent.student.native_apps_1_android_creative_app_bramhuys.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.util.DATABASE_NAME
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.workers.StationDatabaseWorker

/**
 * Instantie aanmaken van database
 *
 */
@Database(entities = [StationModel::class ], version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase(){
    abstract fun stationDao() : StationDao

    companion object {
        @Volatile private var instance : AppDatabase? = null
        fun getInstance(context: Context):AppDatabase{
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        /**
         * Database aanmaken en opvullen van data
         *
         */
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                      val request = OneTimeWorkRequestBuilder<StationDatabaseWorker>().build()
                        WorkManager.getInstance().enqueue(request)
                    }
                })
                .build()
        }
    }

}