package ni.edu.uca.persistenciaroom.bd.dao.repository

import androidx.lifecycle.LiveData
import ni.edu.uca.persistenciaroom.bd.dao.GeneroDao
import ni.edu.uca.persistenciaroom.bd.dao.entidades.GeneroEntity

class GeneroRepository(val dao: GeneroDao) {
    val lista : LiveData<List<GeneroEntity>> =
        dao.getAllRealData()
    suspend fun addGenero(genero: GeneroEntity){
        dao.insertGenero(genero)
    }
    suspend fun updateGenero(genero: GeneroEntity){
        dao.updateGenero(genero)
    }
    suspend fun deleteGenero(genero: GeneroEntity)
    {
        dao.deleteGenero(genero)

    }
    suspend fun deleteAll(){
        dao.deleteAll()
    }
}