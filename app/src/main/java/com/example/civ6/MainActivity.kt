package com.example.civ6


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random.Default.nextInt

private const val TAG = "MainActivity"

var civilizationObjectList = arrayListOf<Civilization>()

class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickListener{
    private var randomChoiceList = arrayListOf<Civilization>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        civilizationObjectList = civilizationsObjectListCreator(resources.getStringArray(R.array.civilization_list),
                resources.getStringArray(R.array.civilizations_leaders))

        btn_start_random_choice.setOnClickListener {
            val randomChoiceList = randomChoice(civilizationObjectList)

            Log.i(TAG, "$randomChoiceList")
            this.randomChoiceList = randomChoiceList

            try {
                rv_main.layoutManager = GridLayoutManager(this,
                        if (tv_numbers_of_cv.text.toString().toInt() < 3) 1 else 2)
                rv_main.adapter = MainAdapter(randomChoiceList, listener = this, R.layout.item_for_main_adapter)
                rv_main.setHasFixedSize(true)
                Log.i(TAG, "Try create recyclerview")
            } catch (e: Exception) {
                Toast.makeText(this, "Exception on create", Toast.LENGTH_SHORT).show()
            }

        }

        btn_ban_list.setOnClickListener {
        try {
            val intent = Intent(this@MainActivity, BanList::class.java)
            intent.putExtra("civilizationObjList", civilizationObjectList)
            startActivity(intent)

        }catch (e: Exception){
            Toast.makeText(this, "Exception on create", Toast.LENGTH_SHORT).show()
        }

        }

    }

    fun increase (@Suppress("UNUSED_PARAMETER")view: View) {
        if (tv_numbers_of_cv.text.toString().toInt() < 6)
            tv_numbers_of_cv.text = (tv_numbers_of_cv.text.toString().toInt() + 1).toString()
    }

    fun decrease (@Suppress("UNUSED_PARAMETER")view: View) {
        if (tv_numbers_of_cv.text.toString().toInt() > 1)
            tv_numbers_of_cv.text = (tv_numbers_of_cv.text.toString().toInt() - 1).toString()
    }

    override fun onItemClick(position: Int) {
        val clickedItem = randomChoiceList[position]
        val intent = Intent(this@MainActivity, CivilizationDescription::class.java)

        intent.putExtra("civilization", clickedItem)
        startActivity(intent)
    }

    private fun civilizationsObjectListCreator(civilizations: Array<String>,
                                               civilizationsLeaders: Array<String>): ArrayList<Civilization> {
        val civilizationsObjectList = arrayListOf<Civilization>()
        var civilizationImage: Int

        for (element in civilizations.indices) {
            civilizationImage = resources.getIdentifier(civilizations[element].toLowerCase(Locale.ROOT), "drawable", packageName)
            civilizationsObjectList.add(Civilization(civilizations[element], civilizationsLeaders[element], civilizationImage, false))
        }
        Log.i(TAG, "civilizationsObjectList created")
        return civilizationsObjectList
    }

    private fun randomChoice(civilizationObjectList: ArrayList<Civilization>): ArrayList<Civilization> {
        val civilizationToDisplay: ArrayList<Civilization> = arrayListOf()
        var numberOfRandomChosen: Int
        var nonBanedCivilization = 0

        for (element in civilizationObjectList.indices){
            if (!civilizationObjectList[element].civilizationBan){
                nonBanedCivilization++
            }
        }
        if (nonBanedCivilization >= tv_numbers_of_cv.text.toString().toInt()) {
            while (civilizationToDisplay.size != tv_numbers_of_cv.text.toString().toInt()) {
                numberOfRandomChosen = nextInt(civilizationObjectList.size)
                if (!civilizationToDisplay.contains(civilizationObjectList[numberOfRandomChosen]) && !civilizationObjectList[numberOfRandomChosen].civilizationBan) {
                    civilizationToDisplay.add(civilizationObjectList[numberOfRandomChosen])
                }
            }
        } else {
            Toast.makeText(this, "You baned to many civilizations", Toast.LENGTH_SHORT).show()
        }
        return civilizationToDisplay
    }

}