package ni.edu.uca.persistenciaroom.bd.dao.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.persistenciaroom.bd.dao.MainBaseDatos
import ni.edu.uca.persistenciaroom.bd.dao.entidades.GeneroEntity
import ni.edu.uca.persistenciaroom.bd.dao.repository.GeneroRepository

class GeneroViewModel(application:
                      Application
): AndroidViewModel(application) {

    val listaG: LiveData<List<GeneroEntity>>
    private val repository: GeneroRepository
    init {
        val generodao =
            MainBaseDatos.getDataBase(application).generoDao()
        repository = GeneroRepository(generodao)
        listaG = repository.lista
    }

    fun agregarGenero(genero: GeneroEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGenero(genero)
        }
    }

    fun actualizarGenero(genero: GeneroEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateGenero(genero)
        }
    }

    fun eliminarGenero(genero: GeneroEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGenero(genero)
        }
    }
    fun eliminarTodo(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}