package com.example.projecttars

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NamedNavArgument
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.navigation.compose.composable

// Transition lambdas must use AnimatedContentTransitionScope with NavBackStackEntry
private val defaultEnter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = {
    fadeIn(animationSpec = tween(500))
}

private val defaultExit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = {
    slideOutHorizontally(targetOffsetX = { -300 }, animationSpec = tween(500))
}

private val defaultPopEnter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = {
    slideInHorizontally(initialOffsetX = { -300 }, animationSpec = tween(500))
}

private val defaultPopExit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = {
    fadeOut(animationSpec = tween(500))
}

// Content lambda uses AnimatedContentScope without generics
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.defaultComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = defaultEnter,
        exitTransition = defaultExit,
        popEnterTransition = defaultPopEnter,
        popExitTransition = defaultPopExit,
        content = content
    )
}
