package hr.algebra.fakestoreapp.api

import android.content.Context
import android.util.Log
import hr.algebra.fakestoreapp.FakeStoreReceiver
import hr.algebra.fakestoreapp.framework.sendBroadcast
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

    println(productItems)
        context.sendBroadcast<FakeStoreReceiver>()
    }
}