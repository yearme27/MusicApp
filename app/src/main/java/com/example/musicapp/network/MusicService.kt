package com.example.musicapp.network

import com.example.musicapp.data.MusicResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {
    @GET("/search")
    fun getMusic(
        @Query("term") term: String,
        @Query("media") media: String = "&music&",
        @Query("entity") entity: String = "song",
        @Query("limit") limit: Int = 50
    ): Call<MusicResponse>


    companion object {
        private var retrofit: Retrofit? = null

        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://itunes.apple.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

    }

}