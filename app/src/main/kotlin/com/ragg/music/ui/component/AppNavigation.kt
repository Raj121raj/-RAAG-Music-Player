/**
 * RAGG Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.ragg.music.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.ragg.music.ui.screens.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Immutable
private data class NavItemState(
    val isSelected: Boolean,
    val iconRes: Int
)

@Stable
private fun isRouteSelected(currentRoute: String?, screenRoute: String, navigationItems: List<Screens>): Boolean {
    if (currentRoute == null) return false
    if (currentRoute == screenRoute) return true
    if (navigationItems.any { it.route == screenRoute } &&
        currentRoute.startsWith("$screenRoute/")) return true

    // Fix: match the route template, not the resolved route
    if (screenRoute == "search_input" &&
        (currentRoute.startsWith("search/") || currentRoute == "search/{query}")) return true

    return false
}

@Composable
fun AppNavigationRail(
    navigationItems: List<Screens>,
    currentRoute: String?,
    onItemClick: (Screens, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    onSearchLongClick: (() -> Unit)? = null
) {
    val haptics = LocalHapticFeedback.current
    val viewConfiguration = LocalViewConfiguration.current

    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(vertical = 24.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        GlassSurface(
            modifier = Modifier.width(80.dp).fillMaxHeight(),
            cornerRadius = 32.dp,
            alpha = 0.15f
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.weight(1f))

        navigationItems.forEach { screen ->
            val isSelected = remember(currentRoute, screen.route) {
                isRouteSelected(currentRoute, screen.route, navigationItems)
            }
            val currentIsSelected by rememberUpdatedState(isSelected)
            val iconRes = remember(isSelected, screen) {
                if (isSelected) screen.iconIdActive else screen.iconIdInactive
            }

            val isSearchItem = screen == Screens.Search && onSearchLongClick != null
            val interactionSource = remember { MutableInteractionSource() }

            // Long press detection using InteractionSource
            if (isSearchItem) {
                LaunchedEffect(interactionSource) {
                    var isLongClick = false
                    interactionSource.interactions.collectLatest { interaction ->
                        when (interaction) {
                            is PressInteraction.Press -> {
                                isLongClick = false
                                delay(viewConfiguration.longPressTimeoutMillis)
                                isLongClick = true
                                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                onSearchLongClick.invoke()
                            }
                            is PressInteraction.Release -> {
                                if (!isLongClick) {
                                    onItemClick(screen, currentIsSelected)
                                }
                            }
                            is PressInteraction.Cancel -> {
                                isLongClick = false
                            }
                        }
                    }
                }
            }

            NavigationRailItem(
                selected = isSelected,
                onClick = {
                    if (!isSearchItem) {
                        onItemClick(screen, currentIsSelected)
                    }
                    // For search item, click is handled via InteractionSource
                },
                interactionSource = interactionSource,
                icon = {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = stringResource(screen.titleId)
                    )
                }
            )
        }

            Spacer(modifier = Modifier.weight(1f))
    }
}
}
}

@Composable
fun AppNavigationBar(
    navigationItems: List<Screens>,
    currentRoute: String?,
    onItemClick: (Screens, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    slimNav: Boolean = false,
    onSearchLongClick: (() -> Unit)? = null
) {
    val haptics = LocalHapticFeedback.current
    val viewConfiguration = LocalViewConfiguration.current

    // Floating Glass Pill Wrapper
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        GlassSurface(
            modifier = Modifier.fillMaxWidth(),
            cornerRadius = 32.dp,
            alpha = 0.15f
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (slimNav) 56.dp else 72.dp)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                navigationItems.forEach { screen ->
                    val isSelected = remember(currentRoute, screen.route) {
                        isRouteSelected(currentRoute, screen.route, navigationItems)
                    }
                    val currentIsSelected by rememberUpdatedState(isSelected)
                    val iconRes = remember(isSelected, screen) {
                        if (isSelected) screen.iconIdActive else screen.iconIdInactive
                    }

                    val isSearchItem = screen == Screens.Search && onSearchLongClick != null
                    val interactionSource = remember { MutableInteractionSource() }

                    // Long press detection using InteractionSource
                    if (isSearchItem) {
                        LaunchedEffect(interactionSource) {
                            var isLongClick = false
                            interactionSource.interactions.collectLatest { interaction ->
                                when (interaction) {
                                    is PressInteraction.Press -> {
                                        isLongClick = false
                                        delay(viewConfiguration.longPressTimeoutMillis)
                                        isLongClick = true
                                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                        onSearchLongClick.invoke()
                                    }
                                    is PressInteraction.Release -> {
                                        if (!isLongClick) {
                                            onItemClick(screen, currentIsSelected)
                                        }
                                    }
                                    is PressInteraction.Cancel -> {
                                        isLongClick = false
                                    }
                                }
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null, // Custom indication could go here
                                onClick = {
                                    if (!isSearchItem) {
                                        onItemClick(screen, currentIsSelected)
                                    }
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = iconRes),
                                contentDescription = stringResource(screen.titleId),
                                tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            if (!slimNav && isSelected) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Box(
                                    modifier = Modifier
                                        .size(4.dp)
                                        .background(
                                            MaterialTheme.colorScheme.primary,
                                            shape = CircleShape
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

