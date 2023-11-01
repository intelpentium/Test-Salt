package kedaiapps.projeku.testandroidsalt.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kedaiapps.projeku.testandroidsalt.common.storage.Preferences
import kedaiapps.projeku.testandroidsalt.db.SaltDB
import kedaiapps.projeku.testandroidsalt.db.table.ListTable
import kedaiapps.projeku.testandroidsalt.ext.ioThread
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    val application: Application,
    val preferences: Preferences
) : ViewModel() {

    val db = SaltDB.getDatabase(this.application)

    //======================= Local Database List ===================
    fun setList(
        author: String,
        title: String,
        description: String,
        urlToImage: String,
        publishedAt: String,
        content: String
    ) {
        ioThread {
            db.daoList()
                .insert(ListTable(0, author, title, description, urlToImage, publishedAt, content))
        }
    }

    fun getList(): LiveData<List<ListTable>> {
        return db.daoList().getAll()
    }

    fun getListId(fav_id: String): LiveData<ListTable> {
        return db.daoList().getById(fav_id)
    }

//    fun updateList(fav_id: String, status: String){
//        ioThread {
//            db.daoList().update(fav_id, status)
//        }
//    }

    fun deleteList() {
        ioThread {
            db.daoList().deleteAll()
        }
    }

    fun setCurrentPage(currentPage: Int) {
        preferences.currentPage = currentPage
    }

    fun getCurrentPage() = preferences.currentPage
}