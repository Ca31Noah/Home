package ec.edu.espoch.arquitectura

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Conexion(context: Context?, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)")
        db.execSQL("CREATE TABLE apellido (id INTEGER PRIMARY KEY AUTOINCREMENT, apellido TEXT)")
        db.execSQL("CREATE TABLE sexo (id INTEGER PRIMARY KEY AUTOINCREMENT, sexo TEXT)")
        db.execSQL("CREATE TABLE vehiculo (id INTEGER PRIMARY KEY AUTOINCREMENT, vehiculo TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuario")
        db.execSQL("DROP TABLE IF EXISTS apellido")
        db.execSQL("DROP TABLE IF EXISTS sexo")
        db.execSQL("DROP TABLE IF EXISTS vehiculo")
        onCreate(db)
    }
}
