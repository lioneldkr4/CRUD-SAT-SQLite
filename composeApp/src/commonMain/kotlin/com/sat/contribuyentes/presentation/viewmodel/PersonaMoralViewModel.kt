package com.sat.contribuyentes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sat.contribuyentes.data.repository.CatalogoRepository
import com.sat.contribuyentes.data.repository.PersonaMoralRepository
import com.sat.contribuyentes.domain.model.Catalogos
import com.sat.contribuyentes.domain.model.DomicilioFiscal
import com.sat.contribuyentes.domain.model.Estado
import com.sat.contribuyentes.domain.model.Municipio
import com.sat.contribuyentes.domain.model.PersonaMoral
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PersonaMoralFormState(
    val denominacionRazonSocial: String = "",
    val fechaConstitucion: String = "",
    val rfcRepresentanteLegal: String = "",
    val rfcSocios: String = "",
    val numeroEscritura: String = "",
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
    // Clasificacion
    val regimenCapital: String = "",
    val actividadEconomica: String = "",
    // Edit mode
    val editingId: Long? = null,
    val errorMessage: String? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class PersonaMoralViewModel(
    private val repository: PersonaMoralRepository,
    private val catalogoRepository: CatalogoRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val personasMorales: StateFlow<List<PersonaMoral>> = _searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) repository.getAllPersonasMorales()
            else repository.searchPersonasMorales(query)
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

    private val _formState = MutableStateFlow(PersonaMoralFormState())
    val formState = _formState.asStateFlow()

    val tiposVialidad = Catalogos.TIPOS_VIALIDAD
    val actividadesEconomicas = Catalogos.ACTIVIDADES_ECONOMICAS
    val regimenesCapital = Catalogos.REGIMENES_CAPITAL

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

    fun updateField(update: PersonaMoralFormState.() -> PersonaMoralFormState) {
        _formState.update { it.update() }
    }

    fun prepareForEdit(pm: PersonaMoral, estados: List<Estado>, municipios: List<Municipio>) {
        val estado = estados.find { it.clave == pm.domicilio.estadoClave }
        val municipio = municipios.find { it.id == pm.domicilio.municipioId }
        if (estado != null) _selectedEstadoClave.value = estado.clave
        _formState.value = PersonaMoralFormState(
            denominacionRazonSocial = pm.denominacionRazonSocial,
            fechaConstitucion = pm.fechaConstitucion,
            rfcRepresentanteLegal = pm.rfcRepresentanteLegal,
            rfcSocios = pm.rfcSocios,
            numeroEscritura = pm.numeroEscritura,
            codigoPostal = pm.domicilio.codigoPostal,
            estadoSeleccionado = estado,
            municipioSeleccionado = municipio,
            localidad = pm.domicilio.localidad,
            colonia = pm.domicilio.colonia,
            tipoVialidad = pm.domicilio.tipoVialidad,
            nombreVialidad = pm.domicilio.nombreVialidad,
            numeroExterior = pm.domicilio.numeroExterior,
            numeroInterior = pm.domicilio.numeroInterior,
            entreCalle1 = pm.domicilio.entreCalle1,
            entreCalle2 = pm.domicilio.entreCalle2,
            referenciaAdicional = pm.domicilio.referenciaAdicional,
            caracteristicasDomicilio = pm.domicilio.caracteristicasDomicilio,
            regimenCapital = pm.regimenCapital,
            actividadEconomica = pm.actividadEconomica,
            editingId = pm.id
        )
    }

    fun resetForm() {
        _formState.value = PersonaMoralFormState()
        _selectedEstadoClave.value = ""
    }

    fun savePersonaMoral() {
        val state = _formState.value
        if (!validateForm(state)) return

        val pm = PersonaMoral(
            id = state.editingId ?: 0L,
            denominacionRazonSocial = state.denominacionRazonSocial.uppercase().trim(),
            fechaConstitucion = state.fechaConstitucion,
            rfcRepresentanteLegal = state.rfcRepresentanteLegal.uppercase().trim(),
            rfcSocios = state.rfcSocios.uppercase().trim(),
            numeroEscritura = state.numeroEscritura.uppercase().trim(),
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
            regimenCapital = state.regimenCapital,
            actividadEconomica = state.actividadEconomica
        )

        viewModelScope.launch {
            try {
                if (state.editingId != null) {
                    repository.updatePersonaMoral(pm)
                } else {
                    repository.insertPersonaMoral(pm)
                }
                resetForm()
            } catch (e: Exception) {
                _formState.update { it.copy(errorMessage = "Error al guardar: ${e.message}") }
            }
        }
    }

    fun deletePersonaMoral(id: Long) {
        viewModelScope.launch {
            repository.deletePersonaMoral(id)
        }
    }

    fun clearError() {
        _formState.update { it.copy(errorMessage = null) }
    }

    private fun validateForm(state: PersonaMoralFormState): Boolean {
        return when {
            state.denominacionRazonSocial.isBlank() -> {
                _formState.update { it.copy(errorMessage = "La razon social es requerida") }
                false
            }
            state.fechaConstitucion.isBlank() -> {
                _formState.update { it.copy(errorMessage = "La fecha de constitucion es requerida") }
                false
            }
            state.rfcRepresentanteLegal.length !in 12..13 -> {
                _formState.update { it.copy(errorMessage = "RFC del representante legal invalido (12-13 caracteres)") }
                false
            }
            state.rfcSocios.isBlank() -> {
                _formState.update { it.copy(errorMessage = "El RFC de socios/accionistas es requerido") }
                false
            }
            state.numeroEscritura.isBlank() -> {
                _formState.update { it.copy(errorMessage = "El numero de escritura es requerido") }
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
            state.regimenCapital.isBlank() -> {
                _formState.update { it.copy(errorMessage = "Seleccione el regimen de capital") }
                false
            }
            state.actividadEconomica.isBlank() -> {
                _formState.update { it.copy(errorMessage = "Seleccione una actividad economica") }
                false
            }
            else -> true
        }
    }
}
