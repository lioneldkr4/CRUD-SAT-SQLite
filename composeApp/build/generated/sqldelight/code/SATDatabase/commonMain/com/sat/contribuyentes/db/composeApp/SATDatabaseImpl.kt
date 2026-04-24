package com.sat.contribuyentes.db.composeApp

import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.sat.contribuyentes.db.EstadoQueries
import com.sat.contribuyentes.db.MunicipioQueries
import com.sat.contribuyentes.db.PersonaFisicaQueries
import com.sat.contribuyentes.db.PersonaMoralQueries
import com.sat.contribuyentes.db.SATDatabase
import kotlin.Long
import kotlin.Unit
import kotlin.reflect.KClass

internal val KClass<SATDatabase>.schema: SqlSchema<QueryResult.Value<Unit>>
  get() = SATDatabaseImpl.Schema

internal fun KClass<SATDatabase>.newInstance(driver: SqlDriver): SATDatabase =
    SATDatabaseImpl(driver)

private class SATDatabaseImpl(
  driver: SqlDriver,
) : TransacterImpl(driver), SATDatabase {
  override val estadoQueries: EstadoQueries = EstadoQueries(driver)

  override val municipioQueries: MunicipioQueries = MunicipioQueries(driver)

  override val personaFisicaQueries: PersonaFisicaQueries = PersonaFisicaQueries(driver)

  override val personaMoralQueries: PersonaMoralQueries = PersonaMoralQueries(driver)

  public object Schema : SqlSchema<QueryResult.Value<Unit>> {
    override val version: Long
      get() = 1

    override fun create(driver: SqlDriver): QueryResult.Value<Unit> {
      driver.execute(null, """
          |CREATE TABLE Estado (
          |    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
          |    clave TEXT NOT NULL UNIQUE,
          |    nombre TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE Municipio (
          |    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
          |    clave TEXT NOT NULL,
          |    nombre TEXT NOT NULL,
          |    estado_clave TEXT NOT NULL,
          |    FOREIGN KEY (estado_clave) REFERENCES Estado(clave)
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE PersonaFisica (
          |    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
          |    curp TEXT NOT NULL UNIQUE,
          |    nombre TEXT NOT NULL,
          |    fecha_nacimiento TEXT NOT NULL,
          |    correo TEXT NOT NULL,
          |    telefono TEXT NOT NULL,
          |    -- Domicilio Fiscal
          |    codigo_postal TEXT NOT NULL,
          |    estado_clave TEXT NOT NULL,
          |    municipio_id INTEGER NOT NULL,
          |    localidad TEXT NOT NULL,
          |    colonia TEXT NOT NULL,
          |    tipo_vialidad TEXT NOT NULL,
          |    nombre_vialidad TEXT NOT NULL,
          |    numero_exterior TEXT NOT NULL,
          |    numero_interior TEXT,
          |    entre_calle1 TEXT,
          |    entre_calle2 TEXT,
          |    referencia_adicional TEXT,
          |    caracteristicas_domicilio TEXT,
          |    -- Actividad
          |    actividad_economica TEXT NOT NULL,
          |    regimen_fiscal TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE PersonaMoral (
          |    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
          |    denominacion_razon_social TEXT NOT NULL,
          |    fecha_constitucion TEXT NOT NULL,
          |    rfc_representante_legal TEXT NOT NULL,
          |    rfc_socios TEXT NOT NULL,
          |    numero_escritura TEXT NOT NULL,
          |    -- Domicilio Fiscal
          |    codigo_postal TEXT NOT NULL,
          |    estado_clave TEXT NOT NULL,
          |    municipio_id INTEGER NOT NULL,
          |    localidad TEXT NOT NULL,
          |    colonia TEXT NOT NULL,
          |    tipo_vialidad TEXT NOT NULL,
          |    nombre_vialidad TEXT NOT NULL,
          |    numero_exterior TEXT NOT NULL,
          |    numero_interior TEXT,
          |    entre_calle1 TEXT,
          |    entre_calle2 TEXT,
          |    referencia_adicional TEXT,
          |    caracteristicas_domicilio TEXT,
          |    -- Actividad
          |    regimen_capital TEXT NOT NULL,
          |    actividad_economica TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      return QueryResult.Unit
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Long,
      newVersion: Long,
      vararg callbacks: AfterVersion,
    ): QueryResult.Value<Unit> = QueryResult.Unit
  }
}
