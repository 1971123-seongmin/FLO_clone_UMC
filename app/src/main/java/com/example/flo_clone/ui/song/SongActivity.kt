package com.example.flo_clone.ui.song

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.flo_clone.MainActivity
import com.example.flo_clone.R
import com.example.flo_clone.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    lateinit var binding : ActivitySongBinding
    lateinit var song: Song
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButton()
        initSong()
        setPlayer(song)

        setPlayerStatus(isPlaying = false)
    }

    private fun initSong() {
        if (intent.hasExtra("title") && intent.hasExtra("singer")) {
            song = Song(
                intent.getStringExtra("title")!!,
                intent.getStringExtra("singer")!!,
                intent.getIntExtra("second", 0),
                intent.getIntExtra("playTime", 0),
                intent.getBooleanExtra("isPlaying", false)
            )
        }
        startTimer()
    }

    private fun setPlayer(song: Song) {
        binding.songTitleTv.text = intent.getStringExtra("title")!!
        binding.singerTitleTv.text = intent.getStringExtra("singer")!!
        binding.startTimerTv.text = String.format("%02d:%02d", song.second / 60, song.second % 60)
        binding.endTimerTv.text = String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)
        binding.songProgressSb.progress = (song.second * 1000 / song.playTime)

        setPlayerStatus(song.isPlaying)
    }

    private fun startTimer() {
        timer = Timer(song.playTime, song.isPlaying)
        timer.start()
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

    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
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

        var isRepeat = true
        binding.nuguBtnRepeatInactiveIb.setOnClickListener{
            isRepeat = !isRepeat
            if (isRepeat) {
                binding.nuguBtnRepeatInactiveIb.setImageResource(R.drawable.nugu_btn_repeat_inactive)
            } else {
                binding.nuguBtnRepeatInactiveIb.setImageResource(R.drawable.nugu_btn_play_32)
            }
        }

        var isRandom = true
        binding.nuguBtnRandomInactiveIb.setOnClickListener{
            isRandom = !isRandom
            if (isRandom) {
                binding.nuguBtnRandomInactiveIb.setImageResource(R.drawable.nugu_btn_random_inactive)
            } else {
                binding.nuguBtnRandomInactiveIb.setImageResource(R.drawable.nugu_btn_play_32)
            }
        }

        var isLike = true
        binding.songLikeIv.setOnClickListener{
            isLike = !isLike
            if (isLike) {
                binding.songLikeIv.setImageResource(R.drawable.ic_my_like_on)
            } else {
                binding.songLikeIv.setImageResource(R.drawable.ic_my_like_off)
            }
        }

        var isUnLike = true
        binding.songUnlikeIv.setOnClickListener{
            isUnLike = !isUnLike
            if (isUnLike) {
                binding.songUnlikeIv.setImageResource(R.drawable.btn_player_unlike_on)
            } else {
                binding.songUnlikeIv.setImageResource(R.drawable.btn_player_unlike_off)
            }
        }
    }

    private fun setPlayerStatus(isPlaying: Boolean) {
        song.isPlaying = isPlaying
        timer.isPlaying = isPlaying

        if (!isPlaying) {
            binding.nuguBtnPlayIb.setImageResource(R.drawable.nugu_btn_play_32)

        } else {
            binding.nuguBtnPlayIb.setImageResource(R.drawable.nugu_btn_pause_32)
        }
    }

    inner class Timer(private val playTime: Int, var isPlaying: Boolean = true): Thread() {

        private var second: Int = 0
        private var mils: Float = 0f

        override fun run() {
            super.run()

            try {
                while (true) {

                    if (second >= playTime) {
                        break
                    }

                    if (isPlaying) {
                        sleep(50)
                        mils += 50

                        runOnUiThread {
                            binding.songProgressSb.progress = ((mils / playTime)*100).toInt()
                        }
                        if (mils % 1000 == 0f) {
                            runOnUiThread {
                                binding.startTimerTv.text = String.format("%02d:%02d", song.second / 60, song.second % 60)
                            }
                            second++
                        }
                    }
                }
            } catch (e:InterruptedException) {
                Log.d("Song", "스레드가 죽었습니다. ${e.message}")
            }

        }
    }

}