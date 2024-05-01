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
import com.google.android.material.tabs.TabLayoutMediator
import java.util.concurrent.locks.Lock

class LockerFragment : Fragment() {

    lateinit var binding: FragmentLockerBinding

    private val information = arrayListOf("저장한 곡", "음악파일")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(layoutInflater)

        setVPAdapter()

        return binding.root
    }

    private fun setVPAdapter() {
        val lockerVPAdapter = LockerVPAdapter(this)
        binding.lockerContentVp.adapter = lockerVPAdapter

        // TabLayout + ViewPager2 연결
        TabLayoutMediator(binding.lockerContentTb, binding.lockerContentVp) {
            tab, positon ->
            tab.text = information[positon]
        }.attach()
    }

}