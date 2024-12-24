package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.view.Brg.DetailBrgView
import com.example.ucp2.ui.view.HalamanUtama.HalamanUtamaView
import com.example.ucp2.ui.view.Brg.InsertBrgView
import com.example.ucp2.ui.view.Spr.InsertSprView
import com.example.ucp2.ui.view.Brg.UpdateBrgView
import com.example.ucp2.ui.view.Brg.UtamaBrgView

import com.example.ucp2.ui.view.Spr.UtamaSprView


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiMulai.route
    ) {
        // Halaman Utama
        composable(
            route = DestinasiMulai.route
        ) {
            HalamanUtamaView(
                onSuplierClick =  {
                    navController.navigate(DestinasiInsertSpr.route)
                },
                onBarangClick = {
                    navController.navigate(DestinasiInsertBrg.route)
                },
                onInsertSprClick = {
                    navController.navigate(DestinasiHomeSpr.route)
                },
                onInsertBrgClick = {
                    navController.navigate(DestinasiHomeBrg.route)
                }
            )
        }

        // Utama Barang
        composable(
            route = DestinasiHomeBrg.route
        ) {
            UtamaBrgView(
                onDetailClick = {id ->
                    navController.navigate("${DestinasiDetailBrg.route}/$id")
                    println(
                        "UtamaHalaman: id = $id"
                    )
                },
                onBack = {
                    navController.popBackStack()
                },

                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertBrg.route
        ) {
            InsertBrgView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {

                },
                modifier = modifier
            )
        }

        composable(
            DestinasiDetailBrg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailBrg.ID) {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt(DestinasiDetailBrg.ID)
            id?.let { id ->
                DetailBrgView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateBrg.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiUpdateBrg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateBrg.ID){
                    type = NavType.IntType
                }
            )
        ) {
            UpdateBrgView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {

                },
                modifier = modifier,
            )
        }

        // Utama Suplier
        composable(
            route = DestinasiHomeSpr.route
        ) {
            UtamaSprView(
                onBack = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertSpr.route
        ) {
            InsertSprView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {

                },
                modifier = modifier
            )
        }


    }
}
