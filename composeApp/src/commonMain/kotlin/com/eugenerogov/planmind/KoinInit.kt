package com.eugenerogov.planmind

import com.eugenerogov.planmind.di.repositoryModule
import com.eugenerogov.planmind.di.serviceApiModule
import com.eugenerogov.planmind.di.useCasesModule
import com.eugenerogov.planmind.di.viewModelModule
import org.koin.mp.KoinPlatformTools
import org.koin.core.context.startKoin

/**
 * Call [initKoin] once on each platform before using any dependency from Koin.
 * For iOS, call it from Swift (for example, inside `@main` App init or `SceneDelegate`).
 */
fun initKoin() {
    // Avoid starting Koin twice (will throw an exception)
    if (KoinPlatformTools.defaultContext().getOrNull() != null) return

    startKoin {
        modules(
            repositoryModule,
            serviceApiModule,
            useCasesModule,
            viewModelModule
        )
    }
}
