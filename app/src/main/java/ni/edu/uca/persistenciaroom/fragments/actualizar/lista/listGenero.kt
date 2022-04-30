package ni.edu.uca.persistenciaroom.fragments.actualizar.lista

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.persistenciaroom.R
import ni.edu.uca.persistenciaroom.bd.dao.GeneroDao
import ni.edu.uca.persistenciaroom.bd.dao.MainBaseDatos
import ni.edu.uca.persistenciaroom.bd.dao.viewmodels.GeneroViewModel

import ni.edu.uca.persistenciaroom.databinding.FragmentListGeneroBinding


class listGenero : Fragment() {


    lateinit var binding: FragmentListGeneroBinding
    private lateinit var viewModel : GeneroViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListGeneroBinding.inflate(layoutInflater)
        val adapter = GeneroAdapter()
        val recyclerView = binding.RwGenero
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel = ViewModelProvider(this).get(GeneroViewModel::class.java)
        viewModel.listaG.observe(viewLifecycleOwner, Observer
        {gene->
            adapter.setData(gene)

        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState:
    Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()

    }

    private fun setupViews() {
        with(binding) {
            BtnAddGenero.setOnClickListener {

                it.findNavController().navigate(R.id.action_addGenero)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if(item.itemId == R.id.menuEliminar){
            eliminarAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun eliminarAll(){
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si"){ _, _ ->
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

