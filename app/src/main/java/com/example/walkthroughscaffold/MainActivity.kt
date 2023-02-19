package com.example.walkthroughscaffold

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.walkthroughscaffold.ui.theme.WalkthroughScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalkthroughScaffoldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldApp()
                }
            }
        }
    }
}

@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable( route = "Home"){
            MainScreen(navController)
        }
        composable( route = "Info"){
            InfoScreen(navController)
        }
        composable( route = "Settings"){
            SettingsScreen(navController)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun MainScreen(navController : NavController){
    Scaffold(
        topBar = { MainTopBar("My App", navController) },
        content = { Text(text="Content for Home screen")},
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InfoScreen(navController : NavController){
    Scaffold(
        topBar = { ScreenTopBar("Info",navController)},
        content = { Text(text = "Content for Info screen")} ,
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController : NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Settings",navController)},
        content = { Text(text = "Content for Settings screen")} ,
    )
}

@Composable
fun MainTopBar(title:String , navController : NavController) {
    var expanded by remember { mutableStateOf( false) }
    TopAppBar(
        title = {Text(title)},
        actions = {
            IconButton(onClick = {
                expanded = !expanded
            }
            ) {
                Icon(Icons.Filled.MoreVert,contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded ,
                onDismissRequest = { expanded = false }) {
                DropdownMenuItem(onClick = { navController.navigate("info")}) {
                    Text("info")
                }
                DropdownMenuItem(onClick = { navController.navigate("settings") }) {
                    Text("Settings")
                }
            }
        }
    )
}

@Composable
fun ScreenTopBar(title : String , navController:NavController){
    TopAppBar(
        title = {Text(title)},
        navigationIcon ={
            IconButton(onClick = {navController.navigateUp()}){
                Icon(Icons.Filled.ArrowBack , contentDescription = null)
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WalkthroughScaffoldTheme {
        ScaffoldApp()
    }
}