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
import ni.edu.uca.persistenciaroom.bd.dao.entidades.UserEntity
import ni.edu.uca.persistenciaroom.bd.dao.viewmodels.UserViewModels
import ni.edu.uca.persistenciaroom.databinding.FragmentNuevoUsuarioBinding

class NuevoUsuario : Fragment() {
    lateinit var fBinding: FragmentNuevoUsuarioBinding
    private lateinit var viewModel: UserViewModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        fBinding =
            FragmentNuevoUsuarioBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(UserViewModels::class.java)
        fBinding.BtnGuardar.setOnClickListener {
            guardarRegistro()
        }
        return fBinding.root
    }
    private fun guardarRegistro() {
        //val baseDatos = MainBaseDatos.getDataBase(this)
        val userName = fBinding.TxtUserName.text.toString()
        val pass = fBinding.TxtPassword.text.toString()
        val nombres = fBinding.TxtNombres.text.toString()
        val apellidos = fBinding.TxtApellidos.text.toString()
        val email = fBinding.TxtEmail.text.toString()
        //Crear objeto
        val usuario = UserEntity(0, userName, pass, email,
            nombres, apellidos)
        //Agregar nuevo usuario
        viewModel.agregarUsuario(usuario)
        Toast.makeText(requireContext(), "Registro guardado",
            Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.ir_Lista)
    }
}
