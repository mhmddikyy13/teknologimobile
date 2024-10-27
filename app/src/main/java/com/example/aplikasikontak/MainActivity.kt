@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.aplikasikontak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.aplikasikontak.data.Kontak
import com.example.aplikasikontak.data.kontaks
import com.example.aplikasikontak.ui.theme.AplikasiKontakTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplikasiKontakTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AplikasiKontakApp()
                }
            }
        }
    }
}

/**
 * Fungsi Composable yang menampilkan aplikasi utama.
 */
@Composable
fun AplikasiKontakApp() {
    // Scaffold menyediakan struktur dasar untuk layout aplikasi.
    Scaffold(
        topBar = {
            AplikasiKontakTopAppBar()
        }
    ) {
        // LazyColumn menampilkan daftar kontak.
        LazyColumn(contentPadding = it) {
            items(kontaks) { kontak ->
                // Menampilkan setiap item kontak.
                KontakItem(
                    kontak = kontak,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

/**
 * Fungsi Composable yang menampilkan item kontak.
 *
 * @param kontak Data kontak yang akan ditampilkan.
 * @param modifier Modifier untuk menyesuaikan tampilan item kontak.
 */
@Composable
fun KontakItem(
    kontak: Kontak,
    modifier: Modifier = Modifier
) {
    // State untuk mengontrol status perluasan item.
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                // Menampilkan ikon kontak.
                KontakIcon(kontak.imageResourceId)
                // Menampilkan informasi kontak (nama dan jenis kelamin).
                KontakInformation(kontak.name, kontak.jenisKelamin)
                Spacer(Modifier.weight(1f))
                // Menampilkan tombol perluasan/penciutan.
                KontakItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            // Menampilkan detail kontak jika item diperluas.
            if (expanded) {
                KontakHobby(
                    kontak.hobbies, modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

/**
 * Fungsi Composable yang menampilkan tombol perluasan/penciutan item kontak.
 *
 * @param expanded Status perluasan item (true jika diperluas, false jika diciutkan).
 * @param onClick Fungsi yang dipanggil saat tombol diklik.
 * @param modifier Modifier untuk menyesuaikan tampilan tombol.
 */
@Composable
private fun KontakItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        // Menampilkan ikon ExpandLess atau ExpandMore tergantung pada status perluasan.
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

/**
 * Fungsi Composable yang menampilkan TopAppBar aplikasi.
 *
 * @param modifier Modifier untuk menyesuaikan tampilan TopAppBar.
 */
@Composable
fun AplikasiKontakTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Menampilkan judul aplikasi.
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

/**
 * Fungsi Composable yang menampilkan ikon kontak.
 *
 * @param dogIcon ID resource gambar ikon kontak.
 * @param modifier Modifier untuk menyesuaikan tampilan ikon.
 */
@Composable
fun KontakIcon(
    @DrawableRes dogIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(dogIcon),
        contentDescription = null // Deskripsi konten tidak diperlukan di sini - gambar dekoratif.
    )
}

/**
 * Fungsi Composable yang menampilkan informasi kontak (nama dan jenis kelamin).
 *
 * @param dogName ID resource string nama kontak.
 * @param jenisKelamin ID resource string jenis kelamin kontak.
 * @param modifier Modifier untuk menyesuaikan tampilan informasi kontak.
 */
@Composable
fun KontakInformation(
    @StringRes dogName: Int,
    @StringRes jenisKelamin: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Menampilkan nama kontak.
        Text(
            text = stringResource(dogName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        // Menampilkan jenis kelamin kontak.
        Text(
            text = stringResource(jenisKelamin),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

/**
 * Fungsi Composable yang menampilkan detail kontak (nomor telepon).
 *
 * @param dogHobby ID resource string detail kontak.
 * @param modifier Modifier untuk menyesuaikan tampilan detail kontak.
 */
@Composable
fun KontakHobby(
    @StringRes dogHobby: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        // Menampilkan label "Nomor:".
        Text(
            text = stringResource(R.string.nomor),
            style = MaterialTheme.typography.labelSmall
        )
        // Menampilkan nomor telepon kontak.
        Text(
            text = stringResource(dogHobby),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

/**
 * Fungsi Composable untuk pratinjau aplikasi dalam tema terang.
 */
@Preview
@Composable
fun AplikasiKontakPreview() {
    AplikasiKontakTheme {
        AplikasiKontakApp()
    }
}

/**
 * Fungsi Composable untuk pratinjau aplikasi dalam tema gelap.
 */
@Preview
@Composable
fun AplikasiKontakDarkThemePreview() {
    AplikasiKontakTheme(darkTheme = true) {
        AplikasiKontakApp()
    }
}