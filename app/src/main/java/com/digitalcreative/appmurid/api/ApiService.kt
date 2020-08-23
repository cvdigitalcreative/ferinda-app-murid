package com.digitalcreative.appmurid.api

import com.digitalcreative.appmurid.data.model.Assignment
import com.digitalcreative.appmurid.data.model.Student
import com.digitalcreative.appmurid.data.response.BaseResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("murid/login/")
    suspend fun login(
        @Body
        body: RequestBody
    ): BaseResponse<Student>

    @GET("murid/{student_id}/kelas/{class_id}/tugas/")
    suspend fun getAllAssignment(
        @Path("student_id")
        studentId: String,

        @Path("class_id")
        classId: String
    ): BaseResponse<List<Assignment>>
}