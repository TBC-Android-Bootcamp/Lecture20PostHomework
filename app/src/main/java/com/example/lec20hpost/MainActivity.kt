package com.example.lec20hpost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val users = ArrayList<UserModel.Data>()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        getItems()
    }

    private fun getItems() {
        HttpRequest.getRequest(HttpRequest.UNKNOWN, object : CustomCallback {
            override fun onSuccess(body: String) {
                val listModel = Gson().fromJson(body,UserModel::class.java).data
                Log.d("listModel", "$listModel")

                users.addAll(listModel)

                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = RecyclerViewAdapter(users, this@MainActivity)
                recyclerView.adapter = adapter
            }

        })
    }

    private fun refresh() {
        users.clear()
    }

    fun getList(id: Int) {
        for (item in users) {
            if (item.id == id) {
                HttpRequest.getRequest(HttpRequest.UNKNOWN, id.toString(), object : CustomCallback {
                    override fun onSuccess(body: String) {
                        val intent = Intent(this@MainActivity, UserActivity::class.java)
                        intent.putExtra("itemId", id.toString())
                        startActivity(intent)
                        finish()
                    }
                })
            }
        }
    }
}
