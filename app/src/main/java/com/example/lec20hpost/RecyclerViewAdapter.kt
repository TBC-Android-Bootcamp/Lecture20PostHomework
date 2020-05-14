package com.example.lec20hpost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class RecyclerViewAdapter(private val users: ArrayList<UserModel.Data>, private val activity: MainActivity) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout,
            parent,
            false
        )
    )

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var model: UserModel.Data

        fun onBind() {
            model = users[position]

            itemView.tvUserId.text = model.id.toString()
            itemView.tvName.text = model.name
            itemView.tvYear.text = model.year.toString()
            itemView.tvPantoneValue.text = model.pantoneValue

            itemView.setOnClickListener {
                activity.getList(model.id)
            }
        }


    }
}