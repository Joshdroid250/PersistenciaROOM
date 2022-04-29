package ni.edu.uca.persistenciaroom.fragments.actualizar.lista
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.persistenciaroom.bd.dao.entidades.UserEntity
import ni.edu.uca.persistenciaroom.databinding.ListaUsuarioBinding

class UserAdapter  :
    RecyclerView.Adapter<UserAdapter.UsuarioHolder>() {

        private var listadoUsuario = emptyList<UserEntity>()
        override fun onCreateViewHolder(parent: ViewGroup, viewType:
        Int): UsuarioHolder {
            val binding =

                ListaUsuarioBinding.inflate(LayoutInflater.from(parent.context),
                    parent, false)
            return UsuarioHolder(binding)
        }
        override fun onBindViewHolder(holder: UsuarioHolder,
                                      position: Int) {
            holder.bind(
                listadoUsuario[position]
            )
        }
        override fun getItemCount(): Int = listadoUsuario.size
        fun setData(users: List<UserEntity>) {
            this.listadoUsuario = users
            notifyDataSetChanged()
        }
        inner class UsuarioHolder(val binding: ListaUsuarioBinding)
            :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(usuario: UserEntity) {
                with(binding) {
                    TvIdUsuario.text = usuario.idUsuario.toString()
                    TvUserName.text = usuario.nombreUsuario
                    TvNombres.text = usuario.nombres
                    TvApellidos.text = usuario.apellidos
                    ClFila.setOnClickListener {
                        val action =
                            ListaFragmentDirections.listaActualizar(usuario)
                        it.findNavController().navigate(action)
                    }
                }
            }
        }
    }