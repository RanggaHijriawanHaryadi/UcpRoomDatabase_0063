package com.example.ucp2.ui.view.HalamanUtama

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HalamanUtamaView(
    onSuplierClick: () -> Unit,
    onBarangClick: () -> Unit,
    onInsertSprClick: () -> Unit,
    onInsertBrgClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Pilih Halaman",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // Tombol untuk Home Suplier
            androidx.compose.material3.Button(
                onClick = onSuplierClick,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Add Suplier")
            }

            androidx.compose.material3.Button(
                onClick = onInsertSprClick,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "List Suplier")
            }

            // Tombol untuk Home Barang
            androidx.compose.material3.Button(
                onClick = onBarangClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add Barang")
            }

            androidx.compose.material3.Button(
                onClick = onInsertBrgClick,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "List Barang")
            }
        }
    }
}
