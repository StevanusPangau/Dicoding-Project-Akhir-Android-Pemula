package com.learn.dicodingprojectakhirandroidpemula

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.learn.dicodingprojectakhirandroidpemula.databinding.ActivityDetailProgrammingBinding

class DetailProgrammingActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NAME = "extra_age"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_CREATOR = "extra_creator"
        const val EXTRA_YEAR = "extra_year"
        const val EXTRA_WEBSITE = "extra_website"
        const val EXTRA_EXCESS = "extra_excess"
    }

    private lateinit var binding: ActivityDetailProgrammingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProgrammingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)
        val photo = intent.getIntExtra(EXTRA_PHOTO, 0)
        val creator = intent.getStringExtra(EXTRA_CREATOR)
        val year = intent.getStringExtra(EXTRA_YEAR)
        val website = intent.getStringExtra(EXTRA_WEBSITE)
        val excess = intent.getStringExtra(EXTRA_EXCESS)

        binding.tvTitle.text = name
        binding.tvDescription.text = description
        binding.tvCreator.text = creator
        binding.tvYear.text = year
        binding.tvWebsite.text = website
        binding.tvExcess.text = excess
        binding.ivProgramming.setImageResource(photo)

        binding.actionShare.setOnClickListener {
            shareProgrammingInfo(name, description)
        }

        // Menambahkan tombol back dan menganti nama action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail $name"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.background)))
    }

    private fun shareProgrammingInfo(name: String?, description: String?) {
        val shareText = "$name\n\n$description"

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

}