package ni.edu.uca.persistenciaroom.fragments.actualizar.lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.persistenciaroom.R
import ni.edu.uca.persistenciaroom.bd.dao.entidades.GeneroEntity
import ni.edu.uca.persistenciaroom.databinding.ListGeneroBinding

class GeneroAdapter: RecyclerView.Adapter<GeneroAdapter.AdapterGeneroHolder>()  {

    private var generoLista = emptyList<GeneroEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): AdapterGeneroHolder {
        val gbinding = ListGeneroBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AdapterGeneroHolder(gbinding)
    }

    override fun onBindViewHolder(holder: AdapterGeneroHolder, position: Int) {
        holder.bind(
            generoLista[position]
        )

    }
    override fun getItemCount(): Int = generoLista.size

    fun setData(genero: List<GeneroEntity>){
        this.generoLista = genero
        notifyDataSetChanged()
    }

    inner class AdapterGeneroHolder(val binding: ListGeneroBinding)
        :
        RecyclerView.ViewHolder(binding.root){
        fun bind(gen: GeneroEntity){
            with(binding){
                tvId.text = gen.id_Genero.toString()
                tvNombre.text = gen.nombre
                tvActivo.text = gen.activo.toString()

                cvGenero.setOnClickListener {
                    val opcion = listGeneroDirections.actionListaAactualizarGenero(gen)
                    it.findNavController().navigate(opcion)
                }
            }
        }
    }

}