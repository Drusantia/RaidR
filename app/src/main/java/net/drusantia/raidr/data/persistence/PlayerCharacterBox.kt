package net.drusantia.raidr.data.persistence
//
//import android.content.Context
//import io.objectbox.BoxStore
//
//object PlayerCharacterBox {
//    private val LOCK = Any()
//    private var instance: BoxStore? = null
//
//    operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//        instance ?: init(context).also { instance = it }
//    }
//
//    private fun init(context: Context): BoxStore = MyObjectBox
//        .builder()
//        .androidContext(context.applicationContext)
//        .build()
//}