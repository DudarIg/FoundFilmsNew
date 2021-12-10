package ru.dudar.findfilms.util

import android.location.Location

fun Location.toPrintString(): String {
    return "[$latitude, $longitude]"
}