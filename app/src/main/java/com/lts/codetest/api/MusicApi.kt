package com.lts.codetest.api

import com.lts.codetest.data.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * <pre>
 *     author : liutangsheng
 *     time   : 2024/02/04
 * </pre>
 */
interface MusicApi {

    @GET("search")
    suspend fun queryMusic(
        @Query("term") term: String,
        @Query("limit") limit: Int,
        @Query("country") country: String
    ): Response
}