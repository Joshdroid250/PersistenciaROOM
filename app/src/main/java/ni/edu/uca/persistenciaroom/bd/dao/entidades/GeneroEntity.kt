package ni.edu.uca.persistenciaroom.bd.dao.entidades
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName="Genero")
data class GeneroEntity(
    @PrimaryKey(autoGenerate = true)
    val id_Genero:Int,
    @ColumnInfo(name = "nombre")
    val nombre:String,
    @ColumnInfo(name = "activo")
    var activo:Boolean
):Parcelable
