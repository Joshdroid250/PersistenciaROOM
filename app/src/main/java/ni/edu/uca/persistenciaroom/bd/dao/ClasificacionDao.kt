package ni.edu.uca.persistenciaroom.bd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ni.edu.uca.persistenciaroom.bd.dao.entidades.ClasificacionEntity

@Dao
interface ClasificacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clasificacion: ClasificacionEntity)

    @Query("SELECT * FROM clasificacion")
    suspend fun getAll(): List<ClasificacionEntity>

    @Query("SELECT * FROM clasificacion")
    fun getAllRealData(): LiveData<List<ClasificacionEntity>>

    @Update
    fun update(clasificacion: ClasificacionEntity)

    @Delete
    fun delete(clasificacion: ClasificacionEntity)

    @Query("DELETE FROM clasificacion")
    suspend fun deleteAll()
}