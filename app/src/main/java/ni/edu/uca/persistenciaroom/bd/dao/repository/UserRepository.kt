package ni.edu.uca.persistenciaroom.bd.dao.repository
import androidx.lifecycle.LiveData
import ni.edu.uca.persistenciaroom.bd.dao.UserDao
import ni.edu.uca.persistenciaroom.bd.dao.entidades.UserEntity

class UserRepository(private val dao: UserDao) {
    val listado : LiveData<List<UserEntity>> =
        dao.getAllRealData()
    suspend fun addUsuario(usuario: UserEntity){
        dao.insert(usuario)
    }
    suspend fun updateUsuario(usuario: UserEntity){
        dao.update(usuario)
    }
    suspend fun deleteUsuario(usuario: UserEntity){
        dao.delete(usuario)
    }
    suspend fun deleteAll(){
        dao.deleteAll()

    }
}
