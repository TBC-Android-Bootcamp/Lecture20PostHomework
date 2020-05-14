package com.example.lec20hpost

import com.google.gson.annotations.SerializedName

class UserModel {
    var page: Int = 0
    @SerializedName("per_page")
    var perPage: Int = 0
    var total: Int = 0
    @SerializedName("total_pages")
    var totalPages: Int = 0
    var data: ArrayList<Data> = ArrayList()

    class Data() {
        var id: Int = 0
        var name: String = ""
        var year: Int = 0
        var color: String = ""
        @SerializedName("pantone_value")
        var pantoneValue: String = ""
    }

    class Item {
        var data = Data()
    }
}
