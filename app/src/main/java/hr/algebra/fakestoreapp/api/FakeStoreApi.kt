package hr.algebra.fakestoreapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://fakestoreapi.com/"

interface FakeStoreApi {
    @GET("products")
     fun getProductList(): Call<List<ProductItem>>

    @GET("products/{product_id}")
     fun getProductDetail(@Path("product_id") id: String): Call<ProductItem>
}