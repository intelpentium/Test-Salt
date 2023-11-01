package kedaiapps.projeku.testandroidsalt.db.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String,
    val title: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
)