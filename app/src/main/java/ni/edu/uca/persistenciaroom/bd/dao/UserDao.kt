package ni.edu.uca.persistenciaroom.bd.dao
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ni.edu.uca.persistenciaroom.bd.dao.entidades.UserEntity
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: UserEntity)
    @Query("SELECT * FROM TblUsuarios")
    suspend fun getAll(): List<UserEntity>
    @Query("SELECT * FROM TblUsuarios")
    fun getAllRealData(): LiveData<List<UserEntity>>
    @Update
    suspend fun update(usuarios: UserEntity)
    @Delete
    suspend fun delete(usuario: UserEntity)
    @Query("Delete from TblUsuarios")
    suspend fun deleteAll()
}