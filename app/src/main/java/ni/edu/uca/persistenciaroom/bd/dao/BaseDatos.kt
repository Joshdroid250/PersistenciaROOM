package ni.edu.uca.persistenciaroom.bd.dao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ni.edu.uca.persistenciaroom.bd.dao.entidades.ClasificacionEntity
import ni.edu.uca.persistenciaroom.bd.dao.entidades.GeneroEntity
import ni.edu.uca.persistenciaroom.bd.dao.entidades.UserEntity


interface MainDataBaseProvider {
    fun usuariosDao(): UserDao
    fun generoDao():GeneroDao
    fun clasificacionDao(): ClasificacionDao
}
@Database(
    entities = [UserEntity::class, GeneroEntity::class, ClasificacionEntity::class],
    version = 1
)
abstract class MainBaseDatos : RoomDatabase(),
    MainDataBaseProvider {
    abstract override fun usuariosDao(): UserDao
    abstract  override  fun generoDao(): GeneroDao
    abstract override fun clasificacionDao(): ClasificacionDao
    companion object {
        @Volatile
        private var INSTANCE: MainBaseDatos? = null
        fun getDataBase(context: Context): MainBaseDatos {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainBaseDatos::class.java,
                        "usuario_main_db"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
