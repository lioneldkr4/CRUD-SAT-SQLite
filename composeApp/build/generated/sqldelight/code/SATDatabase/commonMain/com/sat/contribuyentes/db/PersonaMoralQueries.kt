package com.sat.contribuyentes.db

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class PersonaMoralQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getAllPersonasMorales(mapper: (
    id: Long,
    denominacion_razon_social: String,
    fecha_constitucion: String,
    rfc_representante_legal: String,
    rfc_socios: String,
    numero_escritura: String,
    codigo_postal: String,
    estado_clave: String,
    municipio_id: Long,
    localidad: String,
    colonia: String,
    tipo_vialidad: String,
    nombre_vialidad: String,
    numero_exterior: String,
    numero_interior: String?,
    entre_calle1: String?,
    entre_calle2: String?,
    referencia_adicional: String?,
    caracteristicas_domicilio: String?,
    regimen_capital: String,
    actividad_economica: String,
  ) -> T): Query<T> = Query(-962_928_741, arrayOf("PersonaMoral"), driver, "PersonaMoral.sq",
      "getAllPersonasMorales",
      "SELECT PersonaMoral.id, PersonaMoral.denominacion_razon_social, PersonaMoral.fecha_constitucion, PersonaMoral.rfc_representante_legal, PersonaMoral.rfc_socios, PersonaMoral.numero_escritura, PersonaMoral.codigo_postal, PersonaMoral.estado_clave, PersonaMoral.municipio_id, PersonaMoral.localidad, PersonaMoral.colonia, PersonaMoral.tipo_vialidad, PersonaMoral.nombre_vialidad, PersonaMoral.numero_exterior, PersonaMoral.numero_interior, PersonaMoral.entre_calle1, PersonaMoral.entre_calle2, PersonaMoral.referencia_adicional, PersonaMoral.caracteristicas_domicilio, PersonaMoral.regimen_capital, PersonaMoral.actividad_economica FROM PersonaMoral ORDER BY denominacion_razon_social ASC") {
      cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getLong(8)!!,
      cursor.getString(9)!!,
      cursor.getString(10)!!,
      cursor.getString(11)!!,
      cursor.getString(12)!!,
      cursor.getString(13)!!,
      cursor.getString(14),
      cursor.getString(15),
      cursor.getString(16),
      cursor.getString(17),
      cursor.getString(18),
      cursor.getString(19)!!,
      cursor.getString(20)!!
    )
  }

  public fun getAllPersonasMorales(): Query<PersonaMoral> = getAllPersonasMorales { id,
      denominacion_razon_social, fecha_constitucion, rfc_representante_legal, rfc_socios,
      numero_escritura, codigo_postal, estado_clave, municipio_id, localidad, colonia,
      tipo_vialidad, nombre_vialidad, numero_exterior, numero_interior, entre_calle1, entre_calle2,
      referencia_adicional, caracteristicas_domicilio, regimen_capital, actividad_economica ->
    PersonaMoral(
      id,
      denominacion_razon_social,
      fecha_constitucion,
      rfc_representante_legal,
      rfc_socios,
      numero_escritura,
      codigo_postal,
      estado_clave,
      municipio_id,
      localidad,
      colonia,
      tipo_vialidad,
      nombre_vialidad,
      numero_exterior,
      numero_interior,
      entre_calle1,
      entre_calle2,
      referencia_adicional,
      caracteristicas_domicilio,
      regimen_capital,
      actividad_economica
    )
  }

  public fun <T : Any> getPersonaMoralById(id: Long, mapper: (
    id: Long,
    denominacion_razon_social: String,
    fecha_constitucion: String,
    rfc_representante_legal: String,
    rfc_socios: String,
    numero_escritura: String,
    codigo_postal: String,
    estado_clave: String,
    municipio_id: Long,
    localidad: String,
    colonia: String,
    tipo_vialidad: String,
    nombre_vialidad: String,
    numero_exterior: String,
    numero_interior: String?,
    entre_calle1: String?,
    entre_calle2: String?,
    referencia_adicional: String?,
    caracteristicas_domicilio: String?,
    regimen_capital: String,
    actividad_economica: String,
  ) -> T): Query<T> = GetPersonaMoralByIdQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getLong(8)!!,
      cursor.getString(9)!!,
      cursor.getString(10)!!,
      cursor.getString(11)!!,
      cursor.getString(12)!!,
      cursor.getString(13)!!,
      cursor.getString(14),
      cursor.getString(15),
      cursor.getString(16),
      cursor.getString(17),
      cursor.getString(18),
      cursor.getString(19)!!,
      cursor.getString(20)!!
    )
  }

  public fun getPersonaMoralById(id: Long): Query<PersonaMoral> = getPersonaMoralById(id) { id_,
      denominacion_razon_social, fecha_constitucion, rfc_representante_legal, rfc_socios,
      numero_escritura, codigo_postal, estado_clave, municipio_id, localidad, colonia,
      tipo_vialidad, nombre_vialidad, numero_exterior, numero_interior, entre_calle1, entre_calle2,
      referencia_adicional, caracteristicas_domicilio, regimen_capital, actividad_economica ->
    PersonaMoral(
      id_,
      denominacion_razon_social,
      fecha_constitucion,
      rfc_representante_legal,
      rfc_socios,
      numero_escritura,
      codigo_postal,
      estado_clave,
      municipio_id,
      localidad,
      colonia,
      tipo_vialidad,
      nombre_vialidad,
      numero_exterior,
      numero_interior,
      entre_calle1,
      entre_calle2,
      referencia_adicional,
      caracteristicas_domicilio,
      regimen_capital,
      actividad_economica
    )
  }

  public fun <T : Any> searchPersonaMoral(denominacion_razon_social: String, mapper: (
    id: Long,
    denominacion_razon_social: String,
    fecha_constitucion: String,
    rfc_representante_legal: String,
    rfc_socios: String,
    numero_escritura: String,
    codigo_postal: String,
    estado_clave: String,
    municipio_id: Long,
    localidad: String,
    colonia: String,
    tipo_vialidad: String,
    nombre_vialidad: String,
    numero_exterior: String,
    numero_interior: String?,
    entre_calle1: String?,
    entre_calle2: String?,
    referencia_adicional: String?,
    caracteristicas_domicilio: String?,
    regimen_capital: String,
    actividad_economica: String,
  ) -> T): Query<T> = SearchPersonaMoralQuery(denominacion_razon_social) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getLong(8)!!,
      cursor.getString(9)!!,
      cursor.getString(10)!!,
      cursor.getString(11)!!,
      cursor.getString(12)!!,
      cursor.getString(13)!!,
      cursor.getString(14),
      cursor.getString(15),
      cursor.getString(16),
      cursor.getString(17),
      cursor.getString(18),
      cursor.getString(19)!!,
      cursor.getString(20)!!
    )
  }

  public fun searchPersonaMoral(denominacion_razon_social: String): Query<PersonaMoral> =
      searchPersonaMoral(denominacion_razon_social) { id, denominacion_razon_social_,
      fecha_constitucion, rfc_representante_legal, rfc_socios, numero_escritura, codigo_postal,
      estado_clave, municipio_id, localidad, colonia, tipo_vialidad, nombre_vialidad,
      numero_exterior, numero_interior, entre_calle1, entre_calle2, referencia_adicional,
      caracteristicas_domicilio, regimen_capital, actividad_economica ->
    PersonaMoral(
      id,
      denominacion_razon_social_,
      fecha_constitucion,
      rfc_representante_legal,
      rfc_socios,
      numero_escritura,
      codigo_postal,
      estado_clave,
      municipio_id,
      localidad,
      colonia,
      tipo_vialidad,
      nombre_vialidad,
      numero_exterior,
      numero_interior,
      entre_calle1,
      entre_calle2,
      referencia_adicional,
      caracteristicas_domicilio,
      regimen_capital,
      actividad_economica
    )
  }

  public fun insertPersonaMoral(
    denominacion_razon_social: String,
    fecha_constitucion: String,
    rfc_representante_legal: String,
    rfc_socios: String,
    numero_escritura: String,
    codigo_postal: String,
    estado_clave: String,
    municipio_id: Long,
    localidad: String,
    colonia: String,
    tipo_vialidad: String,
    nombre_vialidad: String,
    numero_exterior: String,
    numero_interior: String?,
    entre_calle1: String?,
    entre_calle2: String?,
    referencia_adicional: String?,
    caracteristicas_domicilio: String?,
    regimen_capital: String,
    actividad_economica: String,
  ) {
    driver.execute(962_539_044, """
        |INSERT INTO PersonaMoral(
        |    denominacion_razon_social, fecha_constitucion, rfc_representante_legal,
        |    rfc_socios, numero_escritura,
        |    codigo_postal, estado_clave, municipio_id, localidad, colonia,
        |    tipo_vialidad, nombre_vialidad, numero_exterior, numero_interior,
        |    entre_calle1, entre_calle2, referencia_adicional, caracteristicas_domicilio,
        |    regimen_capital, actividad_economica
        |) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimMargin(), 20) {
          bindString(0, denominacion_razon_social)
          bindString(1, fecha_constitucion)
          bindString(2, rfc_representante_legal)
          bindString(3, rfc_socios)
          bindString(4, numero_escritura)
          bindString(5, codigo_postal)
          bindString(6, estado_clave)
          bindLong(7, municipio_id)
          bindString(8, localidad)
          bindString(9, colonia)
          bindString(10, tipo_vialidad)
          bindString(11, nombre_vialidad)
          bindString(12, numero_exterior)
          bindString(13, numero_interior)
          bindString(14, entre_calle1)
          bindString(15, entre_calle2)
          bindString(16, referencia_adicional)
          bindString(17, caracteristicas_domicilio)
          bindString(18, regimen_capital)
          bindString(19, actividad_economica)
        }
    notifyQueries(962_539_044) { emit ->
      emit("PersonaMoral")
    }
  }

  public fun updatePersonaMoral(
    denominacion_razon_social: String,
    fecha_constitucion: String,
    rfc_representante_legal: String,
    rfc_socios: String,
    numero_escritura: String,
    codigo_postal: String,
    estado_clave: String,
    municipio_id: Long,
    localidad: String,
    colonia: String,
    tipo_vialidad: String,
    nombre_vialidad: String,
    numero_exterior: String,
    numero_interior: String?,
    entre_calle1: String?,
    entre_calle2: String?,
    referencia_adicional: String?,
    caracteristicas_domicilio: String?,
    regimen_capital: String,
    actividad_economica: String,
    id: Long,
  ) {
    driver.execute(1_016_990_772, """
        |UPDATE PersonaMoral SET
        |    denominacion_razon_social = ?,
        |    fecha_constitucion = ?,
        |    rfc_representante_legal = ?,
        |    rfc_socios = ?,
        |    numero_escritura = ?,
        |    codigo_postal = ?,
        |    estado_clave = ?,
        |    municipio_id = ?,
        |    localidad = ?,
        |    colonia = ?,
        |    tipo_vialidad = ?,
        |    nombre_vialidad = ?,
        |    numero_exterior = ?,
        |    numero_interior = ?,
        |    entre_calle1 = ?,
        |    entre_calle2 = ?,
        |    referencia_adicional = ?,
        |    caracteristicas_domicilio = ?,
        |    regimen_capital = ?,
        |    actividad_economica = ?
        |WHERE id = ?
        """.trimMargin(), 21) {
          bindString(0, denominacion_razon_social)
          bindString(1, fecha_constitucion)
          bindString(2, rfc_representante_legal)
          bindString(3, rfc_socios)
          bindString(4, numero_escritura)
          bindString(5, codigo_postal)
          bindString(6, estado_clave)
          bindLong(7, municipio_id)
          bindString(8, localidad)
          bindString(9, colonia)
          bindString(10, tipo_vialidad)
          bindString(11, nombre_vialidad)
          bindString(12, numero_exterior)
          bindString(13, numero_interior)
          bindString(14, entre_calle1)
          bindString(15, entre_calle2)
          bindString(16, referencia_adicional)
          bindString(17, caracteristicas_domicilio)
          bindString(18, regimen_capital)
          bindString(19, actividad_economica)
          bindLong(20, id)
        }
    notifyQueries(1_016_990_772) { emit ->
      emit("PersonaMoral")
    }
  }

  public fun deletePersonaMoral(id: Long) {
    driver.execute(731_780_630, """DELETE FROM PersonaMoral WHERE id = ?""", 1) {
          bindLong(0, id)
        }
    notifyQueries(731_780_630) { emit ->
      emit("PersonaMoral")
    }
  }

  private inner class GetPersonaMoralByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("PersonaMoral", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("PersonaMoral", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_376_340_869,
        """SELECT PersonaMoral.id, PersonaMoral.denominacion_razon_social, PersonaMoral.fecha_constitucion, PersonaMoral.rfc_representante_legal, PersonaMoral.rfc_socios, PersonaMoral.numero_escritura, PersonaMoral.codigo_postal, PersonaMoral.estado_clave, PersonaMoral.municipio_id, PersonaMoral.localidad, PersonaMoral.colonia, PersonaMoral.tipo_vialidad, PersonaMoral.nombre_vialidad, PersonaMoral.numero_exterior, PersonaMoral.numero_interior, PersonaMoral.entre_calle1, PersonaMoral.entre_calle2, PersonaMoral.referencia_adicional, PersonaMoral.caracteristicas_domicilio, PersonaMoral.regimen_capital, PersonaMoral.actividad_economica FROM PersonaMoral WHERE id = ?""",
        mapper, 1) {
      bindLong(0, id)
    }

    override fun toString(): String = "PersonaMoral.sq:getPersonaMoralById"
  }

  private inner class SearchPersonaMoralQuery<out T : Any>(
    public val denominacion_razon_social: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("PersonaMoral", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("PersonaMoral", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(1_524_163_219,
        """SELECT PersonaMoral.id, PersonaMoral.denominacion_razon_social, PersonaMoral.fecha_constitucion, PersonaMoral.rfc_representante_legal, PersonaMoral.rfc_socios, PersonaMoral.numero_escritura, PersonaMoral.codigo_postal, PersonaMoral.estado_clave, PersonaMoral.municipio_id, PersonaMoral.localidad, PersonaMoral.colonia, PersonaMoral.tipo_vialidad, PersonaMoral.nombre_vialidad, PersonaMoral.numero_exterior, PersonaMoral.numero_interior, PersonaMoral.entre_calle1, PersonaMoral.entre_calle2, PersonaMoral.referencia_adicional, PersonaMoral.caracteristicas_domicilio, PersonaMoral.regimen_capital, PersonaMoral.actividad_economica FROM PersonaMoral WHERE denominacion_razon_social LIKE ? ORDER BY denominacion_razon_social ASC""",
        mapper, 1) {
      bindString(0, denominacion_razon_social)
    }

    override fun toString(): String = "PersonaMoral.sq:searchPersonaMoral"
  }
}
