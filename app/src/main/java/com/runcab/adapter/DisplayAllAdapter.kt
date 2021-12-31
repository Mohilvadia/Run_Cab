package com.runcab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.runcab.R
import com.runcab.model.Data
import com.runcab.model.DisplayAlResponse

class DisplayAllAdapter (var displayList : ArrayList<Data>): RecyclerView.Adapter<DisplayViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayViewHolder {
        return DisplayViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_display,parent,false))
    }

    override fun onBindViewHolder(holder: DisplayViewHolder, position: Int) {
        holder.tvTime!!.text = displayList[position].r_time
        holder.tvDate!!.text = displayList[position].r_date
        holder.tvDriverName!!.text = displayList[position].driver_name
        holder.tvTripName!!.text = displayList[position].only_drop
    }

    override fun getItemCount(): Int {
        return displayList.size
    }
}

class DisplayViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
    var tvDate : TextView? = null
    var tvTime : TextView? = null
    var tvDriverName : TextView? = null
    var tvTripName : TextView? = null
    var tvRoundTrip : TextView? = null
    init {
        tvDate = itemView.findViewById(R.id.tvDate)
        tvTime = itemView.findViewById(R.id.tvTime)
        tvDriverName = itemView.findViewById(R.id.tvDriverName)
        tvTripName = itemView.findViewById(R.id.tvTripName)
        tvRoundTrip = itemView.findViewById(R.id.tvRoundTrip)
    }
}
