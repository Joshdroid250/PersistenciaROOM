package ni.edu.uca.persistenciaroom.fragments.actualizar

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_actualizar.*
import ni.edu.uca.persistenciaroom.R
import ni.edu.uca.persistenciaroom.bd.dao.entidades.GeneroEntity
import ni.edu.uca.persistenciaroom.bd.dao.viewmodels.GeneroViewModel
import ni.edu.uca.persistenciaroom.databinding.FragmentActualizarGeneroBinding

class ActualizarGenero : Fragment() {

    lateinit var ugBinding: FragmentActualizarGeneroBinding
    private val args by navArgs<ActualizarGeneroArgs>()
    private lateinit var viewModel: GeneroViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ugBinding =
            FragmentActualizarGeneroBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(GeneroViewModel::class.java)
        with(ugBinding){
            tvUpname.setText(args.currentGenero.nombre)
            BtnUpdateGenero.setOnClickListener {
                actualizarG()
            }
        }
        setHasOptionsMenu(true)
        return ugBinding.root
    }

    private fun actualizarG(){
        val name = ugBinding.tvUpname.text.toString()

        val gene = GeneroEntity(args.currentGenero.id_Genero, name, args.currentGenero.activo)
        viewModel.actualizarGenero(gene)
        Toast.makeText(requireContext(), "Registro actualizado",
            Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_actualizarGenero2_to_listGenero)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menuEliminar){
            eliminarGenero()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun eliminarGenero(){
        val alert = AlertDialog.Builder(requireContext())
        alert.setPositiveButton("Si"){_, _ ->
            viewModel.eliminarGenero(args.currentGenero)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_actualizarGenero2_to_listGenero)
        }
        alert.setNegativeButton("No"){ _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }
        alert.setTitle("Eliminando ${args.currentGenero.nombre}")
        alert.setMessage("¿Esta seguro de eliminar a ${args.currentGenero.nombre}?")
        alert.show()
    }
}