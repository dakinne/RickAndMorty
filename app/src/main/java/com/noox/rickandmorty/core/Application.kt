package com.noox.rickandmorty.core

import com.noox.rickandmorty.BuildConfig
import com.noox.rickandmorty.character.di.characterModule
import com.noox.rickandmorty.core.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application: android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@Application)
            modules(
                coreModule,
                characterModule
            )
        }
    }

}
