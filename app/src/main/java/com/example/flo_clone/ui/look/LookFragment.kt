package com.example.flo_clone.ui.look

import android.util.Log
import com.example.flo_clone.R
import com.example.flo_clone.base.BaseFragment
import com.example.flo_clone.databinding.FragmentLookBinding

class LookFragment : BaseFragment<FragmentLookBinding>(R.layout.fragment_look) {

    override fun setLayout() {
        Log.d("로그", "hello")
    }

}