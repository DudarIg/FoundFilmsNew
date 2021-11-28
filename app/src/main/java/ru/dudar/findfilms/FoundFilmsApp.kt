package ru.dudar.findfilms

import android.app.Application
import ru.dudar.findfilms.domain.repoDataBase.FilmsDbRepo

class FoundFilmsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        FilmsDbRepo.initialize(this)
    }
}