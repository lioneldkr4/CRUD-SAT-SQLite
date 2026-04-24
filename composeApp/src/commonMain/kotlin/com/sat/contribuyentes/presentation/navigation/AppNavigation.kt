package com.sat.contribuyentes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sat.contribuyentes.data.repository.CatalogoRepository
import com.sat.contribuyentes.data.repository.PersonaFisicaRepository
import com.sat.contribuyentes.data.repository.PersonaMoralRepository
import com.sat.contribuyentes.presentation.screens.DetallePersonaFisicaScreen
import com.sat.contribuyentes.presentation.screens.DetallePersonaMoralScreen
import com.sat.contribuyentes.presentation.screens.FormPersonaFisicaScreen
import com.sat.contribuyentes.presentation.screens.FormPersonaMoralScreen
import com.sat.contribuyentes.presentation.screens.HomeScreen
import com.sat.contribuyentes.presentation.screens.ListaPersonasFisicasScreen
import com.sat.contribuyentes.presentation.screens.ListaPersonasMoralesScreen
import com.sat.contribuyentes.presentation.viewmodel.PersonaFisicaViewModel
import com.sat.contribuyentes.presentation.viewmodel.PersonaMoralViewModel

@Composable
fun AppNavigation(
    pfRepository: PersonaFisicaRepository,
    pmRepository: PersonaMoralRepository,
    catalogoRepository: CatalogoRepository
) {
    val navController = rememberNavController()

    val pfViewModel = PersonaFisicaViewModel(pfRepository, catalogoRepository)
    val pmViewModel = PersonaMoralViewModel(pmRepository, catalogoRepository)

    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        // ---- Persona Fisica ----
        composable(Screen.ListaPersonasFisicas.route) {
            ListaPersonasFisicasScreen(
                viewModel = pfViewModel,
                navController = navController
            )
        }

        composable(
            route = Screen.FormPersonaFisica.route,
            arguments = listOf(navArgument("id") { type = NavType.LongType; defaultValue = -1L })
        ) { backStack ->
            val id = backStack.arguments?.getLong("id")?.takeIf { it != -1L }
            FormPersonaFisicaScreen(
                viewModel = pfViewModel,
                editingId = id,
                navController = navController
            )
        }

        composable(
            route = Screen.DetallePersonaFisica.route,
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStack ->
            val id = backStack.arguments!!.getLong("id")
            DetallePersonaFisicaScreen(
                viewModel = pfViewModel,
                id = id,
                navController = navController
            )
        }

        // ---- Persona Moral ----
        composable(Screen.ListaPersonasMorales.route) {
            ListaPersonasMoralesScreen(
                viewModel = pmViewModel,
                navController = navController
            )
        }

        composable(
            route = Screen.FormPersonaMoral.route,
            arguments = listOf(navArgument("id") { type = NavType.LongType; defaultValue = -1L })
        ) { backStack ->
            val id = backStack.arguments?.getLong("id")?.takeIf { it != -1L }
            FormPersonaMoralScreen(
                viewModel = pmViewModel,
                editingId = id,
                navController = navController
            )
        }

        composable(
            route = Screen.DetallePersonaMoral.route,
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStack ->
            val id = backStack.arguments!!.getLong("id")
            DetallePersonaMoralScreen(
                viewModel = pmViewModel,
                id = id,
                navController = navController
            )
        }
    }
}
