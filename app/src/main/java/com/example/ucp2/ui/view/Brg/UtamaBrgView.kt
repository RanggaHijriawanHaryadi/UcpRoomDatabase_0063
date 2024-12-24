package com.example.ucp2.ui.view.Brg

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.ui.costumwidget.TopBar
import com.example.ucp2.ui.viewmodel.Brg.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.Brg.UtamaBrgUiState
import com.example.ucp2.ui.viewmodel.Brg.UtamaBrgViewModel
import kotlinx.coroutines.launch


@Composable
fun UtamaBrgView(
    viewModel: UtamaBrgViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onDetailClick: (String) -> Unit = { },
    onBack: () -> Unit,
    modifier: Modifier = Modifier

) {
    Scaffold(
        topBar = {
            TopBar(
                judul = "Daftar Barang",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },

    ) { innerPadding ->
        val utamaBrgUiState by viewModel.utamaBrgUIState.collectAsState()

        BodyUtamaBrgView(
            utamaBrgUiState = utamaBrgUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyUtamaBrgView(
    utamaBrgUiState: UtamaBrgUiState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        utamaBrgUiState.isLoadingBrg -> {

            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        utamaBrgUiState.isErrorBrg -> {

            LaunchedEffect(utamaBrgUiState.errorMessageBrg) {
                utamaBrgUiState.errorMessageBrg?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }

        utamaBrgUiState.listBrg.isEmpty() -> {

            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data Barang.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {

            ListBarang(
                listBrg = utamaBrgUiState.listBrg,
                onClick = {
                    onClick(it)
                    println(
                        it
                    )
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListBarang(
    listBrg: List<Barang>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    LazyColumn (
        modifier = modifier
    ) {
        items(
            items = listBrg,
            itemContent = { brg ->
                CardBrg(
                    brg = brg,
                    onClick = { onClick(brg.id.toString()) }
                )
            }
        )
    }
}


@Composable
fun CardBrg(
    brg: Barang,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    val cardColors = if (brg.stok == 0){
        Color.Gray
    } else if (brg.stok in 1 .. 10){
        Color.Red
    }else{
        Color.Green
    }
    Card (
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = cardColors(containerColor = cardColors)
    ) {
        Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding()
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier
                        .size(85.dp)
                        .padding(18.dp)
                )

                Column (
                    modifier = Modifier.padding(8.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = brg.nama,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                    Row (

                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Filled.Info,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "${brg.stok} brg",
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Filled.Star,
                            contentDescription = null,modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = brg.deskripsi,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                }
            }
            Text(
                text = "${brg.harga} hrg",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(top = 30.dp)
            )

    }

    }
}

