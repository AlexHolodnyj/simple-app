package com.example.civ6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_ban_list.*

private const val TAG = "BanList"

class BanList : AppCompatActivity(), MainAdapter.OnItemClickListener {
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ban_list)

        rv_main.layoutManager = GridLayoutManager(this, 2)
        adapter = MainAdapter(civilizationObjectList, listener = this, R.layout.item_for_ban_list)
        rv_main.adapter = adapter
        rv_main.setHasFixedSize(true)
        Log.i(TAG, "BanList recyclerview created")
    }

    override fun onItemClick(position: Int) {
        val checkedItem = civilizationObjectList[position]

        checkedItem.civilizationBan = !checkedItem.civilizationBan
        adapter.notifyItemChanged(position)
    }

    fun banAll(@Suppress("UNUSED_PARAMETER")v: View) {
        for (element in civilizationObjectList.indices){
            civilizationObjectList[element].civilizationBan = true

        }
        adapter.notifyDataSetChanged()

    }

    fun unBanAll(@Suppress("UNUSED_PARAMETER")v: View) {
        for (element in civilizationObjectList.indices){
            civilizationObjectList[element].civilizationBan = false
        }
        adapter.notifyDataSetChanged()

    }


}

