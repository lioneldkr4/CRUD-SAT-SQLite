package com.sat.contribuyentes.domain.model

data class Estado(
    val id: Long,
    val clave: String,
    val nombre: String
)

data class Municipio(
    val id: Long,
    val clave: String,
    val nombre: String,
    val estadoClave: String
)

data class DomicilioFiscal(
    val codigoPostal: String = "",
    val estadoClave: String = "",
    val municipioId: Long = 0L,
    val localidad: String = "",
    val colonia: String = "",
    val tipoVialidad: String = "",
    val nombreVialidad: String = "",
    val numeroExterior: String = "",
    val numeroInterior: String = "",
    val entreCalle1: String = "",
    val entreCalle2: String = "",
    val referenciaAdicional: String = "",
    val caracteristicasDomicilio: String = ""
)

data class PersonaFisica(
    val id: Long = 0L,
    val curp: String = "",
    val nombre: String = "",
    val fechaNacimiento: String = "",
    val correo: String = "",
    val telefono: String = "",
    val domicilio: DomicilioFiscal = DomicilioFiscal(),
    val actividadEconomica: String = "",
    val regimenFiscal: String = ""
)

data class PersonaMoral(
    val id: Long = 0L,
    val denominacionRazonSocial: String = "",
    val fechaConstitucion: String = "",
    val rfcRepresentanteLegal: String = "",
    val rfcSocios: String = "",
    val numeroEscritura: String = "",
    val domicilio: DomicilioFiscal = DomicilioFiscal(),
    val regimenCapital: String = "",
    val actividadEconomica: String = ""
)

// Catalogos estaticos
object Catalogos {

    val TIPOS_VIALIDAD = listOf(
        "ANDADOR", "AVENIDA", "BOULEVARD", "CALLE", "CALLEJON",
        "CALZADA", "CAMINO", "CARRETERA", "CERRADA", "CIRCUITO",
        "CIRCUNVALACION", "CONTINUACION", "CORREDOR", "DIAGONAL",
        "EJE VIAL", "LIBRAMIENTO", "PASAJE", "PEATONAL", "PERIFERICO",
        "PRIVADA", "PROLONGACION", "RETORNO", "VIADUCTO"
    )

    val ACTIVIDADES_ECONOMICAS = listOf(
        "Agricultura, cria y explotacion de animales",
        "Silvicultura, extraccion y actividades conexas",
        "Pesca, caza y captura",
        "Extraccion de petroleo y gas",
        "Mineria de minerales metalicos y no metalicos",
        "Generacion, transmision y distribucion de energia electrica",
        "Suministro de agua y suministro de gas",
        "Edificacion",
        "Construccion de obras de ingenieria civil u obra pesada",
        "Trabajos especializados para la construccion",
        "Fabricacion de alimentos",
        "Fabricacion de bebidas y tabaco",
        "Fabricacion de insumos textiles y acabado de textiles",
        "Fabricacion de productos textiles, excepto prendas de vestir",
        "Fabricacion de prendas de vestir",
        "Curtido y acabado de cuero y piel",
        "Industria de la madera",
        "Industria del papel",
        "Impresion e industrias conexas",
        "Fabricacion de productos derivados del petroleo y del carbon",
        "Industria quimica",
        "Industria del plastico y del hule",
        "Fabricacion de productos a base de minerales no metalicos",
        "Industrias metalicas basicas",
        "Fabricacion de productos metalicos",
        "Fabricacion de maquinaria y equipo",
        "Fabricacion de equipo de computacion, comunicacion, medicion y otros",
        "Fabricacion de accesorios, aparatos electricos y equipo de generacion",
        "Fabricacion de equipo de transporte",
        "Fabricacion de muebles, colchones y persianas",
        "Otras industrias manufactureras",
        "Comercio al por mayor",
        "Comercio al por menor",
        "Transportes",
        "Correos y mensajeria",
        "Almacenamiento",
        "Medios masivos",
        "Informacion en medios masivos",
        "Banca central",
        "Actividades bursatiles, cambiarias y de inversion financiera",
        "Compania de seguros, fianzas y pensiones",
        "Servicios inmobiliarios",
        "Alquiler de bienes muebles",
        "Servicios profesionales, cientificos y tecnicos",
        "Corporativos",
        "Servicios de apoyo a los negocios",
        "Servicios educativos",
        "Servicios de salud y de asistencia social",
        "Servicios de esparcimiento, culturales y deportivos",
        "Servicios de alojamiento temporal",
        "Servicios de preparacion de alimentos y bebidas",
        "Otros servicios excepto actividades gubernamentales",
        "Actividades legislativas, gubernamentales, de imparticion de justicia",
        "Organismos internacionales y extraterritoriales"
    )

    val REGIMENES_FISCALES_FISICAS = listOf(
        "601 - General de Ley Personas Morales",
        "603 - Personas Morales con Fines no Lucrativos",
        "605 - Sueldos y Salarios e Ingresos Asimilados a Salarios",
        "606 - Arrendamiento",
        "607 - Regimen de Enajenacion o Adquisicion de Bienes",
        "608 - Demas Ingresos",
        "609 - Consolidacion",
        "610 - Residentes en el Extranjero sin Establecimiento Permanente en Mexico",
        "611 - Ingresos por Dividendos (socios y accionistas)",
        "612 - Personas Fisicas con Actividades Empresariales y Profesionales",
        "614 - Ingresos por intereses",
        "615 - Regimen de los ingresos por obtencion de premios",
        "616 - Sin obligaciones fiscales",
        "621 - Incorporacion Fiscal",
        "622 - Actividades Agricolas, Ganaderas, Silvicolas y Pesqueras",
        "623 - Opcional para Grupos de Sociedades",
        "624 - Coordinados",
        "625 - Regimen de las Actividades Empresariales con ingresos a traves de Plataformas Tecnologicas",
        "626 - Regimen Simplificado de Confianza"
    )

    val REGIMENES_CAPITAL = listOf(
        "S.A. de C.V. - Sociedad Anonima de Capital Variable",
        "S.A. - Sociedad Anonima",
        "S. de R.L. de C.V. - Sociedad de Responsabilidad Limitada de Capital Variable",
        "S. de R.L. - Sociedad de Responsabilidad Limitada",
        "S.C. - Sociedad Cooperativa",
        "S.N.C. - Sociedad en Nombre Colectivo",
        "S.C.S. - Sociedad en Comandita Simple",
        "S.C.A. - Sociedad en Comandita por Acciones",
        "A.C. - Asociacion Civil",
        "A. en P. - Asociacion en Participacion",
        "S.A.S. - Sociedad por Acciones Simplificada",
        "SAPI de CV - Sociedad Anonima Promotora de Inversion",
        "Fideicomiso",
        "Organo Desconcentrado",
        "Organismo Descentralizado"
    )
}
