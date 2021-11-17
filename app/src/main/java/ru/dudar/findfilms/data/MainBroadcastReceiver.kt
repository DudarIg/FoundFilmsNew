package ru.dudar.findfilms.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MainBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        StringBuilder().apply {
            append("Сообщение: ")
            append("Изменения в работе сети!")
            toString().also {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

}