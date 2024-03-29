package com.location.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.location.composesample.bottom.HomeScreen
import com.location.composesample.ui.theme.AndroidSampleTheme

class MainActivity : ComponentActivity() {
    private val homeList by lazy {
        listOf(HomeScreen.Weight, HomeScreen.Layout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidSampleTheme {
                // A surface container using the 'background' color from the theme
                Home()
            }
        }
    }

    @Composable
    private fun Home() {
        //控制器
        val navController = rememberNavController()
        Scaffold(bottomBar = {
            //添加底部导航栏
            BottomBar(navController)
        }) {
            NavHost(
                navController = navController,
                startDestination = HomeScreen.Weight.rotateName,
                modifier = Modifier.padding(it)
            ) {

                weightGraph(
                    navigateRotate = {
                            route ->
                        navController.navigate(route = route)
                    },
                    back = {
                        navController.popBackStack()
                    }
                )
//                composable(HomeScreen.Weight.rotateName) {
//                    WeightGraph(
//                        navController, items = listOf(
//                            "Button",
//                            "Text",
//                            "TextFiled",
//                            "CheckBox",
//                            "RadioButton",
//                            "Slider",
//                            "Snackbar"
//                        )
//                    )
//                }
                composable(HomeScreen.Layout.rotateName) {
                    LayoutGraph(navController)
                }

            }
        }
    }


    @Composable
    private fun BottomBar(nav: NavHostController) {
        BottomNavigation {
            val navBackStackEntry by nav.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            homeList.forEach {
                val checked =
                    currentDestination?.hierarchy?.any { any -> any.route == it.rotateName } == true
                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = null
                        )
                    },
                    selected = checked,
                    label = {
                        //不同的颜色
                        Text(
                            stringResource(it.titleId),
                            color = if (checked) Color.White else Color.Black
                        )
                    },
                    onClick = {
                        nav.navigate(route = it.rotateName) {
                            popUpTo(nav.graph.findStartDestination().id) {
                                saveState = true
                            }
                            //防止在顶部是多次创建
                            launchSingleTop = true
                            // 恢复状态
                            restoreState = true
                        }
                    })
            }
        }
    }
}



