package com.example.myapplication

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class EmailsActivity : AppCompatActivity(){
    private lateinit var emails: List<Email>
    val email = intent.getStringExtra("email")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.emails)

    }
}



data class Email(val id: Int, val sender: List<String>, val subject: String, val body: String)
