package ec.edu.espoch.arquitectura

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val listViewUsuarios = view.findViewById<ListView>(R.id.listViewUsuarios)
        val usuarios = obtenerUsuarios()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, usuarios)
        listViewUsuarios.adapter = adapter
        return view
    }

    private fun obtenerUsuarios(): List<String> {
        val admin = Conexion(requireContext(), "dbSistema", null, 1)
        val bd = admin.readableDatabase

        val usuarios = mutableListOf<String>()

        // Obtener nombres de usuario
        val cursorUsuario: Cursor = bd.rawQuery("SELECT nombre FROM usuario", null)
        if (cursorUsuario.moveToFirst()) {
            do {
                val nombre = cursorUsuario.getString(cursorUsuario.getColumnIndexOrThrow("nombre"))
                usuarios.add("Usuario: $nombre")
            } while (cursorUsuario.moveToNext())
        }
        cursorUsuario.close()

        // Obtener apellidos
        val cursorApellido: Cursor = bd.rawQuery("SELECT apellido FROM apellido", null)
        if (cursorApellido.moveToFirst()) {
            val apellido = cursorApellido.getString(cursorApellido.getColumnIndexOrThrow("apellido"))
            usuarios.add("Apellido: $apellido")
        }
        cursorApellido.close()

        // Obtener sexos
        val cursorSexo: Cursor = bd.rawQuery("SELECT sexo FROM sexo", null)
        if (cursorSexo.moveToFirst()) {
            val sexo = cursorSexo.getString(cursorSexo.getColumnIndexOrThrow("sexo"))
            usuarios.add("Sexo: $sexo")
        }
        cursorSexo.close()

        // Obtener vehículos
        val cursorVehiculo: Cursor = bd.rawQuery("SELECT vehiculo FROM vehiculo", null)
        if (cursorVehiculo.moveToFirst()) {
            val vehiculo = cursorVehiculo.getString(cursorVehiculo.getColumnIndexOrThrow("vehiculo"))
            usuarios.add("Vehículo: $vehiculo")
        }
        cursorVehiculo.close()

        bd.close()
        return usuarios.distinct() // Elimina elementos duplicados
    }
}
