package com.example.lec20hpost

interface CustomCallback {
    fun onSuccess(body: String) {}
    fun onFailed(error: String) {}
}