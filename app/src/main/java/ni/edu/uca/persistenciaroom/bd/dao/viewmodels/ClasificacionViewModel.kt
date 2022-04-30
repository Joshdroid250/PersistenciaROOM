package ni.edu.uca.persistenciaroom.bd.dao.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.persistenciaroom.bd.dao.MainBaseDatos
import ni.edu.uca.persistenciaroom.bd.dao.entidades.ClasificacionEntity
import ni.edu.uca.persistenciaroom.bd.dao.repository.ClasificacionRepository

class ClasificacionViewModel(application: Application): AndroidViewModel(application) {
    val listaC : LiveData<List<ClasificacionEntity>>
    private val repository: ClasificacionRepository

    init {
        val clasificacionDao =
            MainBaseDatos.getDataBase(application).clasificacionDao()
        repository = ClasificacionRepository(clasificacionDao)
        listaC = repository.listado
    }

    fun agregarClasificacion(clasificacion: ClasificacionEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addClasificacion(clasificacion)
        }
    }

    fun actualizarClasificacion(clasificacion: ClasificacionEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateClasificacion(clasificacion)
        }
    }

    fun eliminarClasificacion(clasificacion: ClasificacionEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteClasificacion(clasificacion)
        }
    }

    fun eliminarTodo(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletaAll()
        }
    }
}