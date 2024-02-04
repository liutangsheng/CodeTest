package com.lts.codetest.repository

import com.lts.codetest.api.MusicApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * <pre>
 *     author : liutangsheng
 *     time   : 2024/02/04
 * </pre>
 */
object MusicRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://itunes.apple.com")
        .client(OkHttpClient.Builder().build())
        // 将返回的数据转换为String
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun getMusics() = retrofit.create(MusicApi::class.java).queryMusic("歌",200,"HK")
}