package com.sat.contribuyentes.data.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.sat.contribuyentes.db.SATDatabase
import java.io.File

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val dbFile = File(System.getProperty("user.home"), ".sat_contribuyentes/sat_contribuyentes.db")
        dbFile.parentFile?.mkdirs()
        val isNew = !dbFile.exists()
        val driver = JdbcSqliteDriver("jdbc:sqlite:${dbFile.absolutePath}")
        if (isNew) {
            SATDatabase.Schema.create(driver)
        }
        return driver
    }
}
