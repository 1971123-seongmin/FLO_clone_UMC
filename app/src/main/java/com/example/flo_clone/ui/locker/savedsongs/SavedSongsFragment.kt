package com.example.flo_clone.ui.locker.savedsongs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flo_clone.R
import com.example.flo_clone.databinding.FragmentSavedSongsBinding

class SavedSongsFragment : Fragment() {

    lateinit var binding: FragmentSavedSongsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedSongsBinding.inflate(layoutInflater)

        return binding.root
    }


}