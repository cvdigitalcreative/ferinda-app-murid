package com.digitalcreative.appmurid.data.repository

import android.util.Log
import com.digitalcreative.appmurid.api.ApiService
import com.digitalcreative.appmurid.data.Result
import com.digitalcreative.appmurid.data.model.Agenda
import com.digitalcreative.appmurid.data.model.Assignment
import com.digitalcreative.appmurid.data.model.Student
import com.digitalcreative.appmurid.utils.helper.Constants.CONNECTION_ERROR
import com.digitalcreative.appmurid.utils.helper.Constants.STATUS_SUCCESS
import com.digitalcreative.appmurid.utils.helper.Constants.UNKNOWN_ERROR
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
            Log.e("NetworkRequest", "Login -> ${e.localizedMessage}")
            Result.ErrorRequest(CONNECTION_ERROR)
        } catch (e: Exception) {
            Log.e("NetworkRequest", "Login -> ${e.localizedMessage}")
            Result.ErrorRequest(UNKNOWN_ERROR)
        }
    }

    suspend fun getAllAssignment(studentId: String, classId: String): Result<List<Assignment>> {
        return try {
            val response = service.getAllAssignment(studentId, classId)
            if (response.status == STATUS_SUCCESS) {
                Result.Success(response.data)
            } else {
                Result.ErrorRequest(response.message)
            }
        } catch (e: ConnectException) {
            Log.e("NetworkRequest", "GetAllAssignment -> ${e.localizedMessage}")
            Result.ErrorRequest(CONNECTION_ERROR)
        } catch (e: Exception) {
            Log.e("NetworkRequest", "GetAllAssignment -> ${e.localizedMessage}")
            Result.ErrorRequest(UNKNOWN_ERROR)
        }
    }

    suspend fun getAssignmentQuestion(
        studentId: String,
        classId: String,
        assignmentId: String
    ): Result<List<Assignment.Section>> {
        return try {
            val response = service.getAssignmentQuestion(studentId, classId, assignmentId)
            if (response.status == STATUS_SUCCESS) {
                Result.Success(response.data)
            } else {
                Result.ErrorRequest(response.message)
            }
        } catch (e: ConnectException) {
            Log.e("NetworkRequest", "GetAssignmentQuestion -> ${e.localizedMessage}")
            Result.ErrorRequest(CONNECTION_ERROR)
        } catch (e: Exception) {
            Log.e("NetworkRequest", "GetAssignmentQuestion -> ${e.localizedMessage}")
            Result.ErrorRequest(UNKNOWN_ERROR)
        }
    }

    suspend fun sendAssignmentAnswer(
        studentId: String,
        classId: String,
        assignmentId: String,
        questions: String,
        answers: String
    ): Result<String> {
        return try {
            val body = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id_soal", questions)
                .addFormDataPart("id_pilihan_jawaban", answers)
                .build()
            val response = service.sendAssignmentAnswer(studentId, classId, assignmentId, body)
            if (response.status == STATUS_SUCCESS) {
                Result.Success(response.message)
            } else {
                Result.ErrorRequest(response.message)
            }
        } catch (e: ConnectException) {
            Log.e("NetworkRequest", "SendAssignmentAnswer -> ${e.localizedMessage}")
            Result.ErrorRequest(CONNECTION_ERROR)
        } catch (e: Exception) {
            Log.e("NetworkRequest", "SendAssignmentAnswer -> ${e.localizedMessage}")
            Result.ErrorRequest(UNKNOWN_ERROR)
        }
    }

    suspend fun getAllAgenda(): Result<List<Agenda>> {
        return try {
            val response = service.getAllAgenda()
            if (response.status == STATUS_SUCCESS) {
                Result.Success(response.data)
            } else {
                Result.ErrorRequest(response.message)
            }
        } catch (e: ConnectException) {
            Log.e("NetworkRequest", "GetAllAgenda -> ${e.localizedMessage}")
            Result.ErrorRequest(CONNECTION_ERROR)
        } catch (e: Exception) {
            Log.e("NetworkRequest", "GetAllAgenda -> ${e.localizedMessage}")
            Result.ErrorRequest(UNKNOWN_ERROR)
        }
    }
}