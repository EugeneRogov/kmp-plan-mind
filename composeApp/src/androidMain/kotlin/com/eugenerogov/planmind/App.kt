package com.eugenerogov.planmind

import android.app.Application
import com.eugenerogov.planmind.di.serviceApiModule
import com.eugenerogov.planmind.di.repositoryModule
import com.eugenerogov.planmind.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // region Koin
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    useCasesModule,
                    repositoryModule,
                    serviceApiModule,
                )
            )
        }
        // endregion
    }
}
