package com.sat.contribuyentes.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.sat.contribuyentes.db.SATDatabase
import com.sat.contribuyentes.domain.model.Estado
import com.sat.contribuyentes.domain.model.Municipio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatalogoRepository(private val database: SATDatabase) {

    fun getAllEstados(): Flow<List<Estado>> {
        return database.estadoQueries
            .getAllEstados()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map { Estado(id = it.id, clave = it.clave, nombre = it.nombre) }
            }
    }

    fun getMunicipiosByEstado(estadoClave: String): Flow<List<Municipio>> {
        return database.municipioQueries
            .getMunicipiosByEstado(estadoClave)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    Municipio(
                        id = it.id,
                        clave = it.clave,
                        nombre = it.nombre,
                        estadoClave = it.estado_clave
                    )
                }
            }
    }
}
