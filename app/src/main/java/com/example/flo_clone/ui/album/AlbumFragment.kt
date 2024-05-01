package com.example.flo_clone.ui.album

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flo_clone.MainActivity
import com.example.flo_clone.R
import com.example.flo_clone.databinding.FragmentAlbumBinding
import com.google.android.material.tabs.TabLayoutMediator


class AlbumFragment : Fragment() {

    lateinit var binding: FragmentAlbumBinding

    private val information = arrayListOf("수록곡", "상세정보", "영상")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(layoutInflater)

        setView()
        setButton()
        setVPAdapter()

        return binding.root
    }

    private fun setButton() {
        //뒤로가기 버튼 클릭
        binding.btnArrowBlackIb.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setVPAdapter() {
        val albumVPAdapter = AlbumVPAdapter(this)
        binding.albumContentVp.adapter = albumVPAdapter

        // TabLayout + ViewPager2 연결
        TabLayoutMediator(binding.albumContentTb, binding.albumContentVp) {
            tab, postion ->
            tab.text = information[postion]
        }.attach()
    }

    private fun setView() {
        val imgRes = arguments?.getInt("imgRes")
        binding.imgAlbumExpIv.setImageResource(imgRes!!)
        binding.albumTitleTv.text = arguments?.getString("title")
        binding.singerNameTv.text = arguments?.getString("singerName")
    }
}