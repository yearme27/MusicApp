package com.example.musicapp.views

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapp.data.MusicList
import com.example.musicapp.databinding.MusicListItemBinding


class MusicAdapter(
    private val list: List<MusicList>,
): RecyclerView.Adapter<MusicAdapter.MusicListViewHolder>() {
    inner class MusicListViewHolder(private val binding: MusicListItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
            fun onBind(musicList: MusicList){
                binding.tvCollectionName.text = musicList.collectionName
                binding.tvArtistName.text = musicList.artistName
                binding.tvTrackPrice.text = musicList.trackPrice.toString()


                binding.root.setOnClickListener {

                    val url = musicList.previewUrl // your URL here
                    val mediaPlayer = MediaPlayer().apply {
                        setAudioAttributes(
                            AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                        )
                        setDataSource(url)
                        prepare() // might take long! (for buffering, etc)
                        start()

                        println("Music is Playing ${musicList.previewUrl}")

                        binding.root.setOnClickListener{
                            pause()
                            println("Music is Stopping ${musicList.previewUrl}")
                        }


                    }


//                    val intent = Intent()
//                    intent.action = Intent.ACTION_VIEW
//                    intent.setDataAndType(Uri.parse(musicList.previewUrl), "audio/*")
//                    startActivity(intent)


                }


                Glide.with(binding.ivArtwork)
                    .load(musicList.artworkUrl60)
                    .into(binding.ivArtwork)
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicListViewHolder {
        return MusicListViewHolder(
            MusicListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MusicListViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size


}