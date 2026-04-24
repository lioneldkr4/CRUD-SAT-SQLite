package com.sat.contribuyentes.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.sat.contribuyentes.db.SATDatabase
import com.sat.contribuyentes.domain.model.DomicilioFiscal
import com.sat.contribuyentes.domain.model.PersonaFisica
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PersonaFisicaRepository(private val database: SATDatabase) {

    fun getAllPersonasFisicas(): Flow<List<PersonaFisica>> {
        return database.personaFisicaQueries
            .getAllPersonasFisicas()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list -> list.map { it.toDomain() } }
    }

    fun getPersonaFisicaById(id: Long): Flow<PersonaFisica?> {
        return database.personaFisicaQueries
            .getPersonaFisicaById(id)
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)
            .map { it?.toDomain() }
    }

    suspend fun insertPersonaFisica(pf: PersonaFisica) = withContext(Dispatchers.IO) {
        database.personaFisicaQueries.insertPersonaFisica(
            curp = pf.curp,
            nombre = pf.nombre,
            fecha_nacimiento = pf.fechaNacimiento,
            correo = pf.correo,
            telefono = pf.telefono,
            codigo_postal = pf.domicilio.codigoPostal,
            estado_clave = pf.domicilio.estadoClave,
            municipio_id = pf.domicilio.municipioId,
            localidad = pf.domicilio.localidad,
            colonia = pf.domicilio.colonia,
            tipo_vialidad = pf.domicilio.tipoVialidad,
            nombre_vialidad = pf.domicilio.nombreVialidad,
            numero_exterior = pf.domicilio.numeroExterior,
            numero_interior = pf.domicilio.numeroInterior.ifBlank { null },
            entre_calle1 = pf.domicilio.entreCalle1.ifBlank { null },
            entre_calle2 = pf.domicilio.entreCalle2.ifBlank { null },
            referencia_adicional = pf.domicilio.referenciaAdicional.ifBlank { null },
            caracteristicas_domicilio = pf.domicilio.caracteristicasDomicilio.ifBlank { null },
            actividad_economica = pf.actividadEconomica,
            regimen_fiscal = pf.regimenFiscal
        )
    }

    suspend fun updatePersonaFisica(pf: PersonaFisica) = withContext(Dispatchers.IO) {
        database.personaFisicaQueries.updatePersonaFisica(
            curp = pf.curp,
            nombre = pf.nombre,
            fecha_nacimiento = pf.fechaNacimiento,
            correo = pf.correo,
            telefono = pf.telefono,
            codigo_postal = pf.domicilio.codigoPostal,
            estado_clave = pf.domicilio.estadoClave,
            municipio_id = pf.domicilio.municipioId,
            localidad = pf.domicilio.localidad,
            colonia = pf.domicilio.colonia,
            tipo_vialidad = pf.domicilio.tipoVialidad,
            nombre_vialidad = pf.domicilio.nombreVialidad,
            numero_exterior = pf.domicilio.numeroExterior,
            numero_interior = pf.domicilio.numeroInterior.ifBlank { null },
            entre_calle1 = pf.domicilio.entreCalle1.ifBlank { null },
            entre_calle2 = pf.domicilio.entreCalle2.ifBlank { null },
            referencia_adicional = pf.domicilio.referenciaAdicional.ifBlank { null },
            caracteristicas_domicilio = pf.domicilio.caracteristicasDomicilio.ifBlank { null },
            actividad_economica = pf.actividadEconomica,
            regimen_fiscal = pf.regimenFiscal,
            id = pf.id
        )
    }

    suspend fun deletePersonaFisica(id: Long) = withContext(Dispatchers.IO) {
        database.personaFisicaQueries.deletePersonaFisica(id)
    }

    fun searchPersonasFisicas(query: String): Flow<List<PersonaFisica>> {
        val pattern = "%$query%"
        return database.personaFisicaQueries
            .searchPersonaFisica(pattern, pattern)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list -> list.map { it.toDomain() } }
    }

    private fun com.sat.contribuyentes.db.PersonaFisica.toDomain(): PersonaFisica {
        return PersonaFisica(
            id = id,
            curp = curp,
            nombre = nombre,
            fechaNacimiento = fecha_nacimiento,
            correo = correo,
            telefono = telefono,
            domicilio = DomicilioFiscal(
                codigoPostal = codigo_postal,
                estadoClave = estado_clave,
                municipioId = municipio_id,
                localidad = localidad,
                colonia = colonia,
                tipoVialidad = tipo_vialidad,
                nombreVialidad = nombre_vialidad,
                numeroExterior = numero_exterior,
                numeroInterior = numero_interior ?: "",
                entreCalle1 = entre_calle1 ?: "",
                entreCalle2 = entre_calle2 ?: "",
                referenciaAdicional = referencia_adicional ?: "",
                caracteristicasDomicilio = caracteristicas_domicilio ?: ""
            ),
            actividadEconomica = actividad_economica,
            regimenFiscal = regimen_fiscal
        )
    }
}
