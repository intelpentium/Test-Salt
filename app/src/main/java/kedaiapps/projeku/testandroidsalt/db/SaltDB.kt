package kedaiapps.projeku.testandroidsalt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kedaiapps.projeku.testandroidsalt.db.table.ListTable

@Database(entities = [ListTable::class], version = 1)
abstract class SaltDB : RoomDatabase(){

    companion object {
        private var INSTANCE: SaltDB? = null

        fun getDatabase(context: Context): SaltDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, SaltDB::class.java, "SaltDB")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as SaltDB
        }
    }

    abstract fun daoList() : daoList
}