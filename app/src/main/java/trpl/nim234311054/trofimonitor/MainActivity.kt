package trpl.nim234311054.trofimonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import trpl.nim234311054.trofimonitor.ui.theme.TrofiMonitorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrofiMonitorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationComponent(navController = navController)
                }
            }
        }
    }

    @Composable
    fun NavigationComponent(navController: NavHostController) {
        var clubs by remember {
            mutableStateOf(
                listOf(
                    Club("Liverpool", 19, 8, 10, 6, 3),
                    Club("Manchester United", 20, 12, 6, 3, 1),
                    Club("Chelsea", 6, 8, 5, 2, 2),
                    Club("Manchester City", 9, 7, 8, 1, 0),
                    Club("Arsenal", 13, 14, 2, 0, 0),
                    Club("Tottenham Hotspur", 2, 8, 4, 0, 0)
                )
            )
        }

        NavHost(navController = navController, startDestination = "mainScreen") {
            composable("mainScreen") {
                MainScreen(
                    clubs = clubs,
                    onAddClubClick = {
                        navController.navigate("addClubScreen")
                    }
                )
            }
            composable("addClubScreen") {
                AddClubScreen(
                    onClubAdded = { newClub ->
                        clubs = clubs + newClub
                        navController.popBackStack()
                    }
                )
            }
        }
    }

    @Composable
    fun MainScreen(clubs: List<Club>, onAddClubClick: () -> Unit) {
        Column {
            StudentInfoScreen()

            DisplayClubs(clubs)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onAddClubClick) {
                Text("Tambah Data Club")
            }

            val filterClubs = clubs.filter { it.totalTrophies > 30 }

            Text(
                text = "Klub yang memiliki lebih dari 30 trofi:",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )

            filterClubs.forEach { club ->
                Text(text = "${club.name} memiliki lebih dari 30 trofi")
            }
        }
    }

    @Composable
    fun StudentInfoScreen() {
        val studentName = "Satria Bagus Al Imanu"
        val studentID = "234311054"

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Nama: $studentName", style = MaterialTheme.typography.bodyLarge)
            Text(text = "NIM: $studentID", style = MaterialTheme.typography.bodyLarge)
        }
    }

    @Composable
    fun DisplayClubs(clubs: List<Club>) {
        val sortedClubs = clubs.sortedByDescending { it.totalTrophies }
        Column {
            sortedClubs.forEach { club ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val logoResId = when (club.name) {
                        "Liverpool" -> R.drawable.liverpool
                        "Manchester United" -> R.drawable.emyu
                        "Chelsea" -> R.drawable.chelsea
                        "Manchester City" -> R.drawable.man_city
                        "Arsenal" -> R.drawable.arsenal
                        "Tottenham Hotspur" -> R.drawable.hospur
                        else -> R.drawable.barcelona
                    }

                    Image(
                        painter = painterResource(id = logoResId),
                        contentDescription = "${club.name} Logo",
                        modifier = Modifier
                            .size(64.dp)
                            .padding(end = 8.dp)
                    )

                    Column {
                        Text(
                            text = club.name,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )

                        Text(text = "Total Trophies: ${club.totalTrophies}")

                        if (club.championsLeague == 0 && club.europaLeague == 0) {
                            Text(text = "${club.name} belum pernah memenangkan trofi internasional")
                        }
                    }
                }
            }
        }
    }
}