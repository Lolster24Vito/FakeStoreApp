package hr.algebra.fakestoreapp.api

import android.content.ContentValues
import android.content.Context
import android.util.Log
import hr.algebra.fakestoreapp.FAKESTORE_PROVIDER_CONTENT_URI
import hr.algebra.fakestoreapp.FakeStoreReceiver
import hr.algebra.fakestoreapp.framework.sendBroadcast
import hr.algebra.fakestoreapp.handler.downloadImageAndStore
import hr.algebra.fakestoreapp.model.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FakeStoreFetcher(private val context: Context) {

    private var fakeStoreApi: FakeStoreApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        fakeStoreApi = retrofit.create(FakeStoreApi::class.java)
    }
    fun fetchItems() {
        val request = fakeStoreApi.getProductList()

        request.enqueue(object:Callback<List<ProductItem>>{
            override fun onResponse(
                call: Call<List<ProductItem>>,
                response: Response<List<ProductItem>>
            ) {
                response?.body()?.let { populateItems(it) }
            }

            override fun onFailure(call: Call<List<ProductItem>>, t: Throwable) {
                Log.e(javaClass.name, t.toString(), t)
            }

        })


    }

    private fun populateItems(productItems: List<ProductItem>) {
        GlobalScope.launch {
            productItems.forEach {
                var picturePath = downloadImageAndStore(context, it.image)
                val values = ContentValues().apply {
                    put(Item::title.name, it.title)
                    put(Item::price.name, it.price)
                    put(Item::picturePath.name, picturePath)
                    put(Item::description.name, it.description)
                    put(Item::rating.name, it.rating.rate)
                }
                context.contentResolver.insert(FAKESTORE_PROVIDER_CONTENT_URI, values)
            }
            context.sendBroadcast<FakeStoreReceiver>()
        }
    }
}