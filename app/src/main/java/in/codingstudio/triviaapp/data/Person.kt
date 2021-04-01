package `in`.codingstudio.triviaapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat


@Entity(tableName = "person_table")
data class Person(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val name : String,
    val cricketer : String,
    val flag : String,
    val created : Long = System.currentTimeMillis()
) {

    val createdDateFormat : String
        get() = DateFormat.getDateTimeInstance().format(created)

}