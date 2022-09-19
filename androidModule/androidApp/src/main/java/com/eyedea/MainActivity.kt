package com.eyedea

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.eyedea.navigation.CoreFeatureNavigator
import com.eyedea.navigation.RootNavGraph
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class,
        ExperimentalComposeUiApi::class, ExperimentalCoilApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            MaterialTheme {
                BoxWithConstraints {
                    val animationDuration = 300
                    val width = constraints.maxWidth
                    val navEngine = rememberAnimatedNavHostEngine(
                        rootDefaultAnimations = RootNavGraphDefaultAnimations(
                            enterTransition = {
                                slideInHorizontally(
                                    initialOffsetX = { width },
                                    animationSpec = tween(
                                        durationMillis = animationDuration,
                                        easing = FastOutSlowInEasing
                                    )
                                )
                            },
                            exitTransition = {
                                slideOutHorizontally(
                                    targetOffsetX = { -width },
                                    animationSpec = tween(
                                        durationMillis = animationDuration,
                                        easing = FastOutSlowInEasing
                                    )
                                )
                            },
                            popEnterTransition = {
                                slideInHorizontally(
                                    initialOffsetX = { -width },
                                    animationSpec = tween(
                                        durationMillis = animationDuration,
                                        easing = FastOutSlowInEasing
                                    )
                                )
                            },
                            popExitTransition = {
                                slideOutHorizontally(
                                    targetOffsetX = { width },
                                    animationSpec = tween(
                                        durationMillis = animationDuration,
                                        easing = FastOutSlowInEasing
                                    )
                                )
                            }
                        )
                    )
                    val navController = navEngine.rememberNavController()
                    val scaffoldState = rememberScaffoldState()
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState
                    ) {
                        DestinationsNavHost(
                            navGraph = RootNavGraph,
                            navController = navController,
                            engine = navEngine,
                            dependenciesContainerBuilder = {
                                dependency(scaffoldState)
                                dependency(CoreFeatureNavigator(destination, navController))
                            }
                        )
                    }
                }
            }
        }
    }
}
