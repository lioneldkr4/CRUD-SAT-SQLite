package com.sat.contribuyentes.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.sat.contribuyentes.presentation.viewmodel.PersonaMoralViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallePersonaMoralScreen(
    viewModel: PersonaMoralViewModel,
    id: Long,
    navController: NavController
) {
    val lista by viewModel.personasMorales.collectAsState()
    val pm = lista.find { it.id == id }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle Persona Moral") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.FormPersonaMoral.createRoute(id))
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar")
                    }
                }
            )
        }
    ) { padding ->
        if (pm == null) {
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
                SectionTitle("Datos de la Empresa")
                DetalleRow("Denominacion / Razon Social", pm.denominacionRazonSocial)
                DetalleRow("Fecha de Constitucion", pm.fechaConstitucion)
                DetalleRow("RFC Representante Legal", pm.rfcRepresentanteLegal)
                DetalleRow("RFC Socios / Accionistas", pm.rfcSocios)
                DetalleRow("Numero de Escritura / Poliza", pm.numeroEscritura)

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                SectionTitle("Domicilio Fiscal")
                DetalleRow("Codigo Postal", pm.domicilio.codigoPostal)
                DetalleRow("Estado", pm.domicilio.estadoClave)
                DetalleRow("Localidad", pm.domicilio.localidad)
                DetalleRow("Colonia", pm.domicilio.colonia)
                DetalleRow(
                    "Direccion",
                    "${pm.domicilio.tipoVialidad} ${pm.domicilio.nombreVialidad} " +
                        "No. ${pm.domicilio.numeroExterior}" +
                        (if (pm.domicilio.numeroInterior.isNotBlank()) " Int. ${pm.domicilio.numeroInterior}" else "")
                )
                if (pm.domicilio.entreCalle1.isNotBlank()) {
                    DetalleRow(
                        "Entre Calles",
                        "${pm.domicilio.entreCalle1} y ${pm.domicilio.entreCalle2}"
                    )
                }
                if (pm.domicilio.referenciaAdicional.isNotBlank()) {
                    DetalleRow("Referencia", pm.domicilio.referenciaAdicional)
                }
                if (pm.domicilio.caracteristicasDomicilio.isNotBlank()) {
                    DetalleRow("Caracteristicas", pm.domicilio.caracteristicasDomicilio)
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                SectionTitle("Clasificacion Empresarial")
                DetalleRow("Regimen de Capital", pm.regimenCapital)
                DetalleRow("Actividad Economica", pm.actividadEconomica)

                Spacer(Modifier.padding(bottom = 24.dp))
            }
        }
    }
}
