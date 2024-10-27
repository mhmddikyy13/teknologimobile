package com.example.aplikasikontak.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.aplikasikontak.R

data class Kontak(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val jenisKelamin: Int,
    @StringRes val hobbies: Int
)

val kontaks = listOf(
    Kontak(R.drawable.loro, R.string.name_1, R.string.jenis_kelamin_1, R.string.nomor_description_1),
    Kontak(R.drawable.siji, R.string.name_2, R.string.jenis_kelamin_2, R.string.nomor_description_2),
    Kontak(R.drawable.telu, R.string.name_3, R.string.jenis_kelamin_3, R.string.nomor_description_3),
    Kontak(R.drawable.papat, R.string.name_4, R.string.jenis_kelamin_4, R.string.nomor_description_4),
    Kontak(R.drawable.limo, R.string.name_5, R.string.jenis_kelamin_5, R.string.nomor_description_5),
    Kontak(R.drawable.enem, R.string.name_6, R.string.jenis_kelamin_6, R.string.nomor_description_6),
    Kontak(R.drawable.pitu, R.string.name_7, R.string.jenis_kelamin_7, R.string.nomor_description_7),
    Kontak(R.drawable.wolu, R.string.name_8, R.string.jenis_kelamin_8, R.string.nomor_description_8),
    Kontak(R.drawable.songo, R.string.name_9, R.string.jenis_kelamin_9, R.string.nomor_description_9),
)