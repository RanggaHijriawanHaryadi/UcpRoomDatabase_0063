package com.example.ucp2.ui.view.HalamanUtama

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R

@Composable
fun HalamanUtamaView(
    onSuplierClick: () -> Unit,
    onBarangClick: () -> Unit,
    onInsertSprClick: () -> Unit,
    onInsertBrgClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, top = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEEB645))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.a),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp),
                    contentScale = ContentScale.Fit
                )
                Column{
                    Text(
                        text = "Post package ",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Gunakan Spacer untuk memberi jarak vertikal
        Spacer(modifier = Modifier.height(50.dp))  // Jarak vertikal

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Menyelaraskan elemen di tengah secara horizontal
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center // Menyelaraskan Row secara horizontal
            ) {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(140.dp)
                        .clickable { onSuplierClick() },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF62F2EA))

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.c),
                            contentDescription = "Add Suplier",
                            modifier = Modifier.size(80.dp)
                        )
                        Text(
                            text = "Add Suplier",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(140.dp)
                        .clickable { onInsertSprClick() },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF62F2EA))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.e),
                            contentDescription = "List Suplier",
                            modifier = Modifier.size(80.dp)
                        )
                        Text(
                            text = "List Suplier",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center // Menyelaraskan Row secara horizontal
            ) {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(140.dp)
                        .clickable { onBarangClick() },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF62F2EA))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.b),
                            contentDescription = "Add Barang",
                            modifier = Modifier.size(80.dp)
                        )
                        Text(
                            text = "Add Barang",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(140.dp)
                        .clickable { onInsertBrgClick() },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF62F2EA))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.d),
                            contentDescription = "List Barang",
                            modifier = Modifier.size(80.dp)
                        )
                        Text(
                            text = "List Barang",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
