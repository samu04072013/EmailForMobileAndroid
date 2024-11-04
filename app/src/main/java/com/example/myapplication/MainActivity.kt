package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.ContextCompat
import android.content.res.ColorStateList

class MainActivity : AppCompatActivity() {
    private lateinit var signInButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    var users = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signInButton = findViewById(R.id.signInButton)
        emailEditText = findViewById(R.id.Email)
        passwordEditText = findViewById(R.id.Password)

        signInButton.setOnClickListener {
            signIn(emailEditText.text.toString(), passwordEditText.text.toString())
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val user = User("benjamincurtis@example-pet-store.com", "123456")
        users.add(user)
    }

    fun signIn(email: String, password: String) {
            Toast.makeText(this, verifySignIn(email, password).toString(), Toast.LENGTH_LONG).show()
    }
    fun verifySignIn(email: String, password: String): String {
        var result: String = ""
        if (email.isEmpty() || password.isEmpty()) {
            result = "Please fill all the fields"
        }else{
            for (user in users) {
                if (user.email == email && user.password == password) {
                    result = "Sign in successful"
                }else{
                    result = "Sign in failed"
                }
            }
        }
        return result
    }
}
class User() {
    lateinit var email: String
    lateinit var password: String
    constructor(email: String, password: String) : this() {
        this.email = email
        this.password = password
    }
}
