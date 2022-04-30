package ni.edu.uca.persistenciaroom.bd.dao.entidades

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName ="clasificacion")
data class ClasificacionEntity(
    @PrimaryKey(autoGenerate = true)
    val idClasificacion: Int,

    @ColumnInfo(name = "abreviacion")
    val abreviacion: String,

    @ColumnInfo(name = "nombre")
    val nombre: String,

    @ColumnInfo(name = "activo")
    val activo: Boolean
): Parcelable
