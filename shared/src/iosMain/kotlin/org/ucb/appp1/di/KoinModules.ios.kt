package org.ucb.appp1.di

private var isKoinInitialized = false

fun initKoinIos() {
    if (!isKoinInitialized) {
        initKoin()
        isKoinInitialized = true
    }
}
