package com.eugenerogov.planmind.di

import com.eugenerogov.planmind.viewmodel.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val androidAppModule =
    module {
        viewModel {
            HomeViewModel()
        }
    }
