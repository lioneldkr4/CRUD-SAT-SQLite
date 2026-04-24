package com.sat.contribuyentes.db

import kotlin.Long
import kotlin.String

public data class PersonaMoral(
  public val id: Long,
  public val denominacion_razon_social: String,
  public val fecha_constitucion: String,
  public val rfc_representante_legal: String,
  public val rfc_socios: String,
  public val numero_escritura: String,
  public val codigo_postal: String,
  public val estado_clave: String,
  public val municipio_id: Long,
  public val localidad: String,
  public val colonia: String,
  public val tipo_vialidad: String,
  public val nombre_vialidad: String,
  public val numero_exterior: String,
  public val numero_interior: String?,
  public val entre_calle1: String?,
  public val entre_calle2: String?,
  public val referencia_adicional: String?,
  public val caracteristicas_domicilio: String?,
  public val regimen_capital: String,
  public val actividad_economica: String,
)
