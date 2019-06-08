package org.sopt24.dshyun0226.androidseminar.Network.Post

data class PostLoginResponse(
        val status: Int,
        val success: Boolean,
        val message: String,
        val data: String? //null값을 가질 수 있게 물음표
)