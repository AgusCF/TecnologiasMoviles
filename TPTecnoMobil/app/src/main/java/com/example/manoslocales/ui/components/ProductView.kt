package com.example.manoslocales.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manoslocales.data.local.entities.Product

@Composable
fun ProductItem(product: Product) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = "Imagen de ${product.name}",
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)), // Color de fondo predeterminado
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Text(
            text = "$${product.price}",
            fontSize = 14.sp,
            color = Color(0xFF555555),
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = product.owner.user.city,
            fontSize = 14.sp,
            color = Color(0xFF555555),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}