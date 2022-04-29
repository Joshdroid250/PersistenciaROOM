package ni.edu.uca.persistenciaroom.bd.dao.viewmodels
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.persistenciaroom.bd.dao.MainBaseDatos
import ni.edu.uca.persistenciaroom.bd.dao.entidades.UserEntity
import ni.edu.uca.persistenciaroom.bd.dao.repository.UserRepository

class UserViewModels (application:
Application):AndroidViewModel(application)
    {
        val lista : LiveData<List<UserEntity>>
        private val repository: UserRepository
        init {
            val usuarioDao =
                MainBaseDatos.getDataBase(application).usuariosDao()
            repository = UserRepository(usuarioDao)
            lista = repository.listado
        }
        fun agregarUsuario(usuario: UserEntity){
            viewModelScope.launch(Dispatchers.IO) {
                repository.addUsuario(usuario)
            }
        }
        fun actualizarUsuario(usuario: UserEntity){
            viewModelScope.launch(Dispatchers.IO){
                repository.updateUsuario(usuario)
            }
        }
        fun eliminarUsuario(usuario: UserEntity){
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteUsuario(usuario)
            }
        }
        fun eliminarTodo(){
            viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}
