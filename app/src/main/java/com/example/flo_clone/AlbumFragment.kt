package com.example.flo_clone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flo_clone.databinding.ActivitySongBinding
import com.example.flo_clone.databinding.FragmentAlbumBinding


class AlbumFragment : Fragment() {
    lateinit var binding: FragmentAlbumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAlbumBinding.inflate(layoutInflater)
        setView()
        return binding.root
    }
    private fun setView() {
        binding.albumTitleTv.text = arguments?.getString("title")
        binding.singerNameTv.text = arguments?.getString("singerName")
    }
}