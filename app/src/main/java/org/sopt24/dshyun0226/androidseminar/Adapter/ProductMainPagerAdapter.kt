package org.sopt24.dshyun0226.androidseminar.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ProductMainPagerAdapter(fm: FragmentManager, private val num_frgment: Int): FragmentStatePagerAdapter(fm){
    override fun getItem(p0: Int): Fragment? {
         //To change body of created functions use File | Settings | File Templates.
        return when(p0){
            0 -> AllProductMainFragment()
            1 -> NewProductMainFragment()
            2 -> EndProductMainFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return num_frgment //To change body of created functions use File | Settings | File Templates.
    }
}