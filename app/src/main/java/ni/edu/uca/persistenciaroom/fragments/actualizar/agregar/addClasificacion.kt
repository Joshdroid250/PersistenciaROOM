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
import ni.edu.uca.persistenciaroom.bd.dao.entidades.ClasificacionEntity
import ni.edu.uca.persistenciaroom.bd.dao.viewmodels.ClasificacionViewModel
import ni.edu.uca.persistenciaroom.databinding.FragmentAddClasificacionBinding


class addClasificacion : Fragment() {
    private lateinit var cBinding: FragmentAddClasificacionBinding
    private lateinit var viewModel: ClasificacionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cBinding =
            FragmentAddClasificacionBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(ClasificacionViewModel::class.java)
        cBinding.btnSaveC.setOnClickListener {
            guardarRegistro()
        }
        return cBinding.root
    }

    private fun guardarRegistro() {
        val abrevia = cBinding.edtAbreviacion.text.toString()
        val name = cBinding.edtnombreC.text.toString()

        val clasificacion = ClasificacionEntity(0,abrevia,name,true)

        viewModel.agregarClasificacion(clasificacion)

        Toast.makeText(requireContext(), "Registro guardado",
            Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_addClasificacion_to_listClasificacion)
    }
}