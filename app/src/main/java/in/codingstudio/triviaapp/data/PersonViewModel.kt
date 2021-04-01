package `in`.codingstudio.triviaapp.data

import android.app.Application
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.first

class PersonViewModel(application: Application) : AndroidViewModel(application) {

    val allPersons : LiveData<List<Person>>
    private var _personId = MutableLiveData(0.toLong())
    val personId : LiveData<Long> = _personId

    private val dataStore : DataStore<Preferences>
    private val repository : PersonRepository


    init {
        val dao = PersonDatabase.getInstance(application).personDao
        repository = PersonRepository(dao)
        allPersons = repository.allPersons
        dataStore = application.createDataStore(name = "person_data")
    }

    suspend fun insert(person : Person) : Long{
        return repository.insert(person)
    }

    suspend fun getPerson(id : Long) : Person{
        return repository.getPerson(id)
    }

    suspend fun getAllPerson() : LiveData<List<Person>>{
        return repository.getAllPerson()
    }


    fun saveId(id : Long ){
        _personId.value = id
    }


    suspend fun savePreferences(key : String, value : String){
        val dataStoreKey = preferencesKey<String>(key)

        dataStore.edit {
            it[dataStoreKey] = value
        }
    }

    suspend fun readPreferences(key: String) : String? {
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

}