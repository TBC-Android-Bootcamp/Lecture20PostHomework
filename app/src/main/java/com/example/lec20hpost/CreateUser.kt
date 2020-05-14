package com.example.lec20hpost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_user.*
import org.json.JSONObject

class CreateUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        init()
    }

    private fun init(){
        createUserButton.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                val parameters = mutableMapOf<String, String>()
                parameters["email"] = email
                parameters["password"] = password

                HttpRequest.postRequest(HttpRequest.CREATE, parameters, object : CustomCallback{
                    override fun onSuccess(body: String) {
                        parseResponseAndSetToFields(body)
                    }

                    override fun onFailed(error: String) {
                        parseErrorBody(error)
                    }
                })
            } else {
                Toast.makeText(this, "Please fill All Fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun parseResponseAndSetToFields(body: String) {
        val responseBody = JSONObject(body)
        if (responseBody.has("email")) emailCreatedTextView.text = responseBody.getString("email")
        if (responseBody.has("password")) passwordCreatedTextView.text = responseBody.getString("password")
        if (responseBody.has("id")) idCreatedTextView.text = responseBody.getString("id")
    }

    private fun parseErrorBody(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}
