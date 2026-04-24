package com.sat.contribuyentes.db

import kotlin.Long
import kotlin.String

public data class Municipio(
  public val id: Long,
  public val clave: String,
  public val nombre: String,
  public val estado_clave: String,
)
