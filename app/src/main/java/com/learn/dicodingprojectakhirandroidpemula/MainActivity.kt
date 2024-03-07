package com.learn.dicodingprojectakhirandroidpemula

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvProgrammingLanguages: RecyclerView
    private val list = ArrayList<ProgrammingLanguages>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvProgrammingLanguages = findViewById(R.id.rv_programming_languages)
        rvProgrammingLanguages.setHasFixedSize(true)

        list.addAll(getListProgrammingLanguages())
        showRecyclerList()

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.background)))
    }

    private fun getListProgrammingLanguages(): ArrayList<ProgrammingLanguages> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataCreator = resources.getStringArray(R.array.data_creator)
        val dataYear = resources.getStringArray(R.array.data_year)
        val dataWebsite = resources.getStringArray(R.array.data_website)
        val dataExcess = resources.getStringArray(R.array.data_excess)

        val listHero = ArrayList<ProgrammingLanguages>()
        for (i in dataName.indices) {
            val programming = ProgrammingLanguages(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataCreator[i], dataYear[i], dataWebsite[i], dataExcess[i])
            listHero.add(programming)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvProgrammingLanguages.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListProgrammingLanguagesAdapter(list)
        rvProgrammingLanguages.adapter = listHeroAdapter

        // Menambahkan event click pada item list
        listHeroAdapter.setOnItemClickCallback(object : ListProgrammingLanguagesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ProgrammingLanguages) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(programming: ProgrammingLanguages) {
        val moveIntent = Intent(this@MainActivity, DetailProgrammingActivity::class.java)
        moveIntent.putExtra(DetailProgrammingActivity.EXTRA_NAME, programming.name)
        moveIntent.putExtra(DetailProgrammingActivity.EXTRA_DESCRIPTION, programming.description)
        moveIntent.putExtra(DetailProgrammingActivity.EXTRA_PHOTO, programming.photo)
        moveIntent.putExtra(DetailProgrammingActivity.EXTRA_CREATOR, programming.creator)
        moveIntent.putExtra(DetailProgrammingActivity.EXTRA_YEAR, programming.year)
        moveIntent.putExtra(DetailProgrammingActivity.EXTRA_WEBSITE, programming.website)
        moveIntent.putExtra(DetailProgrammingActivity.EXTRA_EXCESS, programming.excess)
        startActivity(moveIntent)
    }

    // Menambahkan menu pada action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val moveIntentAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntentAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}