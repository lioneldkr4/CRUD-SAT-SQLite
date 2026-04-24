package com.sat.contribuyentes.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.sat.contribuyentes.db.SATDatabase
import com.sat.contribuyentes.domain.model.DomicilioFiscal
import com.sat.contribuyentes.domain.model.PersonaMoral
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PersonaMoralRepository(private val database: SATDatabase) {

    fun getAllPersonasMorales(): Flow<List<PersonaMoral>> {
        return database.personaMoralQueries
            .getAllPersonasMorales()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list -> list.map { it.toDomain() } }
    }

    fun getPersonaMoralById(id: Long): Flow<PersonaMoral?> {
        return database.personaMoralQueries
            .getPersonaMoralById(id)
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)
            .map { it?.toDomain() }
    }

    suspend fun insertPersonaMoral(pm: PersonaMoral) = withContext(Dispatchers.IO) {
        database.personaMoralQueries.insertPersonaMoral(
            denominacion_razon_social = pm.denominacionRazonSocial,
            fecha_constitucion = pm.fechaConstitucion,
            rfc_representante_legal = pm.rfcRepresentanteLegal,
            rfc_socios = pm.rfcSocios,
            numero_escritura = pm.numeroEscritura,
            codigo_postal = pm.domicilio.codigoPostal,
            estado_clave = pm.domicilio.estadoClave,
            municipio_id = pm.domicilio.municipioId,
            localidad = pm.domicilio.localidad,
            colonia = pm.domicilio.colonia,
            tipo_vialidad = pm.domicilio.tipoVialidad,
            nombre_vialidad = pm.domicilio.nombreVialidad,
            numero_exterior = pm.domicilio.numeroExterior,
            numero_interior = pm.domicilio.numeroInterior.ifBlank { null },
            entre_calle1 = pm.domicilio.entreCalle1.ifBlank { null },
            entre_calle2 = pm.domicilio.entreCalle2.ifBlank { null },
            referencia_adicional = pm.domicilio.referenciaAdicional.ifBlank { null },
            caracteristicas_domicilio = pm.domicilio.caracteristicasDomicilio.ifBlank { null },
            regimen_capital = pm.regimenCapital,
            actividad_economica = pm.actividadEconomica
        )
    }

    suspend fun updatePersonaMoral(pm: PersonaMoral) = withContext(Dispatchers.IO) {
        database.personaMoralQueries.updatePersonaMoral(
            denominacion_razon_social = pm.denominacionRazonSocial,
            fecha_constitucion = pm.fechaConstitucion,
            rfc_representante_legal = pm.rfcRepresentanteLegal,
            rfc_socios = pm.rfcSocios,
            numero_escritura = pm.numeroEscritura,
            codigo_postal = pm.domicilio.codigoPostal,
            estado_clave = pm.domicilio.estadoClave,
            municipio_id = pm.domicilio.municipioId,
            localidad = pm.domicilio.localidad,
            colonia = pm.domicilio.colonia,
            tipo_vialidad = pm.domicilio.tipoVialidad,
            nombre_vialidad = pm.domicilio.nombreVialidad,
            numero_exterior = pm.domicilio.numeroExterior,
            numero_interior = pm.domicilio.numeroInterior.ifBlank { null },
            entre_calle1 = pm.domicilio.entreCalle1.ifBlank { null },
            entre_calle2 = pm.domicilio.entreCalle2.ifBlank { null },
            referencia_adicional = pm.domicilio.referenciaAdicional.ifBlank { null },
            caracteristicas_domicilio = pm.domicilio.caracteristicasDomicilio.ifBlank { null },
            regimen_capital = pm.regimenCapital,
            actividad_economica = pm.actividadEconomica,
            id = pm.id
        )
    }

    suspend fun deletePersonaMoral(id: Long) = withContext(Dispatchers.IO) {
        database.personaMoralQueries.deletePersonaMoral(id)
    }

    fun searchPersonasMorales(query: String): Flow<List<PersonaMoral>> {
        val pattern = "%$query%"
        return database.personaMoralQueries
            .searchPersonaMoral(pattern)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list -> list.map { it.toDomain() } }
    }

    private fun com.sat.contribuyentes.db.PersonaMoral.toDomain(): PersonaMoral {
        return PersonaMoral(
            id = id,
            denominacionRazonSocial = denominacion_razon_social,
            fechaConstitucion = fecha_constitucion,
            rfcRepresentanteLegal = rfc_representante_legal,
            rfcSocios = rfc_socios,
            numeroEscritura = numero_escritura,
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
            regimenCapital = regimen_capital,
            actividadEconomica = actividad_economica
        )
    }
}
