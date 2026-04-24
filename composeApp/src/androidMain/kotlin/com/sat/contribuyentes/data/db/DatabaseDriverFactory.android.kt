package com.sat.contribuyentes.data.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.sat.contribuyentes.db.SATDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(SATDatabase.Schema, context, "sat_contribuyentes.db")
    }
}
