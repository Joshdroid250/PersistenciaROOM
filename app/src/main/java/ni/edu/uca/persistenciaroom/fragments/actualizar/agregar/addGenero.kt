package ni.edu.uca.persistenciaroom.fragments.actualizar.agregar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ni.edu.uca.persistenciaroom.R
import ni.edu.uca.persistenciaroom.bd.dao.entidades.GeneroEntity
import ni.edu.uca.persistenciaroom.bd.dao.viewmodels.GeneroViewModel
import ni.edu.uca.persistenciaroom.databinding.FragmentAddGeneroBinding


class addGenero : Fragment() {

    lateinit var adbinding: FragmentAddGeneroBinding
    private lateinit var viewModel: GeneroViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adbinding = FragmentAddGeneroBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(GeneroViewModel::class.java)

        adbinding.btnSaveG.setOnClickListener {
            saveGenero()
        }
        return adbinding.root
    }
    private fun saveGenero(){
        val nombre = adbinding.edtGenero.text.toString()

        val genero = GeneroEntity(0, nombre, activo = true)

        viewModel.agregarGenero(genero)
        Toast.makeText(requireContext(), "Registro guardado",
            Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_retornarListGenero)
    }

}