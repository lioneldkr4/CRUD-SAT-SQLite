package com.sat.contribuyentes.db

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class EstadoQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getAllEstados(mapper: (
    id: Long,
    clave: String,
    nombre: String,
  ) -> T): Query<T> = Query(-571_299_153, arrayOf("Estado"), driver, "Estado.sq", "getAllEstados",
      "SELECT Estado.id, Estado.clave, Estado.nombre FROM Estado ORDER BY nombre ASC") { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!
    )
  }

  public fun getAllEstados(): Query<Estado> = getAllEstados { id, clave, nombre ->
    Estado(
      id,
      clave,
      nombre
    )
  }

  public fun <T : Any> getEstadoByClave(clave: String, mapper: (
    id: Long,
    clave: String,
    nombre: String,
  ) -> T): Query<T> = GetEstadoByClaveQuery(clave) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!
    )
  }

  public fun getEstadoByClave(clave: String): Query<Estado> = getEstadoByClave(clave) { id, clave_,
      nombre ->
    Estado(
      id,
      clave_,
      nombre
    )
  }

  public fun insertEstado(clave: String, nombre: String) {
    driver.execute(1_869_464_594, """INSERT INTO Estado(clave, nombre) VALUES (?, ?)""", 2) {
          bindString(0, clave)
          bindString(1, nombre)
        }
    notifyQueries(1_869_464_594) { emit ->
      emit("Estado")
    }
  }

  private inner class GetEstadoByClaveQuery<out T : Any>(
    public val clave: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Estado", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Estado", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_550_274_361,
        """SELECT Estado.id, Estado.clave, Estado.nombre FROM Estado WHERE clave = ?""", mapper, 1)
        {
      bindString(0, clave)
    }

    override fun toString(): String = "Estado.sq:getEstadoByClave"
  }
}
