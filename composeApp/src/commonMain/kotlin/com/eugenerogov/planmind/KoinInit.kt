package com.eugenerogov.planmind

import com.eugenerogov.planmind.di.repositoryModule
import com.eugenerogov.planmind.di.serviceApiModule
import com.eugenerogov.planmind.di.useCasesModule
import com.eugenerogov.planmind.di.viewModelModule
import org.koin.mp.KoinPlatformTools
import org.koin.core.context.startKoin

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
