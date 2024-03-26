package mx.edu.utttt.loginfire.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.edu.utttt.loginfire.model.UserRepository

class MainViewModel {
    private val user = UserRepository()

    // Valores para Nombre
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    // Valores para Apellidos
    private val _lastname = MutableLiveData<String>()
    val lastname: LiveData<String> = _lastname

    // Valores para Dirección
    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    // Valores para Municipio
    private val _municipality = MutableLiveData<String>()
    val municipality: LiveData<String> = _municipality

    // Valores para Código Postal
    private val _postalCode = MutableLiveData<String>()
    val postalCode: LiveData<String> = _postalCode

    // Valores para Código Sexo
    private val _sex = MutableLiveData<String>()
    val sex: LiveData<String> = _sex

    // Valores para Código Clave de elector
    private val _electorKey = MutableLiveData<String>()
    val electorKey: LiveData<String> = _electorKey

    // Valores para CURP
    private val _curp = MutableLiveData<String>()
    val curp: LiveData<String> = _curp

    // Valores para Fecha de nacimiento
    private val _birthdate = MutableLiveData<String>()
    val birthdate: LiveData<String> = _birthdate

    fun obtainUserInfo(userId: String?, context: Context) {
        val db = user.db
        db.collection("users").document(userId ?: "")
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    Log.d("Firestore", "Documento: ${document.data}")
                    // Actualiza los LiveData con los datos del documento
                    _name.value = document.getString("name")
                    _lastname.value = document.getString("lastname")
                    _address.value = document.getString("address")
                    _municipality.value = document.getString("municipality")
                    _postalCode.value = document.getString("postalCode")
                    _sex.value = document.getString("sex")
                    _electorKey.value = document.getString("electorKey")
                    _curp.value = document.getString("curp")
                    _birthdate.value = document.getString("birthdate")
                } else {
                    Toast.makeText(context, "No se encontro el documento", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Error al obtener el documento", Toast.LENGTH_SHORT).show()
            }
    }

}