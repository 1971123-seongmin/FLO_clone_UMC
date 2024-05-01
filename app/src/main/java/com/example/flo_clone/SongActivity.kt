package com.example.flo_clone

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.flo_clone.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    lateinit var binding : ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButton()
        setView()
        setPlayerStatus(isPlaying = true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(MainActivity.STRING_INTENT_KEY, binding.songTitleTv.text.toString())
        }
        setResult(Activity.RESULT_OK, intent)
        //intent.putExtra("title", binding.songTitleTv.text.toString())
        // 이동할 때 액티비티를 스택에서 제거
        //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        //startActivity(intent)
    }


    private fun setView() {
        binding.songTitleTv.text = intent.getStringExtra("title")
        binding.singerTitleTv.text = intent.getStringExtra("singerName")
    }

    private fun setButton() {
        var isPlaying = true // 초기 상태 설정
        binding.nuguBtnDownIb.setOnClickListener{
            finish()
        }
        binding.nuguBtnPlayIb.setOnClickListener {
            isPlaying = !isPlaying // 클릭할 때마다 상태 변경
            setPlayerStatus(isPlaying) // 변경된 상태에 따라 이미지 변경
        }
    }
    fun setPlayerStatus(isPlaying: Boolean) {
        if (isPlaying) {
            binding.nuguBtnPlayIb.setImageResource(R.drawable.nugu_btn_play_32)

        } else {
            binding.nuguBtnPlayIb.setImageResource(R.drawable.nugu_btn_pause_32)
        }
    }
}