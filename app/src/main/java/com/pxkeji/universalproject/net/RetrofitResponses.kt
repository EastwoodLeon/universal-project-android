package com.pxkeji.universalproject.net

open class BaseResponse {
    var success: Boolean? = false
    var msg: String? = ""
}

class LoginResponse : BaseResponse() {
    var data: LoginModel? = null
}