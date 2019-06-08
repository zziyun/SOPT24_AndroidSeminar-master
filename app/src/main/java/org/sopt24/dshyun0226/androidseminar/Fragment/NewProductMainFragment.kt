package org.sopt24.dshyun0226.androidseminar.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_all_product_main.*
import org.sopt24.dshyun0226.androidseminar.Adapter.ProductOverviewRecyclerViewAdapter
import org.sopt24.dshyun0226.androidseminar.Data.ProductOverviewData
import org.sopt24.dshyun0226.androidseminar.Network.ApplicationController
import org.sopt24.dshyun0226.androidseminar.Network.Get.GetMainProductListResponse
import org.sopt24.dshyun0226.androidseminar.Network.NetworkService

import org.sopt24.dshyun0226.androidseminar.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewProductMainFragment : Fragment() {
    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }
    lateinit var productOverviewRecyclerViewAdapter: ProductOverviewRecyclerViewAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var dataList: ArrayList<ProductOverviewData> = ArrayList()
/*        dataList.add(ProductOverviewData(
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                8,"신규작품 1",120,"신규작가 A"))
        dataList.add(ProductOverviewData(
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                9, "신규작품 2", 100, "신규작가 B"))
        dataList.add(ProductOverviewData(
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                10, "신규작품 3", 99, "신규작가 C"))
        dataList.add(ProductOverviewData(
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                11, "신규작품 4", 10, "신규작가 D"))
        dataList.add(ProductOverviewData(
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                12, "신규작품 5", 1, "신규작가 E"))
        dataList.add(ProductOverviewData(
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                13, "신규작품 6", 1, "신규작가 F"))
        dataList.add(ProductOverviewData(
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                14, "신규작품 7", 1, "신규작가 G"))
        dataList.add(ProductOverviewData(
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                15, "신규작품 8", 1, "신규작가 H"))*/
        productOverviewRecyclerViewAdapter = ProductOverviewRecyclerViewAdapter(context!!, dataList)
        rv_product_overview_all.adapter = productOverviewRecyclerViewAdapter
        rv_product_overview_all.layoutManager = GridLayoutManager(context!!, 3)

        getMainProductListResponse() //위에 리스트를 지우고 서버와의 통신을 통해 dataList를 Update

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_product_main, container, false)
    }
    private fun getMainProductListResponse(){
        val getMainProductListResponse = networkService.getMainProductListResponse(
                "application/json", 2)
        getMainProductListResponse.enqueue(object: Callback<GetMainProductListResponse> {
            override fun onFailure(call: Call<GetMainProductListResponse>, t: Throwable){
                Log.e("AllMainProductListFail", t.toString())
            }

            override  fun onResponse(
                    call: Call<GetMainProductListResponse>,
                    response: Response<GetMainProductListResponse>
            ){
                if(response.isSuccessful){
                    if(response.body()!!.status == 200){
                        val tmp: ArrayList<ProductOverviewData> = response.body()!!.data!!
                        productOverviewRecyclerViewAdapter.dataList = tmp
                        productOverviewRecyclerViewAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
}
