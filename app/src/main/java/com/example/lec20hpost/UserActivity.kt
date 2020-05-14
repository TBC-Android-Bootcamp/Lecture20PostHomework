package com.example.lec20hpost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        init()
    }

    private fun init() {
        val getId = intent.extras?.getString("itemId", "")

        if (getId != null) {
            HttpRequest.getRequest(HttpRequest.UNKNOWN, getId, object : CustomCallback {
                override fun onSuccess(body: String) {
                    val listModel = Gson().fromJson(body, UserModel.Item::class.java).data
                    Log.d("listModel", "$listModel")
//                    d("bodyItem", body)
                    itemIdTextView.text = listModel.id.toString()
                    itemNameTextView.text = listModel.name
                    itemYearTextView.text = listModel.year.toString()
                    itemPantoneValueTextView.text = listModel.pantoneValue
                }

            })
        }

        nextActivity.setOnClickListener {
            val intent = Intent(this@UserActivity, CreateUser::class.java)
            startActivity(intent)
            finish()
        }
    }
}
