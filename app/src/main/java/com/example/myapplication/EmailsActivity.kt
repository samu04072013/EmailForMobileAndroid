package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EmailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.emails)

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
