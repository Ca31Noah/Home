package ec.edu.espoch.arquitectura

import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    private lateinit var etLinearUsuario: EditText
    private lateinit var etLinearLastName: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var checkboxCarro: CheckBox
    private lateinit var checkboxMoto: CheckBox
    private lateinit var checkboxTriciclo: CheckBox
    private lateinit var btnIngresar: Button
    private lateinit var tvResult: TextView
    private lateinit var spinnerRangoEdad: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        etLinearUsuario = view.findViewById(R.id.etLinearUsuario)
        etLinearLastName = view.findViewById(R.id.etLinearLastName)
        radioGroup = view.findViewById(R.id.radioGroup)
        checkboxCarro = view.findViewById(R.id.checkbox_carro)
        checkboxMoto = view.findViewById(R.id.checkbox_moto)
        checkboxTriciclo = view.findViewById(R.id.checkbox_triciclo)
        spinnerRangoEdad = view.findViewById(R.id.fromspi)
        btnIngresar = view.findViewById(R.id.bLinearIngresar)
        tvResult = view.findViewById(R.id.tvResult)

        btnIngresar.setOnClickListener {
            val usuario = etLinearUsuario.text.toString()
            val apellido = etLinearLastName.text.toString()

            // Obtener el valor del RadioButton seleccionado
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val sexo = view.findViewById<RadioButton>(selectedRadioButtonId)?.text.toString()

            // Obtener los valores de los CheckBox seleccionados
            val vehiculos = mutableListOf<String>()
            if (checkboxCarro.isChecked) vehiculos.add("Carro")
            if (checkboxMoto.isChecked) vehiculos.add("Moto")
            if (checkboxTriciclo.isChecked) vehiculos.add("Triciclo")

            // Obtener el valor seleccionado del Spinner
            val rangoEdad = spinnerRangoEdad.selectedItem.toString()

            val resultado = "Nombre: $usuario\n" +
                    "Apellido: $apellido\n" +
                    "Sexo: $sexo\n" +
                    "Vehículos: ${vehiculos.joinToString(", ")}\n" +
                    "Rango de Edad: $rangoEdad"

            tvResult.text = resultado

            ingresarUsuario(usuario, apellido, sexo, vehiculos.joinToString(", "))
        }

        return view
    }

    private fun ingresarUsuario(nombre: String, apellido: String, sexo: String, vehiculos: String) {
        val admin = Conexion(requireContext(), "dbSistema", null, 1)
        val bd = admin.writableDatabase

        val valuesUsuario = ContentValues().apply {
            put("nombre", nombre)
        }
        val valuesApellido = ContentValues().apply {
            put("apellido", apellido)
        }
        val valuesSexo = ContentValues().apply {
            put("sexo", sexo)
        }
        val valuesVehiculo = ContentValues().apply {
            put("vehiculo", vehiculos)
        }

        val newRowIdUsuario = bd.insert("usuario", null, valuesUsuario)
        val newRowIdApellido = bd.insert("apellido", null, valuesApellido)
        val newRowIdSexo = bd.insert("sexo", null, valuesSexo)
        val newRowIdVehiculo = bd.insert("vehiculo", null, valuesVehiculo)

        if (newRowIdUsuario != -1L && newRowIdApellido != -1L && newRowIdSexo != -1L && newRowIdVehiculo != -1L) {
            Toast.makeText(requireContext(), "Datos guardados con éxito", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Error al guardar los datos", Toast.LENGTH_SHORT).show()
        }
    }
}
