package com.example.flo_clone

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.flo_clone.databinding.ActivityMainBinding
import com.example.flo_clone.ui.home.HomeFragment
import com.example.flo_clone.ui.locker.LockerFragment
import com.example.flo_clone.ui.look.LookFragment
import com.example.flo_clone.ui.search.SearchFragment
import com.example.flo_clone.ui.song.Song
import com.example.flo_clone.ui.song.SongActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding // 뷰 바인딩 함수

    companion object {
        const val STRING_INTENT_KEY = "my_string_key"
        const val TAG = "MainActivity"
    }
    private val getResultText = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val returnString = result.data?.getStringExtra(MainActivity.STRING_INTENT_KEY)
            returnString?.let { makeToastMsg(it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStartFragment()
        setBottomNavigation()
        changeActivity()
    }
    private fun makeToastMsg(msg: String) {
        Toast.makeText(this, "${msg}", Toast.LENGTH_SHORT).show()
    }

    private fun changeActivity() {
        val song = Song(binding.playerTitleTv.text.toString(), binding.playerSingerTv.text.toString(),
            0, 60, false)

        binding.mainPlayer.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java)
            //intent.putExtra("title", binding.playerTitleTv.text.toString())
            //intent.putExtra("singer", binding.playerSingerTv.text.toString())
            intent.putExtra("title", song.title)
            intent.putExtra("singer", song.singer)
            intent.putExtra("second", song.second)
            intent.putExtra("playTime", song.playTime)
            intent.putExtra("isPlaying", song.isPlaying)
            getResultText.launch(intent) // Song 액티비티를 시작하고 결과 처리 콜백 호출
        }
    }

    private fun setStartFragment() {
        val homeFragment = HomeFragment() // 홈 프래그먼트 생성

        // 시작 프래그먼트 생성
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout, homeFragment).commit()
    }

    // 바텀네비게이션 뷰를 설정하는 함수
    // menu 파일의 home_navigation_menu 파일 사용
    // (android:icon="@drawable/click_look_img" 선택되면 이미지 변경) drawable의 click_look_img.xml 파일 (아이템마다 다름)
    // drawable 파일의 btm_color_xml 파일 -> 선택되면 글자 파랑색 x 이면 글자 검정색으로 설정하는 파일
    private fun setBottomNavigation() {
        val bottomNavigationView = binding.bottomNav // 바텀네비게이션 뷰 변수에 설정 (뷰 바인딩)
        bottomNavigationView.itemIconTintList = null // 바텀네비게이션 뷰 스타일 x -> 클릭시 클릭이미지, 색으로 변하게 하기 위해

        // 바텀 네비게이션 뷰의 아이템 선택 리스너
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.fragment_home -> { // 홈 프래그먼트가 선택되면 현재 프래그먼트를 홈 프래그먼트로 변경 (이하 동일)
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,
                        HomeFragment()).commit()
                    true
                }
                R.id.fragment_look -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,
                        LookFragment()).commit()
                    true
                }
                R.id.fragment_serach -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,
                        SearchFragment()).commit()
                    true
                }
                R.id.fragment_locker -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,
                        LockerFragment()).commit()
                    true
                }
                else -> false// when 문의 else
            }

        }
    }

}