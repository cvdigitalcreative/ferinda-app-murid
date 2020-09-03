package com.digitalcreative.appmurid.data.response

data class BaseResponse<T>(
    val status: String,
    val message: String,
    val data: T
)