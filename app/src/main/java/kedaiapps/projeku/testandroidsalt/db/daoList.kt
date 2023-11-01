package kedaiapps.projeku.testandroidsalt.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kedaiapps.projeku.testandroidsalt.db.table.ListTable

@Dao
interface daoList {
    @Query("SELECT * FROM ListTable")
    fun getAll() : LiveData<List<ListTable>>

    @Query("SELECT * FROM ListTable WHERE id=:id")
    fun getById(id: String): LiveData<ListTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg data: ListTable)

//    @Query("UPDATE ListPokemonTable SET status=:status WHERE id=:id")
//    fun update(id: String, status: String)

    @Query("DELETE FROM ListTable")
    fun deleteAll()
}