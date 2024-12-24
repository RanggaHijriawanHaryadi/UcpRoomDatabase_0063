package com.example.ucp2.ui.navigation

interface HalamanNavigasi {
    val route: String
}

object DestinasiHomeBrg : HalamanNavigasi {
    override val route = "home-Brg"
}

object DestinasiInsertBrg : HalamanNavigasi {
    override val route = "insert-Brg"
}

object DestinasiDetailBrg : HalamanNavigasi{
    override val route = "detail"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}

object DestinasiUpdateBrg : HalamanNavigasi{
    override val route = "update"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}

object DestinasiMulai : HalamanNavigasi {
    override val route = "Memulai app"
}

object DestinasiHomeSpr : HalamanNavigasi {
    override val route = "home-Spr"
}

object DestinasiInsertSpr : HalamanNavigasi {
    override val route: String = "insert-spr"
}