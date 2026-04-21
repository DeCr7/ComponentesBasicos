package ni.edu.uam.componentesbasicos

import androidx.compose.material3.MaterialTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ni.edu.uam.componentesbasicos.ui.theme.ComponentesBasicosTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var darkMode by remember { mutableStateOf(false) }

            ComponentesBasicosTheme(darkTheme = darkMode) {
                Pantalla1(
                    darkMode = darkMode,
                    onToggleDarkMode = { darkMode = !darkMode }
                )
            }
        }
    }
}

@Composable
fun Pantalla1(
    darkMode: Boolean,
    onToggleDarkMode: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var saludo by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (darkMode) "Modo oscuro activo" else "Modo claro activo",
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onToggleDarkMode,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Text("Cambiar modo")
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    saludo = ""
                },
                label = { Text("Escribe tu nombre") },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (nombre.isNotBlank()) {
                        saludo = "¡Hola, $nombre! 👋"
                        nombre = ""
                    }
                },
                enabled = nombre.isNotBlank() // ✔ AQUÍ VA
            ) {
                Text("Saludar")
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (saludo.isNotEmpty()) {
                Text(
                    text = saludo,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Image(
                    painter = painterResource(id = R.drawable.hola),
                    contentDescription = "Saludo",
                    modifier = Modifier.height(150.dp)
                )
            }
        }
    }
}