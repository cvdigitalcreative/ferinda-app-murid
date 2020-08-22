package com.digitalcreative.appmurid.api

import com.digitalcreative.appmurid.data.model.Boolean
import com.digitalcreative.appmurid.data.response.BaseResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("murid/login/")
    suspend fun login(
        @Body
        body: RequestBody
    ): BaseResponse<Boolean>

}