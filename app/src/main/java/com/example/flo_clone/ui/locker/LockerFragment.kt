package com.example.flo_clone.ui.locker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flo_clone.R
import com.example.flo_clone.base.BaseFragment
import com.example.flo_clone.databinding.FragmentLockerBinding

class LockerFragment : BaseFragment<FragmentLockerBinding>(R.layout.fragment_locker) {

    override fun setLayout() {
        Log.d("로그", "hello")
    }

}