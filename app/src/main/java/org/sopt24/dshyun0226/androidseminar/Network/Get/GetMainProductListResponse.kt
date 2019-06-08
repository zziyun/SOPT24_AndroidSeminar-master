package org.sopt24.dshyun0226.androidseminar.Network.Get

import org.sopt24.dshyun0226.androidseminar.Data.ProductOverviewData

data class GetMainProductListResponse(
        val status: Int,
        val success: Boolean,
        val message: String,
        val data: ArrayList<ProductOverviewData>?
                //오류가 날때는 data값이 전달되지 않아 그래서 null값이 올 수도 있다는 뜻에서 '?'붙임
)