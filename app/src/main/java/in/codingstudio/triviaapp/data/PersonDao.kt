package `in`.codingstudio.triviaapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person : Person) : Long

    @Update
    suspend fun update(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Query("SELECT * FROM person_table")
    fun getPersonData() : LiveData<List<Person>>

    @Query("SELECT * FROM person_table WHERE id = :personId")
    suspend fun getPerson(personId : Long) : Person

}