package com.sat.contribuyentes.db

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class MunicipioQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getMunicipiosByEstado(estado_clave: String, mapper: (
    id: Long,
    clave: String,
    nombre: String,
    estado_clave: String,
  ) -> T): Query<T> = GetMunicipiosByEstadoQuery(estado_clave) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!
    )
  }

  public fun getMunicipiosByEstado(estado_clave: String): Query<Municipio> =
      getMunicipiosByEstado(estado_clave) { id, clave, nombre, estado_clave_ ->
    Municipio(
      id,
      clave,
      nombre,
      estado_clave_
    )
  }

  public fun <T : Any> getAllMunicipios(mapper: (
    id: Long,
    clave: String,
    nombre: String,
    estado_clave: String,
  ) -> T): Query<T> = Query(-1_703_172_947, arrayOf("Municipio"), driver, "Municipio.sq",
      "getAllMunicipios",
      "SELECT Municipio.id, Municipio.clave, Municipio.nombre, Municipio.estado_clave FROM Municipio ORDER BY nombre ASC") {
      cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!
    )
  }

  public fun getAllMunicipios(): Query<Municipio> = getAllMunicipios { id, clave, nombre,
      estado_clave ->
    Municipio(
      id,
      clave,
      nombre,
      estado_clave
    )
  }

  public fun insertMunicipio(
    clave: String,
    nombre: String,
    estado_clave: String,
  ) {
    driver.execute(140_823_544,
        """INSERT INTO Municipio(clave, nombre, estado_clave) VALUES (?, ?, ?)""", 3) {
          bindString(0, clave)
          bindString(1, nombre)
          bindString(2, estado_clave)
        }
    notifyQueries(140_823_544) { emit ->
      emit("Municipio")
    }
  }

  private inner class GetMunicipiosByEstadoQuery<out T : Any>(
    public val estado_clave: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Municipio", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Municipio", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-173_625_699,
        """SELECT Municipio.id, Municipio.clave, Municipio.nombre, Municipio.estado_clave FROM Municipio WHERE estado_clave = ? ORDER BY nombre ASC""",
        mapper, 1) {
      bindString(0, estado_clave)
    }

    override fun toString(): String = "Municipio.sq:getMunicipiosByEstado"
  }
}
