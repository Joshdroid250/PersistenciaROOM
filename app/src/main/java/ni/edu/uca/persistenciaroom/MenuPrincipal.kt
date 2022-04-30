package ni.edu.uca.persistenciaroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import ni.edu.uca.persistenciaroom.R
import ni.edu.uca.persistenciaroom.databinding.FragmentMenuPrincipalBinding


class MenuPrincipal : Fragment() {

    lateinit var binding: FragmentMenuPrincipalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuPrincipalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.verGenero.setOnClickListener {
            it.findNavController().navigate(R.id.action_menuPrincipal_to_listGenero)
        }
        binding.verUser.setOnClickListener {
            it.findNavController().navigate(R.id.action_menuPrincipal_to_listaFragment)
        }
        binding.VerClasificacion.setOnClickListener {
            it.findNavController().navigate(R.id.action_menuPrincipal_to_listClasificacion)
        }
    }

}