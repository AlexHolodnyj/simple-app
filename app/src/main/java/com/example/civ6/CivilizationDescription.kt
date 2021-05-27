package com.example.civ6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_civilization_description.*
import java.lang.Exception

class CivilizationDescription : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_civilization_description)
        val civilizationData = intent.getParcelableExtra("civilization") as Civilization?

        try {
            if (civilizationData != null) {
                tv_civilization_description_name.text = civilizationData.name
            }
            if (civilizationData != null) {
                tv_civilization_description_leader.text = civilizationData.leader
            }
            if (civilizationData != null) {
                iv_civilization_description_image.setImageResource(civilizationData.image)
            }
        } catch (e: Exception){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }

        tv_civilization_bonus.text = resources.getString(resources.getIdentifier(civilizationData?.name, "string", packageName))
        tv_leader_bonus.text = resources.getString(resources.getIdentifier(civilizationData?.leader?.replace(" ", ""), "string", packageName))
    }



}