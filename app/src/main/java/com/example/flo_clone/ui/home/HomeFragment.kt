package com.example.flo_clone.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.flo_clone.ui.album.AlbumFragment
import com.example.flo_clone.MainActivity
import com.example.flo_clone.R
import com.example.flo_clone.databinding.FragmentHomeBinding
import com.example.flo_clone.ui.home.banner.BannerFragment
import com.example.flo_clone.ui.home.banner.BannerVPAdapter
import com.example.flo_clone.ui.home.panel.PanelVPAdapter

data class SongPractice(val title: String, val singerName: String)

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding // 뷰 바인딩

    var currentPage = 0

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
        setVPAdapter()
        setIndicator()

        runTimerThread()

        return binding.root
    }

    // ViewPager2 Adapter 설정 함수
    private fun setVPAdapter() {
        // 패널
        var panelVPAdapter = PanelVPAdapter(this)
        binding.homePanelVp.adapter = panelVPAdapter
        binding.homePanelVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // 배너
        val bannerVPAdapter = BannerVPAdapter(this)
        bannerVPAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerVPAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        bannerVPAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerVPAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        binding.homeBannerVp.adapter = bannerVPAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    // Panel ViewPager2 에 대한 circleIndicatior 설정
    private fun setIndicator() {
        val circleIndicatior3 =  binding.circleIndicatior3
        circleIndicatior3.setViewPager(binding.homePanelVp)
        circleIndicatior3.createIndicators(7,0)
    }

    // 타이머 스레드 실행
    private fun runTimerThread() {
        val thread = Thread(PagerRunnable())
        thread.start()
    }

    // UI 스레드 작업 수행을 위한 핸들러
    val handler= Handler(Looper.getMainLooper()){
        setPage()
        true
    }

    // 일정 시간 마다 페이지 자동 넘기기
    inner class PagerRunnable: Runnable {
        override fun run() {
            while (true) {
                try {
                    Thread.sleep(2000)
                    handler.sendEmptyMessage(0)
                } catch (e: InterruptedException) {
                    Log.e("로그", "InterruptedException")
                }
            }
        }
    }

    //페이지 변경하기
    fun setPage(){
        if(currentPage == 7)
            currentPage = 0
        binding.homePanelVp.setCurrentItem(currentPage, true)
        currentPage+=1
    }

    private fun setOnClickView() {
        val albumImg = binding.albumIvImg1
        albumImg.setOnClickListener {
            val imgRes = R.drawable.lilac_album_cover
            val song = SongPractice(binding.tvAlbumName1.text.toString(), binding.tvAlbumSinger1.text.toString()) // 데이터 설정
            val albumFragment = AlbumFragment().apply {
                arguments = Bundle().apply {
                    putInt("imgRes", imgRes)
                    putString("title", song.title)
                    putString("singerName", song.singerName)
                }
            }
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, albumFragment).commitAllowingStateLoss()
        }
    }
}