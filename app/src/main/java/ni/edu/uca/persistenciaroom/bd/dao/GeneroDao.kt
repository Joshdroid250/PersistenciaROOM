package ni.edu.uca.persistenciaroom.bd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ni.edu.uca.persistenciaroom.bd.dao.entidades.GeneroEntity

@Dao
interface GeneroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenero(gen: GeneroEntity)

    @Query("SELECT * FROM Genero")
    fun getAllRealData(): LiveData<List<GeneroEntity>>

    @Query("SELECT * FROM Genero")
    suspend fun getAllGenero(): List<GeneroEntity>

    @Query("SELECT * FROM Genero where id_Genero= :id")
    suspend fun getByIdGenero(id: Int): GeneroEntity

    @Update
    fun updateGenero(usuario: GeneroEntity)

    @Delete
    fun deleteGenero(usuario: GeneroEntity)
    @Query("Delete from Genero")
    suspend fun deleteAll()

}