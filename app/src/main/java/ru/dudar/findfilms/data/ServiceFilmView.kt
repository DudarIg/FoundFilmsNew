package ru.dudar.findfilms.data

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.io.File
import ru.dudar.findfilms.data.Film as Film

class ServiceFilmView : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val data = intent?.getSerializableExtra("film") as Film
        Thread {
            val path = this.getFilesDir()
            val file = File(path, "FilmsView.txt")
            file.appendText("${data!!.style} ${data!!.title}\n")
            stopSelf(startId)
        }.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}