package ru.dudar.findfilms

import android.app.Application
import ru.dudar.findfilms.data.database.FilmsDbRepo

class FoundFilmsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        FilmsDbRepo.initialize(this)
    }
}