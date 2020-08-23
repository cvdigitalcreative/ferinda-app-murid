package com.digitalcreative.appmurid.data.repository

import android.util.Log
import com.digitalcreative.appmurid.api.ApiService
import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.model.Student
import com.digitalcreative.appmurid.utils.Constants.CONNECTION_ERROR
import com.digitalcreative.appmurid.utils.Constants.STATUS_SUCCESS
import com.digitalcreative.appmurid.utils.Constants.UNKNOWN_ERROR
import okhttp3.MultipartBody
import java.net.ConnectException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(private val service: ApiService) {

    suspend fun login(emailNis: String, password: String): Result<Student> {
        return try {
            val body = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email_nis", emailNis)
                .addFormDataPart("password", password)
                .build()
            val response = service.login(body)
            if (response.status == STATUS_SUCCESS) {
                Result.Success(response.data)
            } else {
                Result.ErrorRequest(response.message)
            }
        } catch (e: ConnectException) {
            Log.e("NetworkRepository-Login", e.localizedMessage!!)
            Result.ErrorRequest(CONNECTION_ERROR)
        } catch (e: Exception) {
            Log.e("NetworkRepository-Login", e.localizedMessage!!)
            Result.ErrorRequest(UNKNOWN_ERROR)
        }
    }
}