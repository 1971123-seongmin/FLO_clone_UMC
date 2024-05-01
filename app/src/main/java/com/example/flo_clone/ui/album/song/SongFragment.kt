package com.example.flo_clone.ui.album.song

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo_clone.R
import com.example.flo_clone.databinding.FragmentSongBinding

class SongFragment : Fragment() {
    lateinit var binding : FragmentSongBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)
        setButton()
        return binding.root
    }

    private fun setButton() {
        var isSelect = true
        binding.songMixoffTg.setOnClickListener{
            isSelect = !isSelect
            setToggleStatus(isSelect)
        }
    }

    // 내 취향 Mix 버튼 클릭하면 이미지 변경
    private fun setToggleStatus(isSelect: Boolean) {
        if (isSelect) {
            binding.songMixoffTg.setImageResource(R.drawable.btn_toggle_off)
        } else {
            binding.songMixoffTg.setImageResource(R.drawable.btn_toggle_on)
        }
    }
}