package org.ucb.appp1

import androidx.compose.ui.window.ComposeUIViewController
import org.ucb.appp1.di.initKoinIos

fun MainViewController() = ComposeUIViewController {
    initKoinIos()
    App()
}
