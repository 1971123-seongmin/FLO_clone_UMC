package com.example.flo_clone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flo_clone.databinding.ActivityMainBinding
import com.example.flo_clone.ui.home.HomeFragment
import com.example.flo_clone.ui.locker.LockerFragment
import com.example.flo_clone.ui.look.LookFragment
import com.example.flo_clone.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding // 뷰 바인딩 함수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStartFragment()
        setBottomNavigation()
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