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
import com.sat.contribuyentes.presentation.viewmodel.PersonaMoralViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPersonaMoralScreen(
    viewModel: PersonaMoralViewModel,
    editingId: Long?,
    navController: NavController
) {
    val formState by viewModel.formState.collectAsState()
    val estados by viewModel.estados.collectAsState()
    val municipios by viewModel.municipios.collectAsState()
    val allMorales by viewModel.personasMorales.collectAsState()

    LaunchedEffect(editingId, allMorales, estados, municipios) {
        if (editingId != null && estados.isNotEmpty()) {
            val pm = allMorales.find { it.id == editingId }
            if (pm != null && formState.editingId == null) {
                viewModel.prepareForEdit(pm, estados, municipios)
            } else if (pm != null && formState.editingId != null && formState.municipioSeleccionado == null) {
                viewModel.prepareForEdit(pm, estados, municipios)
            }
        } else if (editingId == null) {
            if (formState.editingId != null) viewModel.resetForm()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (editingId != null) "Editar Persona Moral" else "Nueva Persona Moral") },
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

            SectionTitle("Datos de la Empresa")

            SatTextField(
                label = "Denominacion o Razon Social",
                value = formState.denominacionRazonSocial,
                onValueChange = { viewModel.updateField { copy(denominacionRazonSocial = it.uppercase()) } },
                placeholder = "En mayusculas, validado por SE"
            )

            SatTextField(
                label = "Fecha de Constitucion",
                value = formState.fechaConstitucion,
                onValueChange = { viewModel.updateField { copy(fechaConstitucion = it) } },
                placeholder = "DD/MM/AAAA"
            )

            SatTextField(
                label = "RFC del Representante Legal",
                value = formState.rfcRepresentanteLegal,
                onValueChange = { viewModel.updateField { copy(rfcRepresentanteLegal = it.uppercase().take(13)) } },
                placeholder = "13 caracteres"
            )

            SatTextField(
                label = "RFC de Socios / Accionistas",
                value = formState.rfcSocios,
                onValueChange = { viewModel.updateField { copy(rfcSocios = it.uppercase()) } },
                placeholder = "Separados por coma si son varios"
            )

            SatTextField(
                label = "Numero de Escritura / Poliza",
                value = formState.numeroEscritura,
                onValueChange = { viewModel.updateField { copy(numeroEscritura = it.uppercase()) } }
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

            SectionTitle("Clasificacion Empresarial")

            SatDropdown(
                label = "Regimen de Capital",
                options = viewModel.regimenesCapital,
                selectedOption = formState.regimenCapital.ifBlank { null },
                onOptionSelected = { viewModel.updateField { copy(regimenCapital = it) } },
                optionLabel = { it }
            )

            SatDropdown(
                label = "Actividad Economica",
                options = viewModel.actividadesEconomicas,
                selectedOption = formState.actividadEconomica.ifBlank { null },
                onOptionSelected = { viewModel.updateField { copy(actividadEconomica = it) } },
                optionLabel = { it }
            )

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.savePersonaMoral()
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
