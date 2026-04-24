package com.sat.contribuyentes.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ListaPersonasFisicas : Screen("lista_personas_fisicas")
    object FormPersonaFisica : Screen("form_persona_fisica?id={id}") {
        fun createRoute(id: Long? = null) = if (id != null) "form_persona_fisica?id=$id" else "form_persona_fisica?id=-1"
    }
    object DetallePersonaFisica : Screen("detalle_persona_fisica/{id}") {
        fun createRoute(id: Long) = "detalle_persona_fisica/$id"
    }
    object ListaPersonasMorales : Screen("lista_personas_morales")
    object FormPersonaMoral : Screen("form_persona_moral?id={id}") {
        fun createRoute(id: Long? = null) = if (id != null) "form_persona_moral?id=$id" else "form_persona_moral?id=-1"
    }
    object DetallePersonaMoral : Screen("detalle_persona_moral/{id}") {
        fun createRoute(id: Long) = "detalle_persona_moral/$id"
    }
}
