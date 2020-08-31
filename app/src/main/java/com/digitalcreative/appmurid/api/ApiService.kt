package com.digitalcreative.appmurid.api

import com.digitalcreative.appmurid.data.model.*
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

    @GET("murid/{student_id}/kelas/{class_id}/tugas/{assignment_id}/soal/")
    suspend fun getAssignmentQuestion(
        @Path("student_id")
        studentId: String,

        @Path("class_id")
        classId: String,

        @Path("assignment_id")
        assignmentId: String
    ): BaseResponse<List<Assignment.Section>>

    @POST("murid/{student_id}/kelas/{class_id}/tugas/{assignment_id}/jawab/")
    suspend fun sendAssignmentAnswer(
        @Path("student_id")
        studentId: String,

        @Path("class_id")
        classId: String,

        @Path("assignment_id")
        assignmentId: String,

        @Body
        body: RequestBody
    ): BaseResponse<Nothing>

    @GET("murid/agenda/")
    suspend fun getAllAgenda(): BaseResponse<List<Agenda>>

    @GET("murid/{student_id}")
    suspend fun getDetailProfile(
        @Path("student_id")
        studentId: String
    ): BaseResponse<Profile>

    @GET("murid/{student_id}/rapot/semester/{semester_id}")
    suspend fun getRaport(
        @Path("student_id")
        studentId: String,

        @Path("semester_id")
        semesterId: String
    ): BaseResponse<Raport>
}