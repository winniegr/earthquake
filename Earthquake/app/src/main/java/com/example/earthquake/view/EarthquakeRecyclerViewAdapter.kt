package com.example.earthquake.view

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquake.viewmodel.Feature
import com.example.earthquake.databinding.ItemEarthquakeBinding
import com.example.earthquake.utils.openGaode

class EarthquakeRecyclerViewAdapter(private val context: Activity) :
    RecyclerView.Adapter<EarthquakeRecyclerViewAdapter.ViewHolder>() {

    var features: List<Feature> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemEarthquakeBinding.inflate(LayoutInflater.from(parent.context))).apply {
        }
    }

    override fun getItemCount(): Int = features.size

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(context: Activity, feature: Feature) {
            feature.geometry?.let {
                openGaode(
                    context,
                    feature.properties?.title ?: "earthquake",
                    it.coordinates?.get(0) ?: 0.0,
                    it.coordinates?.get(1) ?: 0.0
                )
            }
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val property = features[position].properties
        holder.binding.title.text = property?.title ?: ""
        holder.binding.alert.text = property?.alert ?: ""
        holder.binding.status.text = property?.status ?: ""
        holder.binding.root.setOnClickListener {
            onItemClickListener.onItemSelected(
                context,
                features[position]
            )
        }
        property?.mag?.let {
            if (it >= 7.5) {
                holder.binding.title.setTextColor(Color.parseColor("#FFFF0000"))
            } else {
                holder.binding.title.setTextColor(Color.parseColor("#FF000000"))
            }
        }
    }

    inner class ViewHolder(val binding: ItemEarthquakeBinding) :
        RecyclerView.ViewHolder(binding.root)
}
