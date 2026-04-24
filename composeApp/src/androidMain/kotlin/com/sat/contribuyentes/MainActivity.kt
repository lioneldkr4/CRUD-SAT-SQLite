package com.sat.contribuyentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sat.contribuyentes.data.db.DatabaseDriverFactory
import com.sat.contribuyentes.data.db.createDatabase
import com.sat.contribuyentes.data.repository.CatalogoRepository
import com.sat.contribuyentes.data.repository.PersonaFisicaRepository
import com.sat.contribuyentes.data.repository.PersonaMoralRepository
import com.sat.contribuyentes.presentation.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = createDatabase(DatabaseDriverFactory(this))
        val pfRepository = PersonaFisicaRepository(database)
        val pmRepository = PersonaMoralRepository(database)
        val catalogoRepository = CatalogoRepository(database)

        setContent {
            AppNavigation(
                pfRepository = pfRepository,
                pmRepository = pmRepository,
                catalogoRepository = catalogoRepository
            )
        }
    }
}
