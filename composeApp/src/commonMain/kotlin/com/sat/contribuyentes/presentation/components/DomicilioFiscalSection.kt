package com.sat.contribuyentes.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sat.contribuyentes.domain.model.Estado
import com.sat.contribuyentes.domain.model.Municipio

@Composable
fun DomicilioFiscalSection(
    // field values
    codigoPostal: String,
    estadoSeleccionado: Estado?,
    municipioSeleccionado: Municipio?,
    localidad: String,
    colonia: String,
    tipoVialidad: String,
    nombreVialidad: String,
    numeroExterior: String,
    numeroInterior: String,
    entreCalle1: String,
    entreCalle2: String,
    referenciaAdicional: String,
    caracteristicasDomicilio: String,
    // catalogs
    estados: List<Estado>,
    municipios: List<Municipio>,
    tiposVialidad: List<String>,
    // callbacks
    onCodigoPostalChange: (String) -> Unit,
    onEstadoSelected: (Estado) -> Unit,
    onMunicipioSelected: (Municipio) -> Unit,
    onLocalidadChange: (String) -> Unit,
    onColoniaChange: (String) -> Unit,
    onTipoVialidadChange: (String) -> Unit,
    onNombreVialidadChange: (String) -> Unit,
    onNumeroExteriorChange: (String) -> Unit,
    onNumeroInteriorChange: (String) -> Unit,
    onEntreCalle1Change: (String) -> Unit,
    onEntreCalle2Change: (String) -> Unit,
    onReferenciaAdicionalChange: (String) -> Unit,
    onCaracteristicasDomicilioChange: (String) -> Unit
) {
    SectionTitle(title = "Domicilio Fiscal")

    SatTextField(
        label = "Codigo Postal",
        value = codigoPostal,
        onValueChange = onCodigoPostalChange,
        placeholder = "00000"
    )

    SatDropdown(
        label = "Estado / Entidad",
        options = estados,
        selectedOption = estadoSeleccionado,
        onOptionSelected = onEstadoSelected,
        optionLabel = { it.nombre }
    )

    SatDropdown(
        label = "Municipio o Alcaldia",
        options = municipios,
        selectedOption = municipioSeleccionado,
        onOptionSelected = onMunicipioSelected,
        optionLabel = { it.nombre },
        enabled = estadoSeleccionado != null
    )

    SatTextField(
        label = "Localidad",
        value = localidad,
        onValueChange = onLocalidadChange
    )

    SatTextField(
        label = "Colonia",
        value = colonia,
        onValueChange = onColoniaChange
    )

    SatDropdown(
        label = "Tipo de Vialidad",
        options = tiposVialidad,
        selectedOption = tipoVialidad.ifBlank { null },
        onOptionSelected = onTipoVialidadChange,
        optionLabel = { it }
    )

    SatTextField(
        label = "Nombre de Vialidad",
        value = nombreVialidad,
        onValueChange = onNombreVialidadChange,
        placeholder = "Nombre de la calle"
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SatTextField(
            label = "Num. Exterior",
            value = numeroExterior,
            onValueChange = onNumeroExteriorChange,
            modifier = Modifier.weight(1f),
            placeholder = "S/N"
        )
        SatTextField(
            label = "Num. Interior",
            value = numeroInterior,
            onValueChange = onNumeroInteriorChange,
            modifier = Modifier.weight(1f),
            placeholder = "Opcional"
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SatTextField(
            label = "Entre Calle 1",
            value = entreCalle1,
            onValueChange = onEntreCalle1Change,
            modifier = Modifier.weight(1f)
        )
        SatTextField(
            label = "Entre Calle 2",
            value = entreCalle2,
            onValueChange = onEntreCalle2Change,
            modifier = Modifier.weight(1f)
        )
    }

    SatTextField(
        label = "Referencia Adicional",
        value = referenciaAdicional,
        onValueChange = onReferenciaAdicionalChange,
        placeholder = "Frente al parque, fachada roja..."
    )

    SatTextField(
        label = "Caracteristicas del Domicilio",
        value = caracteristicasDomicilio,
        onValueChange = onCaracteristicasDomicilioChange,
        singleLine = false,
        maxLines = 3,
        placeholder = "Descripcion fisica del edificio o casa"
    )
}
