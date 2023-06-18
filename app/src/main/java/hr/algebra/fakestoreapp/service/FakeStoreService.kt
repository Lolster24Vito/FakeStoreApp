package hr.algebra.fakestoreapp.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.JobIntentService
import hr.algebra.fakestoreapp.api.FakeStoreFetcher

private const val JOB_ID=1
@Suppress("DEPRECATION")
class FakeStoreService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        FakeStoreFetcher(this).fetchItems()
    }
    companion object {
        fun enqueue(context: Context) {
            enqueueWork(context, FakeStoreService::class.java, JOB_ID,
                Intent(context, FakeStoreService::class.java))
        }
    }
}