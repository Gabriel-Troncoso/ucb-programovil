package org.ucb.appp1.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
): KoinApplication = startKoin {
    appDeclaration()
    modules(sharedModules())
}
