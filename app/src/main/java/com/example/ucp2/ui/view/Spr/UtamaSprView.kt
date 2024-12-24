package com.example.ucp2.ui.view.Spr

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.entity.Suplier
import com.example.ucp2.ui.costumwidget.TopBar
import com.example.ucp2.ui.viewmodel.Brg.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.Spr.UtamaSprUiState
import com.example.ucp2.ui.viewmodel.Spr.UtamaSprViewModel
import kotlinx.coroutines.launch

@Composable
fun UtamaSprView(
    viewModel: UtamaSprViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit,
    onDetailClick: (String) ->Unit = { },
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            TopBar(
                judul = "Daftar Suplier",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },

    ) { innerPadding ->
        val utamaSprUiState by viewModel.utamaSprUIState.collectAsState()

        BodyUtamaSprView(
            utamaSprUiState = utamaSprUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyUtamaSprView(
    utamaSprUiState: UtamaSprUiState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() } // Snackbar state
    when {
        utamaSprUiState.isLoadingSpr -> {
            // Menampilkan indikator loading
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        utamaSprUiState.isErrorSpr -> {
            //Menampilkan pesarn error
            LaunchedEffect(utamaSprUiState.errorMessageSpr) {
                utamaSprUiState.errorMessageSpr?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message) //Tampilkan Snackbar
                    }
                }
            }
        }

        utamaSprUiState.listSpr.isEmpty() -> {
            // Menampilkan pesan jika data kosong
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data Suplier.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            //Menampilkan daftar mahasiswa
            ListSuplier(
                listSpr = utamaSprUiState.listSpr,
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
fun ListSuplier(
    listSpr: List<Suplier>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    LazyColumn (
        modifier = modifier
    ) {
        items(
            items = listSpr,
            itemContent = { spr ->
                CardSpr(
                    spr = spr,
                    onClick = { onClick(spr.id.toString()) }
                )
            }
        )
    }
}

@Composable
fun CardSpr(
    spr: Suplier,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Card (
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column (
            modifier = Modifier.padding(4.dp)
        ) {

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = spr.nama,
                    fontWeight = FontWeight.Bold,

                )
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = spr.kontak,
                    fontWeight = FontWeight.Bold,
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = spr.alamat,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}