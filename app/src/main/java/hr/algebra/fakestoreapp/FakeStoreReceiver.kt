package hr.algebra.fakestoreapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.fakestoreapp.framework.*

class FakeStoreReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //context.setBooleanPreference(DATA_IMPORTED)
        context.startActivity<MainHostActivity>()
    }
}