package bandtec.adsa.fajan.app.continuada2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCachorros {

    @GET("cachorros")
    fun get() : Call<List<Cachorro>>

    @GET("cachorros/{id}")
    fun getById(@Path("id") id: Int) : Call<Cachorro>
}