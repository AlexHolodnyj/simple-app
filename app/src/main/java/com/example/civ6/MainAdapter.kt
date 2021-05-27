package com.example.civ6

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_for_ban_list.view.*
import kotlinx.android.synthetic.main.item_for_main_adapter.view.iv_civilization_image
import kotlinx.android.synthetic.main.item_for_main_adapter.view.tv_civilization
import kotlinx.android.synthetic.main.item_for_main_adapter.view.tv_civilization_leader
import java.lang.Exception

private const val TAG = "Main_adapter"

class MainAdapter(
        private val itemList: ArrayList<Civilization>,
        private val listener: OnItemClickListener,
        private val layoutId: Int
        ) :
        RecyclerView.Adapter<MainAdapter.ItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId,
                parent, false)

        return  ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]

        try {
            holder.civilizationName.text = currentItem.name
            holder.civilizationLeader.text = currentItem.leader
            holder.civilizationImage.setImageResource(currentItem.image)
            if (currentItem.civilizationBan) {
                holder.civilizationBan!!.visibility = View.VISIBLE
            }else {
                holder.civilizationBan!!.visibility = View.INVISIBLE
            }
        } catch (e: Exception) {
            Log.i(TAG, "Exception")
        }
    }

    override fun getItemCount() = itemList.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView),
        View.OnClickListener{
        val civilizationImage: ImageView = itemView.iv_civilization_image
        val civilizationName: TextView = itemView.tv_civilization
        val civilizationLeader: TextView = itemView.tv_civilization_leader
        val civilizationBan: ImageView? = itemView.cb_item_for_ban_list

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}