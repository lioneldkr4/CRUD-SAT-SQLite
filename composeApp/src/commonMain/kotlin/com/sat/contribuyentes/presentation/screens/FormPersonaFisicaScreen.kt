package com.sat.contribuyentes.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sat.contribuyentes.presentation.components.DomicilioFiscalSection
import com.sat.contribuyentes.presentation.components.ErrorBanner
import com.sat.contribuyentes.presentation.components.SatDropdown
import com.sat.contribuyentes.presentation.components.SatTextField
import com.sat.contribuyentes.presentation.components.SectionTitle
import com.sat.contribuyentes.presentation.viewmodel.PersonaFisicaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPersonaFisicaScreen(
    viewModel: PersonaFisicaViewModel,
    editingId: Long?,
    navController: NavController
) {
    val formState by viewModel.formState.collectAsState()
    val estados by viewModel.estados.collectAsState()
    val municipios by viewModel.municipios.collectAsState()
    val allPersonas by viewModel.personasFisicas.collectAsState()

    // Load record for editing
    LaunchedEffect(editingId, allPersonas) {
        if (editingId != null) {
            val pf = allPersonas.find { it.id == editingId }
            if (pf != null && formState.editingId == null) {
                viewModel.prepareForEdit(pf, estados, municipios)
            }
        } else {
            if (formState.editingId != null) viewModel.resetForm()
        }
    }

    // Navigate back on successful save (form resets)
    LaunchedEffect(formState.editingId, formState.curp) {
        // When form is reset after save, go back
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (editingId != null) "Editar Persona Fisica" else "Nueva Persona Fisica") },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.resetForm()
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            formState.errorMessage?.let { error ->
                ErrorBanner(message = error)
            }

            SectionTitle("Datos de Identificacion")

            SatTextField(
                label = "CURP",
                value = formState.curp,
                onValueChange = { viewModel.updateField { copy(curp = it.uppercase().take(18)) } },
                placeholder = "18 caracteres alfanumericos"
            )

            SatTextField(
                label = "Nombre(s) y Apellidos",
                value = formState.nombre,
                onValueChange = { viewModel.updateField { copy(nombre = it.uppercase()) } },
                placeholder = "En mayusculas"
            )

            SatTextField(
                label = "Fecha de Nacimiento",
                value = formState.fechaNacimiento,
                onValueChange = { viewModel.updateField { copy(fechaNacimiento = it) } },
                placeholder = "DD/MM/AAAA"
            )

            SatTextField(
                label = "Correo Electronico",
                value = formState.correo,
                onValueChange = { viewModel.updateField { copy(correo = it) } },
                placeholder = "ejemplo@correo.com"
            )

            SatTextField(
                label = "Telefono",
                value = formState.telefono,
                onValueChange = { viewModel.updateField { copy(telefono = it.take(10)) } },
                placeholder = "10 digitos"
            )

            DomicilioFiscalSection(
                codigoPostal = formState.codigoPostal,
                estadoSeleccionado = formState.estadoSeleccionado,
                municipioSeleccionado = formState.municipioSeleccionado,
                localidad = formState.localidad,
                colonia = formState.colonia,
                tipoVialidad = formState.tipoVialidad,
                nombreVialidad = formState.nombreVialidad,
                numeroExterior = formState.numeroExterior,
                numeroInterior = formState.numeroInterior,
                entreCalle1 = formState.entreCalle1,
                entreCalle2 = formState.entreCalle2,
                referenciaAdicional = formState.referenciaAdicional,
                caracteristicasDomicilio = formState.caracteristicasDomicilio,
                estados = estados,
                municipios = municipios,
                tiposVialidad = viewModel.tiposVialidad,
                onCodigoPostalChange = { viewModel.updateField { copy(codigoPostal = it.take(5)) } },
                onEstadoSelected = viewModel::onEstadoSelected,
                onMunicipioSelected = viewModel::onMunicipioSelected,
                onLocalidadChange = { viewModel.updateField { copy(localidad = it.uppercase()) } },
                onColoniaChange = { viewModel.updateField { copy(colonia = it.uppercase()) } },
                onTipoVialidadChange = { viewModel.updateField { copy(tipoVialidad = it) } },
                onNombreVialidadChange = { viewModel.updateField { copy(nombreVialidad = it.uppercase()) } },
                onNumeroExteriorChange = { viewModel.updateField { copy(numeroExterior = it.uppercase()) } },
                onNumeroInteriorChange = { viewModel.updateField { copy(numeroInterior = it.uppercase()) } },
                onEntreCalle1Change = { viewModel.updateField { copy(entreCalle1 = it.uppercase()) } },
                onEntreCalle2Change = { viewModel.updateField { copy(entreCalle2 = it.uppercase()) } },
                onReferenciaAdicionalChange = { viewModel.updateField { copy(referenciaAdicional = it) } },
                onCaracteristicasDomicilioChange = { viewModel.updateField { copy(caracteristicasDomicilio = it) } }
            )

            SectionTitle("Actividad Fiscal")

            SatDropdown(
                label = "Actividad Economica",
                options = viewModel.actividadesEconomicas,
                selectedOption = formState.actividadEconomica.ifBlank { null },
                onOptionSelected = { viewModel.updateField { copy(actividadEconomica = it) } },
                optionLabel = { it }
            )

            SatDropdown(
                label = "Regimen Fiscal",
                options = viewModel.regimenesFiscales,
                selectedOption = formState.regimenFiscal.ifBlank { null },
                onOptionSelected = { viewModel.updateField { copy(regimenFiscal = it) } },
                optionLabel = { it }
            )

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.savePersonaFisica()
                    if (formState.errorMessage == null) {
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (editingId != null) "Actualizar" else "Guardar")
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}
