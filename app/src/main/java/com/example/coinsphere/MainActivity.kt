package com.example.coinsphere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.coinsphere.ui.theme.CoinSphereTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinSphereTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    HomeScreen()
                }
            }
        }
    }
}

data class Crypto(
    val name: String,
    val price: String,
    val imgURL: String
)

@Composable
fun HomeScreen() {
    val cryptos =listOf(
        Crypto("Bitcoin", "$109,797.37", "https://cryptologos.cc/logos/bitcoin-btc-logo.png"),
        Crypto("Ethereum", "$4,321.21", "https://cryptologos.cc/logos/ethereum-eth-logo.png"),
        Crypto("Tether", "$1.0000", "https://cryptologos.cc/logos/tether-usdt-logo.png"),
        Crypto("XRP", "$2.8100", "https://cryptologos.cc/logos/xrp-xrp-logo.png"),
        Crypto("BNB", "$845.0000", "https://cryptologos.cc/logos/bnb-bnb-logo.png"),
        Crypto("Solana", "$201.8500", "https://cryptologos.cc/logos/solana-sol-logo.png"),
        Crypto("USDC", "$0.9998", "https://cryptologos.cc/logos/usd-coin-usdc-logo.png"),
        Crypto("Dogecoin", "$0.1320", "https://cryptologos.cc/logos/dogecoin-doge-logo.png"),
        Crypto("TRON", "$0.3630", "https://cryptologos.cc/logos/tron-trx-logo.png")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "CoinSphere",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier
            .height(16.dp))

        //Tajeta informativas
        InfoCard(title = "Global Market Cap", value = "$2.18T")
        InfoCard(title = "Fear & Greed", value = "Neutral (54)")
        InfoCard(title = "Altcoin Season", value = "No")

        Spacer(modifier = Modifier
            .height(16.dp))

        //Encabezados lista
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "#",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Name",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Price",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                fontWeight = FontWeight.SemiBold
            )
        }
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))

        LazyColumn {
            itemsIndexed(cryptos) { index, crypto ->
                CryptoItemWithRank(rank = index + 1, crypto = crypto)
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            }
        }
    }
}

@Composable
fun CryptoItemWithRank(rank: Int, crypto: Crypto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = crypto.imgURL,
                contentDescription = crypto.name,
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = rank.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.width(24.dp)
            )
            Spacer(Modifier.width(8.dp))
            Image(
                painter = rememberAsyncImagePainter(crypto.imgURL),
                contentDescription = crypto.name,
                modifier = Modifier.size(36.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.width(12.dp))
            Text(
                crypto.name,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp
            )
        }
        Text(
            crypto.price,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.sp
        )
    }
}


@Composable
fun InfoCard(title: String, value: String) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
            Text(value, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
        }
    }
}


@Preview(
    showBackground = true,
)
@Composable
fun GreetingPreview() {
    CoinSphereTheme {
        HomeScreen()
    }
}