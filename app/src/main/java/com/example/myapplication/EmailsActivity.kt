package com.example.myapplication

import EmailAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EmailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.emails)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val emailList = listOf(
            Email("Meeting at 10", "boss@example.com", "Please be on time"),
            Email("Project update", "team@example.com", "The project is on track")
            // Añade más correos electrónicos a la lista
        )

        val adapter = EmailAdapter(emailList)
        recyclerView.adapter = adapter

        // Obtén el email del intent
        val email = intent.getStringExtra("user_email")
        if (email != null) {
            val emailsList = giveEmails(email)
            // Aquí podrías mostrar los correos en un RecyclerView o ListView
            Toast.makeText(this, emailsList.joinToString(", "), Toast.LENGTH_LONG).show()
        }
    }

    private fun giveEmails(email: String): List<String> {
        val result = mutableListOf<String>()
        for (map in emails) {
            for (value in map.values) {
                if (value.contains(email)) {
                    result.add(map.keys.first())
                }
            }
        }
        return result
    }

    private fun newEmail(email: String, recipients: List<String>) {
        emails.add(mapOf(email to recipients))
        Toast.makeText(this, "Email sent successfully", Toast.LENGTH_LONG).show()
    }
}
