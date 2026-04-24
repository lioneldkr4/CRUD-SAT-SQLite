package com.sat.contribuyentes.db

import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.sat.contribuyentes.db.composeApp.newInstance
import com.sat.contribuyentes.db.composeApp.schema
import kotlin.Unit

public interface SATDatabase : Transacter {
  public val estadoQueries: EstadoQueries

  public val municipioQueries: MunicipioQueries

  public val personaFisicaQueries: PersonaFisicaQueries

  public val personaMoralQueries: PersonaMoralQueries

  public companion object {
    public val Schema: SqlSchema<QueryResult.Value<Unit>>
      get() = SATDatabase::class.schema

    public operator fun invoke(driver: SqlDriver): SATDatabase =
        SATDatabase::class.newInstance(driver)
  }
}
