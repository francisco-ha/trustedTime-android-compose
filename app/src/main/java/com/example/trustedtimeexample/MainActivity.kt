package com.example.trustedtimeexample

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trustedtimeexample.ui.theme.TrustedTimeExampleTheme
import java.time.Duration
import java.time.Instant

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrustedTimeExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 2. Le pasamos los innerPadding a tu función para que el botón no se tape
                    Greeting(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // 2. Centramos todo lo que esté adentro
    ) {
        Button(
            modifier = Modifier.padding(26.dp),
            onClick = {
                Log.i("Test1", TrustedTimeApp.trustedTime?.computeCurrentUnixEpochMillis().toString())
                Log.i("Test2", TrustedTimeApp.trustedTime?.computeCurrentInstant().toString())
                Log.i("Test3", System.currentTimeMillis().toString())
            }) {
            Text(
                text = "Click me"
            )
        }
    }

}

fun hasPassedXHours(startTime: Long, endTime: Long, hours: Long): Boolean {
    val startInstant = Instant.ofEpochMilli(startTime)
    val endInstant = Instant.ofEpochMilli(endTime)

    val duration = Duration.between(startInstant, endInstant)

    return duration.toHours() >= hours
}