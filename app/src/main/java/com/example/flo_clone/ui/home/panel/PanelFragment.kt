package com.example.flo_clone.ui.home.panel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flo_clone.R
import com.example.flo_clone.databinding.FragmentPanelBinding

class PanelFragment : Fragment() {
    lateinit var binding: FragmentPanelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPanelBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


}