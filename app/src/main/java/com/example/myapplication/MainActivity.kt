package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var createAccountButton: Button
    private lateinit var signInButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createAccountButton = findViewById(R.id.createAccountButton)
        signInButton = findViewById(R.id.signInButton)
        emailEditText = findViewById(R.id.Email)
        passwordEditText = findViewById(R.id.Password)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            signIn(email, password)
        }

        createAccountButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            createAccount(email, password)
        }

        // Ajusta el padding cuando la vista cambia debido a la barra de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Usuario de ejemplo
        users.add(User("mail", "123"))
    }

    private fun signIn(email: String, password: String) {
        val result = verifySignIn(email, password)
        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
        if (result == "Sign in successful") {
            posSignIn(email)
        }
    }

    private fun posSignIn(email: String) {
        val intent = Intent(this, EmailsActivity::class.java)
        intent.putExtra("user_email", email)
        startActivity(intent)
    }

    private fun verifySignIn(email: String, password: String): String {
        if (email.isEmpty() || password.isEmpty()) {
            return "Please fill all the fields"
        }

        // Verifica si el usuario existe
        //val user = users.find { it.email == email && it.password == password }
        var answer = ""
        val thread = Thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://10.0.2.2:7110/Email/LogIn?correo=$email&contrasenna=$password")
                    .build()
                val response = client.newCall(request).execute()
                if (response.equals(true)) {
                    answer = "Sign in successful"
                } else {
                    answer = "Sign in failed"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                answer = "An error has occurred"
            }

        }

        thread.start()
        return answer
    }
    private fun createAccount(email: String, password: String) {
        val result = verifyCreateAccount(email, password)
        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
    }

    private fun verifyCreateAccount(email: String, password: String): String {
        // Validación de campos vacíos
        if (email.isEmpty() || password.isEmpty()) {
            return "Please fill all the fields"
        }

        // Validación del formato del correo electrónico
        if (!isValidEmail(email)) {
            return "Invalid email format"
        }

        // Verifica si el correo electrónico ya existe
        val existingUser = users.find { it.email == email }
        return if (existingUser != null) {
            "Email already exists"
        } else {
            // Crea el nuevo usuario
            users.add(User(email, password))
            "Account created successfully"
        }
    }

    private fun isValidEmail(email: String): Boolean {
        // Simple regex para validar el correo electrónico (puedes ajustarlo según tus necesidades)
        return email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
    }
}

data class User(val email: String, val password: String)
data class Email(val subject: String, val sender: String, val recipents: List<String>, val preview: String)