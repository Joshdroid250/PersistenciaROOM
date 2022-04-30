package ni.edu.uca.persistenciaroom.fragments.actualizar.lista

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ni.edu.uca.persistenciaroom.R
import ni.edu.uca.persistenciaroom.bd.dao.viewmodels.ClasificacionViewModel
import ni.edu.uca.persistenciaroom.databinding.FragmentListClasificacionBinding


class listClasificacion : Fragment() {
    lateinit var cBinding: FragmentListClasificacionBinding

    private lateinit var viewModel: ClasificacionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cBinding = FragmentListClasificacionBinding.inflate(inflater, container, false)

        val adapter = ClasificacionAdapter()
        val recycleView = cBinding.RwClasificacion

        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(ClasificacionViewModel::class.java)
        viewModel.listaC.observe(viewLifecycleOwner, Observer {clasif -> adapter.setData(clasif) })

        setHasOptionsMenu(true)

        return cBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews(){
        with(cBinding){
            BtnAddClasificacion.setOnClickListener {
                it.findNavController().navigate(R.id.action_listClasificacion_to_addClasificacion)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuEliminar) {
            eliminarTodo()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun eliminarTodo() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModel.eliminarTodo()
            Toast.makeText(
                requireContext(),
                "Registros eliminados satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setTitle("Eliminando todos los registro")
        alerta.setMessage("¿Esta seguro de eliminar los registros?")
        alerta.create().show()
    }
}