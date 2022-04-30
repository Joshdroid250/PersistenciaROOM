package ni.edu.uca.persistenciaroom.fragments.actualizar.lista

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.persistenciaroom.bd.dao.entidades.ClasificacionEntity
import ni.edu.uca.persistenciaroom.databinding.ListClasificacionBinding

class ClasificacionAdapter:
    RecyclerView.Adapter<ClasificacionAdapter.ClasificacionHolder> (){
    private var listaClasificacion = emptyList<ClasificacionEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClasificacionHolder {
        val binding = ListClasificacionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClasificacionHolder(binding)
    }

    override fun onBindViewHolder(holder: ClasificacionHolder, position: Int) {
        holder.bind(
            listaClasificacion[position]
        )
    }

    override fun getItemCount(): Int = listaClasificacion.size

    fun setData(clasificaciones: List<ClasificacionEntity>){
        this.listaClasificacion = clasificaciones
        notifyDataSetChanged()
    }

    inner class ClasificacionHolder(val binding: ListClasificacionBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(clasificacion: ClasificacionEntity){
            with(binding){
                tvAbreviacion.text = clasificacion.abreviacion
                tvCId.text = clasificacion.idClasificacion.toString()
                tvCNombre.text = clasificacion.nombre

                cvClasificacion.setOnClickListener {
                    val action = listClasificacionDirections.actionListClasificacionToActualizarClasificacion(clasificacion)
                    it.findNavController().navigate(action)
                }
            }
        }
    }

}