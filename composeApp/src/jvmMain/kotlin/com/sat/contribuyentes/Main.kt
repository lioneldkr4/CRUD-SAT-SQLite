package com.sat.contribuyentes

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sat.contribuyentes.data.db.DatabaseDriverFactory
import com.sat.contribuyentes.data.db.createDatabase
import com.sat.contribuyentes.data.repository.CatalogoRepository
import com.sat.contribuyentes.data.repository.PersonaFisicaRepository
import com.sat.contribuyentes.data.repository.PersonaMoralRepository
import com.sat.contribuyentes.presentation.navigation.AppNavigation

fun main() = application {
    val database = createDatabase(DatabaseDriverFactory())
    val pfRepository = PersonaFisicaRepository(database)
    val pmRepository = PersonaMoralRepository(database)
    val catalogoRepository = CatalogoRepository(database)

    Window(
        onCloseRequest = ::exitApplication,
        title = "SAT Contribuyentes"
    ) {
        AppNavigation(
            pfRepository = pfRepository,
            pmRepository = pmRepository,
            catalogoRepository = catalogoRepository
        )
    }
}
