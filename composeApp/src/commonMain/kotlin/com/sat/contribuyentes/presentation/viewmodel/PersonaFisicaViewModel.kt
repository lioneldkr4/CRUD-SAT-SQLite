package com.sat.contribuyentes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sat.contribuyentes.data.repository.CatalogoRepository
import com.sat.contribuyentes.data.repository.PersonaFisicaRepository
import com.sat.contribuyentes.domain.model.Catalogos
import com.sat.contribuyentes.domain.model.DomicilioFiscal
import com.sat.contribuyentes.domain.model.Estado
import com.sat.contribuyentes.domain.model.Municipio
import com.sat.contribuyentes.domain.model.PersonaFisica
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PersonaFisicaFormState(
    val curp: String = "",
    val nombre: String = "",
    val fechaNacimiento: String = "",
    val correo: String = "",
    val telefono: String = "",
    // Domicilio
    val codigoPostal: String = "",
    val estadoSeleccionado: Estado? = null,
    val municipioSeleccionado: Municipio? = null,
    val localidad: String = "",
    val colonia: String = "",
    val tipoVialidad: String = "",
    val nombreVialidad: String = "",
    val numeroExterior: String = "",
    val numeroInterior: String = "",
    val entreCalle1: String = "",
    val entreCalle2: String = "",
    val referenciaAdicional: String = "",
    val caracteristicasDomicilio: String = "",
    // Actividad
    val actividadEconomica: String = "",
    val regimenFiscal: String = "",
    // Edit mode
    val editingId: Long? = null,
    val errorMessage: String? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class PersonaFisicaViewModel(
    private val repository: PersonaFisicaRepository,
    private val catalogoRepository: CatalogoRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val personasFisicas: StateFlow<List<PersonaFisica>> = _searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) repository.getAllPersonasFisicas()
            else repository.searchPersonasFisicas(query)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val estados: StateFlow<List<Estado>> = catalogoRepository.getAllEstados()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _selectedEstadoClave = MutableStateFlow("")

    val municipios: StateFlow<List<Municipio>> = _selectedEstadoClave
        .flatMapLatest { clave ->
            catalogoRepository.getMunicipiosByEstado(clave)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _formState = MutableStateFlow(PersonaFisicaFormState())
    val formState = _formState.asStateFlow()

    val tiposVialidad = Catalogos.TIPOS_VIALIDAD
    val actividadesEconomicas = Catalogos.ACTIVIDADES_ECONOMICAS
    val regimenesFiscales = Catalogos.REGIMENES_FISCALES_FISICAS

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onEstadoSelected(estado: Estado) {
        _selectedEstadoClave.value = estado.clave
        _formState.update { it.copy(estadoSeleccionado = estado, municipioSeleccionado = null) }
    }

    fun onMunicipioSelected(municipio: Municipio) {
        _formState.update { it.copy(municipioSeleccionado = municipio) }
    }

    fun updateField(update: PersonaFisicaFormState.() -> PersonaFisicaFormState) {
        _formState.update { it.update() }
    }

    fun prepareForEdit(pf: PersonaFisica, estados: List<Estado>, municipios: List<Municipio>) {
        val estado = estados.find { it.clave == pf.domicilio.estadoClave }
        val municipio = municipios.find { it.id == pf.domicilio.municipioId }
        if (estado != null) _selectedEstadoClave.value = estado.clave
        _formState.value = PersonaFisicaFormState(
            curp = pf.curp,
            nombre = pf.nombre,
            fechaNacimiento = pf.fechaNacimiento,
            correo = pf.correo,
            telefono = pf.telefono,
            codigoPostal = pf.domicilio.codigoPostal,
            estadoSeleccionado = estado,
            municipioSeleccionado = municipio,
            localidad = pf.domicilio.localidad,
            colonia = pf.domicilio.colonia,
            tipoVialidad = pf.domicilio.tipoVialidad,
            nombreVialidad = pf.domicilio.nombreVialidad,
            numeroExterior = pf.domicilio.numeroExterior,
            numeroInterior = pf.domicilio.numeroInterior,
            entreCalle1 = pf.domicilio.entreCalle1,
            entreCalle2 = pf.domicilio.entreCalle2,
            referenciaAdicional = pf.domicilio.referenciaAdicional,
            caracteristicasDomicilio = pf.domicilio.caracteristicasDomicilio,
            actividadEconomica = pf.actividadEconomica,
            regimenFiscal = pf.regimenFiscal,
            editingId = pf.id
        )
    }

    fun resetForm() {
        _formState.value = PersonaFisicaFormState()
        _selectedEstadoClave.value = ""
    }

    fun savePersonaFisica() {
        val state = _formState.value
        if (!validateForm(state)) return

        val pf = PersonaFisica(
            id = state.editingId ?: 0L,
            curp = state.curp.uppercase().trim(),
            nombre = state.nombre.uppercase().trim(),
            fechaNacimiento = state.fechaNacimiento,
            correo = state.correo.trim(),
            telefono = state.telefono.trim(),
            domicilio = DomicilioFiscal(
                codigoPostal = state.codigoPostal,
                estadoClave = state.estadoSeleccionado?.clave ?: "",
                municipioId = state.municipioSeleccionado?.id ?: 0L,
                localidad = state.localidad.uppercase().trim(),
                colonia = state.colonia.uppercase().trim(),
                tipoVialidad = state.tipoVialidad,
                nombreVialidad = state.nombreVialidad.uppercase().trim(),
                numeroExterior = state.numeroExterior.uppercase().trim(),
                numeroInterior = state.numeroInterior.uppercase().trim(),
                entreCalle1 = state.entreCalle1.uppercase().trim(),
                entreCalle2 = state.entreCalle2.uppercase().trim(),
                referenciaAdicional = state.referenciaAdicional.trim(),
                caracteristicasDomicilio = state.caracteristicasDomicilio.trim()
            ),
            actividadEconomica = state.actividadEconomica,
            regimenFiscal = state.regimenFiscal
        )

        viewModelScope.launch {
            try {
                if (state.editingId != null) {
                    repository.updatePersonaFisica(pf)
                } else {
                    repository.insertPersonaFisica(pf)
                }
                resetForm()
            } catch (e: Exception) {
                _formState.update { it.copy(errorMessage = "Error al guardar: ${e.message}") }
            }
        }
    }

    fun deletePersonaFisica(id: Long) {
        viewModelScope.launch {
            repository.deletePersonaFisica(id)
        }
    }

    fun clearError() {
        _formState.update { it.copy(errorMessage = null) }
    }

    private fun validateForm(state: PersonaFisicaFormState): Boolean {
        return when {
            state.curp.length != 18 -> {
                _formState.update { it.copy(errorMessage = "CURP debe tener 18 caracteres") }
                false
            }
            state.nombre.isBlank() -> {
                _formState.update { it.copy(errorMessage = "El nombre es requerido") }
                false
            }
            state.fechaNacimiento.isBlank() -> {
                _formState.update { it.copy(errorMessage = "La fecha de nacimiento es requerida") }
                false
            }
            state.correo.isBlank() || !state.correo.contains("@") -> {
                _formState.update { it.copy(errorMessage = "Correo electronico invalido") }
                false
            }
            state.telefono.length != 10 -> {
                _formState.update { it.copy(errorMessage = "Telefono debe tener 10 digitos") }
                false
            }
            state.codigoPostal.length != 5 -> {
                _formState.update { it.copy(errorMessage = "Codigo Postal debe tener 5 digitos") }
                false
            }
            state.estadoSeleccionado == null -> {
                _formState.update { it.copy(errorMessage = "Seleccione un estado") }
                false
            }
            state.municipioSeleccionado == null -> {
                _formState.update { it.copy(errorMessage = "Seleccione un municipio") }
                false
            }
            state.tipoVialidad.isBlank() -> {
                _formState.update { it.copy(errorMessage = "Seleccione el tipo de vialidad") }
                false
            }
            state.nombreVialidad.isBlank() -> {
                _formState.update { it.copy(errorMessage = "El nombre de vialidad es requerido") }
                false
            }
            state.numeroExterior.isBlank() -> {
                _formState.update { it.copy(errorMessage = "El numero exterior es requerido") }
                false
            }
            state.actividadEconomica.isBlank() -> {
                _formState.update { it.copy(errorMessage = "Seleccione una actividad economica") }
                false
            }
            state.regimenFiscal.isBlank() -> {
                _formState.update { it.copy(errorMessage = "Seleccione un regimen fiscal") }
                false
            }
            else -> true
        }
    }
}
