package com.example.musicapp.views

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.musicapp.data.MusicResponse
import com.example.musicapp.databinding.FragmentMusicListBinding
import com.example.musicapp.network.MusicService
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicListFragment(
    private var term: String ="rock"
): Fragment() {
    lateinit var binding: FragmentMusicListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicListBinding.inflate(layoutInflater)
//        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                when (tab?.position) {
//                    0 -> updateTabText(0)
//                    1 -> updateTabText(1)
//                    2 -> updateTabText(2)
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {}
//            override fun onTabReselected(tab: TabLayout.Tab?) {}
//        })


        getMusicList(term)

        binding.btnClassic.setOnClickListener{
            term = "classic"
            binding.btnClassic.background.setTint(Color.BLUE)
            binding.btnPop.background.setTint(Color.WHITE)
            binding.btnRock.background.setTint(Color.WHITE)
            getMusicList(term)
        }
        binding.btnPop.setOnClickListener{
            term = "pop"
            binding.btnPop.background.setTint(Color.BLUE)
            binding.btnRock.background.setTint(Color.WHITE)
            binding.btnClassic.background.setTint(Color.WHITE)
            getMusicList(term)
        }
        binding.btnRock.setOnClickListener{
            term = "rock"
            binding.btnRock.background.setTint(Color.BLUE)
            binding.btnClassic.background.setTint(Color.WHITE)
            binding.btnClassic.background.setTint(Color.WHITE)


            getMusicList(term)
        }






        return binding.root
    }

    private fun getMusicList(term: String){
        MusicService.getRetrofitInstance()
            ?.create(MusicService::class.java)
            ?.getMusic(term)?.enqueue(object: Callback<MusicResponse> {
                override fun onResponse(
                    Call: Call<MusicResponse>,
                    response: Response<MusicResponse>
                ){
                    if(response.isSuccessful){
                        val musicAdapter = MusicAdapter(response.body()!!.results)
                        binding.rvMusicList.adapter = musicAdapter
                    }
                    else {
                        println(response.toString())
                    }
                }

                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {

                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }


            })

    }
}