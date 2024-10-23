package trpl.nim234311054.trofimonitor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddClubScreen(onClubAdded: (Club) -> Unit) {
    var name by remember { mutableStateOf("") }
    var premierLeague by remember { mutableStateOf("") }
    var faCup by remember { mutableStateOf("") }
    var eflCup by remember { mutableStateOf("") }
    var championsLeague by remember { mutableStateOf("") }
    var europaLeague by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        StudentInfoScreen()

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Club Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = premierLeague,
            onValueChange = { premierLeague = it },
            label = { Text("Premier League Titles") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = faCup,
            onValueChange = { faCup = it },
            label = { Text("FA Cup Titles") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = eflCup,
            onValueChange = { eflCup = it },
            label = { Text("EFL Cup Titles") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = championsLeague,
            onValueChange = { championsLeague = it },
            label = { Text("Champions League Titles") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = europaLeague,
            onValueChange = { europaLeague = it },
            label = { Text("Europa League Titles") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (name.isNotEmpty() && premierLeague.isNotEmpty() && faCup.isNotEmpty() &&
                    eflCup.isNotEmpty() && championsLeague.isNotEmpty() && europaLeague.isNotEmpty()
                ) {
                    val newClub = Club(
                        name = name,
                        premierLeague = premierLeague.toInt(),
                        faCup = faCup.toInt(),
                        eflCup = eflCup.toInt(),
                        championsLeague = championsLeague.toInt(),
                        europaLeague = europaLeague.toInt()
                    )
                    onClubAdded(newClub)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp), // Padding tambahan di sekitar tombol
        ) {
            Text("Add Club", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun StudentInfoScreen() {
    val studentName = "Muhammad Hasanuddin"
    val studentID = "234311045"

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally // Nama dan NIM dipusatkan
    ) {
        Text(
            text = "Nama: $studentName",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "NIM: $studentID",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

class AddClubScreen {

}
