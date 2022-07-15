package com.example.musicapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MusicResponse(
    val results: List<MusicList>
)

@Parcelize
data class MusicList(
    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: Double,
    val previewUrl: String
) : Parcelable