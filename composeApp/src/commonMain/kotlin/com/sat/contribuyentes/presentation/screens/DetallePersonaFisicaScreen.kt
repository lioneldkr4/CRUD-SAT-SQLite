package com.sat.contribuyentes.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sat.contribuyentes.presentation.components.DetalleRow
import com.sat.contribuyentes.presentation.components.SectionTitle
import com.sat.contribuyentes.presentation.navigation.Screen
import com.sat.contribuyentes.presentation.viewmodel.PersonaFisicaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallePersonaFisicaScreen(
    viewModel: PersonaFisicaViewModel,
    id: Long,
    navController: NavController
) {
    val lista by viewModel.personasFisicas.collectAsState()
    val pf = lista.find { it.id == id }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle Persona Fisica") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.FormPersonaFisica.createRoute(id))
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar")
                    }
                }
            )
        }
    ) { padding ->
        if (pf == null) {
            Column(
                modifier = Modifier.fillMaxSize().padding(padding),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Registro no encontrado", modifier = Modifier.padding(16.dp))
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                SectionTitle("Identificacion")
                DetalleRow("CURP", pf.curp)
                DetalleRow("Nombre(s) y Apellidos", pf.nombre)
                DetalleRow("Fecha de Nacimiento", pf.fechaNacimiento)
                DetalleRow("Correo Electronico", pf.correo)
                DetalleRow("Telefono", pf.telefono)

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                SectionTitle("Domicilio Fiscal")
                DetalleRow("Codigo Postal", pf.domicilio.codigoPostal)
                DetalleRow("Estado", pf.domicilio.estadoClave)
                DetalleRow("Localidad", pf.domicilio.localidad)
                DetalleRow("Colonia", pf.domicilio.colonia)
                DetalleRow(
                    "Direccion",
                    "${pf.domicilio.tipoVialidad} ${pf.domicilio.nombreVialidad} " +
                        "No. ${pf.domicilio.numeroExterior}" +
                        (if (pf.domicilio.numeroInterior.isNotBlank()) " Int. ${pf.domicilio.numeroInterior}" else "")
                )
                if (pf.domicilio.entreCalle1.isNotBlank()) {
                    DetalleRow(
                        "Entre Calles",
                        "${pf.domicilio.entreCalle1} y ${pf.domicilio.entreCalle2}"
                    )
                }
                if (pf.domicilio.referenciaAdicional.isNotBlank()) {
                    DetalleRow("Referencia", pf.domicilio.referenciaAdicional)
                }
                if (pf.domicilio.caracteristicasDomicilio.isNotBlank()) {
                    DetalleRow("Caracteristicas", pf.domicilio.caracteristicasDomicilio)
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                SectionTitle("Actividad Fiscal")
                DetalleRow("Actividad Economica", pf.actividadEconomica)
                DetalleRow("Regimen Fiscal", pf.regimenFiscal)

                androidx.compose.foundation.layout.Spacer(Modifier.padding(bottom = 24.dp))
            }
        }
    }
}
