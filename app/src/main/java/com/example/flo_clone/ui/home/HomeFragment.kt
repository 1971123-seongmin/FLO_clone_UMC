package com.example.flo_clone.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo_clone.AlbumFragment
import com.example.flo_clone.MainActivity
import com.example.flo_clone.R
import com.example.flo_clone.databinding.FragmentHomeBinding

data class Song(val title: String, val singerName: String)

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding // 뷰 바인딩

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setOnClickView()
        return binding.root
    }

    private fun setOnClickView() {
        val albumImg = binding.albumIvImg1
        albumImg.setOnClickListener {
            val song = Song("Album Name", "Artist Name") // 데이터 설정
            val albumFragment = AlbumFragment().apply {
                arguments = Bundle().apply {
                    putString("title", song.title)
                    putString("singerName", song.singerName)
                }
            }
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, albumFragment).commitAllowingStateLoss()
        }
    }
}