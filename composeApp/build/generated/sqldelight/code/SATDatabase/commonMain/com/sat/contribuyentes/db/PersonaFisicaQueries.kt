package com.sat.contribuyentes.db

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class PersonaFisicaQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getAllPersonasFisicas(mapper: (
    id: Long,
    curp: String,
    nombre: String,
    fecha_nacimiento: String,
    correo: String,
    telefono: String,
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
    actividad_economica: String,
    regimen_fiscal: String,
  ) -> T): Query<T> = Query(383_734_360, arrayOf("PersonaFisica"), driver, "PersonaFisica.sq",
      "getAllPersonasFisicas",
      "SELECT PersonaFisica.id, PersonaFisica.curp, PersonaFisica.nombre, PersonaFisica.fecha_nacimiento, PersonaFisica.correo, PersonaFisica.telefono, PersonaFisica.codigo_postal, PersonaFisica.estado_clave, PersonaFisica.municipio_id, PersonaFisica.localidad, PersonaFisica.colonia, PersonaFisica.tipo_vialidad, PersonaFisica.nombre_vialidad, PersonaFisica.numero_exterior, PersonaFisica.numero_interior, PersonaFisica.entre_calle1, PersonaFisica.entre_calle2, PersonaFisica.referencia_adicional, PersonaFisica.caracteristicas_domicilio, PersonaFisica.actividad_economica, PersonaFisica.regimen_fiscal FROM PersonaFisica ORDER BY nombre ASC") {
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

  public fun getAllPersonasFisicas(): Query<PersonaFisica> = getAllPersonasFisicas { id, curp,
      nombre, fecha_nacimiento, correo, telefono, codigo_postal, estado_clave, municipio_id,
      localidad, colonia, tipo_vialidad, nombre_vialidad, numero_exterior, numero_interior,
      entre_calle1, entre_calle2, referencia_adicional, caracteristicas_domicilio,
      actividad_economica, regimen_fiscal ->
    PersonaFisica(
      id,
      curp,
      nombre,
      fecha_nacimiento,
      correo,
      telefono,
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
      actividad_economica,
      regimen_fiscal
    )
  }

  public fun <T : Any> getPersonaFisicaById(id: Long, mapper: (
    id: Long,
    curp: String,
    nombre: String,
    fecha_nacimiento: String,
    correo: String,
    telefono: String,
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
    actividad_economica: String,
    regimen_fiscal: String,
  ) -> T): Query<T> = GetPersonaFisicaByIdQuery(id) { cursor ->
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

  public fun getPersonaFisicaById(id: Long): Query<PersonaFisica> = getPersonaFisicaById(id) { id_,
      curp, nombre, fecha_nacimiento, correo, telefono, codigo_postal, estado_clave, municipio_id,
      localidad, colonia, tipo_vialidad, nombre_vialidad, numero_exterior, numero_interior,
      entre_calle1, entre_calle2, referencia_adicional, caracteristicas_domicilio,
      actividad_economica, regimen_fiscal ->
    PersonaFisica(
      id_,
      curp,
      nombre,
      fecha_nacimiento,
      correo,
      telefono,
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
      actividad_economica,
      regimen_fiscal
    )
  }

  public fun <T : Any> searchPersonaFisica(
    nombre: String,
    curp: String,
    mapper: (
      id: Long,
      curp: String,
      nombre: String,
      fecha_nacimiento: String,
      correo: String,
      telefono: String,
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
      actividad_economica: String,
      regimen_fiscal: String,
    ) -> T,
  ): Query<T> = SearchPersonaFisicaQuery(nombre, curp) { cursor ->
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

  public fun searchPersonaFisica(nombre: String, curp: String): Query<PersonaFisica> =
      searchPersonaFisica(nombre, curp) { id, curp_, nombre_, fecha_nacimiento, correo, telefono,
      codigo_postal, estado_clave, municipio_id, localidad, colonia, tipo_vialidad, nombre_vialidad,
      numero_exterior, numero_interior, entre_calle1, entre_calle2, referencia_adicional,
      caracteristicas_domicilio, actividad_economica, regimen_fiscal ->
    PersonaFisica(
      id,
      curp_,
      nombre_,
      fecha_nacimiento,
      correo,
      telefono,
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
      actividad_economica,
      regimen_fiscal
    )
  }

  public fun insertPersonaFisica(
    curp: String,
    nombre: String,
    fecha_nacimiento: String,
    correo: String,
    telefono: String,
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
    actividad_economica: String,
    regimen_fiscal: String,
  ) {
    driver.execute(-776_997_320, """
        |INSERT INTO PersonaFisica(
        |    curp, nombre, fecha_nacimiento, correo, telefono,
        |    codigo_postal, estado_clave, municipio_id, localidad, colonia,
        |    tipo_vialidad, nombre_vialidad, numero_exterior, numero_interior,
        |    entre_calle1, entre_calle2, referencia_adicional, caracteristicas_domicilio,
        |    actividad_economica, regimen_fiscal
        |) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimMargin(), 20) {
          bindString(0, curp)
          bindString(1, nombre)
          bindString(2, fecha_nacimiento)
          bindString(3, correo)
          bindString(4, telefono)
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
          bindString(18, actividad_economica)
          bindString(19, regimen_fiscal)
        }
    notifyQueries(-776_997_320) { emit ->
      emit("PersonaFisica")
    }
  }

  public fun updatePersonaFisica(
    curp: String,
    nombre: String,
    fecha_nacimiento: String,
    correo: String,
    telefono: String,
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
    actividad_economica: String,
    regimen_fiscal: String,
    id: Long,
  ) {
    driver.execute(911_006_248, """
        |UPDATE PersonaFisica SET
        |    curp = ?,
        |    nombre = ?,
        |    fecha_nacimiento = ?,
        |    correo = ?,
        |    telefono = ?,
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
        |    actividad_economica = ?,
        |    regimen_fiscal = ?
        |WHERE id = ?
        """.trimMargin(), 21) {
          bindString(0, curp)
          bindString(1, nombre)
          bindString(2, fecha_nacimiento)
          bindString(3, correo)
          bindString(4, telefono)
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
          bindString(18, actividad_economica)
          bindString(19, regimen_fiscal)
          bindLong(20, id)
        }
    notifyQueries(911_006_248) { emit ->
      emit("PersonaFisica")
    }
  }

  public fun deletePersonaFisica(id: Long) {
    driver.execute(659_426_438, """DELETE FROM PersonaFisica WHERE id = ?""", 1) {
          bindLong(0, id)
        }
    notifyQueries(659_426_438) { emit ->
      emit("PersonaFisica")
    }
  }

  private inner class GetPersonaFisicaByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("PersonaFisica", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("PersonaFisica", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(1_829_815_697,
        """SELECT PersonaFisica.id, PersonaFisica.curp, PersonaFisica.nombre, PersonaFisica.fecha_nacimiento, PersonaFisica.correo, PersonaFisica.telefono, PersonaFisica.codigo_postal, PersonaFisica.estado_clave, PersonaFisica.municipio_id, PersonaFisica.localidad, PersonaFisica.colonia, PersonaFisica.tipo_vialidad, PersonaFisica.nombre_vialidad, PersonaFisica.numero_exterior, PersonaFisica.numero_interior, PersonaFisica.entre_calle1, PersonaFisica.entre_calle2, PersonaFisica.referencia_adicional, PersonaFisica.caracteristicas_domicilio, PersonaFisica.actividad_economica, PersonaFisica.regimen_fiscal FROM PersonaFisica WHERE id = ?""",
        mapper, 1) {
      bindLong(0, id)
    }

    override fun toString(): String = "PersonaFisica.sq:getPersonaFisicaById"
  }

  private inner class SearchPersonaFisicaQuery<out T : Any>(
    public val nombre: String,
    public val curp: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("PersonaFisica", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("PersonaFisica", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-546_517_079,
        """SELECT PersonaFisica.id, PersonaFisica.curp, PersonaFisica.nombre, PersonaFisica.fecha_nacimiento, PersonaFisica.correo, PersonaFisica.telefono, PersonaFisica.codigo_postal, PersonaFisica.estado_clave, PersonaFisica.municipio_id, PersonaFisica.localidad, PersonaFisica.colonia, PersonaFisica.tipo_vialidad, PersonaFisica.nombre_vialidad, PersonaFisica.numero_exterior, PersonaFisica.numero_interior, PersonaFisica.entre_calle1, PersonaFisica.entre_calle2, PersonaFisica.referencia_adicional, PersonaFisica.caracteristicas_domicilio, PersonaFisica.actividad_economica, PersonaFisica.regimen_fiscal FROM PersonaFisica WHERE nombre LIKE ? OR curp LIKE ? ORDER BY nombre ASC""",
        mapper, 2) {
      bindString(0, nombre)
      bindString(1, curp)
    }

    override fun toString(): String = "PersonaFisica.sq:searchPersonaFisica"
  }
}
