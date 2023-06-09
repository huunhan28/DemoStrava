package com.example.demostrava.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.demostrava.R
import com.example.demostrava.data.model.output.ActivityModel
import com.example.demostrava.ui.main.adapter.ActivityAdapter.DataViewHolder
import com.example.demostrava.ui.main.view.DetailActivityActivity
import com.example.demostrava.ui.main.view.LoginActivity

class ActivityAdapter() : RecyclerView.Adapter<DataViewHolder>() {

    lateinit var mContext: Context
    lateinit var actionToParent: CallToAction
    lateinit var users: ArrayList<ActivityModel>
    constructor(context: Context,users: ArrayList<ActivityModel>, action: CallToAction) : this() {
        this.mContext = context
        this.users = users
        this.actionToParent = action
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var textViewUserName: TextView
        private lateinit var textViewUserEmail: TextView
        private lateinit var rootItem: View
        fun bind(activityModel: ActivityModel) {
            itemView.apply {
                textViewUserName = findViewById(R.id.textViewUserName)
                textViewUserEmail = findViewById(R.id.textViewUserEmail)
                textViewUserName.text = activityModel.name
                textViewUserEmail.text = activityModel.distance.toString()
//                Glide.with(imageViewAvatar.context)
//                    .load(activityModel.)
//                    .into(imageViewAvatar)
                rootItem = findViewById(R.id.rootItem)
                rootItem.setOnClickListener {
                    var bundle = Bundle()
                    bundle.putString("summaryPolyline",activityModel.map?.summaryPolyline)
                    bundle.putString("name",activityModel.name)
                    actionToParent.action(bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addActivities(users: List<ActivityModel>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}