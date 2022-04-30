package ni.edu.uca.persistenciaroom.fragments.actualizar

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_clasificacion.*
import ni.edu.uca.persistenciaroom.R
import ni.edu.uca.persistenciaroom.bd.dao.entidades.ClasificacionEntity
import ni.edu.uca.persistenciaroom.bd.dao.viewmodels.ClasificacionViewModel
import ni.edu.uca.persistenciaroom.databinding.FragmentActualizarClasificacionBinding


class ActualizarClasificacion : Fragment() {
    lateinit var cUBinding: FragmentActualizarClasificacionBinding
    private val args by navArgs<ActualizarClasificacionArgs>()
    private lateinit var viewModel: ClasificacionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        cUBinding =
            FragmentActualizarClasificacionBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(ClasificacionViewModel::class.java)

        with(cUBinding){
            tvCUpAbreviacion.setText(args.currentClasificacion.abreviacion)
            tvCUpname.setText(args.currentClasificacion.nombre)

            BtnUpdateGenero.setOnClickListener {
                GuardarCambios()
            }
        }
        setHasOptionsMenu(true)
        return cUBinding.root
    }

    private fun GuardarCambios() {
        val abrev = cUBinding.tvCUpAbreviacion.text.toString()
        val nombre = cUBinding.tvCUpname.text.toString()

        val clasif =
            ClasificacionEntity(args.currentClasificacion.idClasificacion, abrev, nombre, args.currentClasificacion.activo)

        viewModel.actualizarClasificacion(clasif)
        Toast.makeText(requireContext(), "Registro actualizado",
            Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_actualizarClasificacion_to_listClasificacion)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater:
    MenuInflater
    ) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (item.itemId == R.id.menuEliminar) {
            eliminarClasificacion()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun eliminarClasificacion() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModel.eliminarClasificacion(args.currentClasificacion)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_actualizarClasificacion_to_listClasificacion)
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setTitle("Eliminando ${args.currentClasificacion.nombre}")
        alerta.setMessage("¿Esta seguro de eliminar a ${args.currentClasificacion.nombre}?")
        alerta.create().show()
    }
}