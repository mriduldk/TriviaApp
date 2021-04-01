package `in`.codingstudio.triviaapp.data

import androidx.lifecycle.LiveData

class PersonRepository(private val dao : PersonDao) {

    val allPersons : LiveData<List<Person>> = dao.getPersonData()

    suspend fun insert(person : Person) : Long{
        return dao.insert(person)
    }

    suspend fun update(person : Person){
        dao.update(person)
    }

    suspend fun delete(person: Person){
        dao.delete(person)
    }

    suspend fun getPerson(id : Long) : Person{
        return dao.getPerson(id)
    }

    suspend fun getAllPerson() : LiveData<List<Person>>{
        return dao.getPersonData()
    }

}