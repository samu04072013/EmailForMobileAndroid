package com.example.myapplication

import android.content.Intent
import EmailAdapter
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EmailsActivity : AppCompatActivity() {

    private var emailList = mutableListOf<Email>()
    private val NEW_EMAIL_REQUEST_CODE = 1 // Código de solicitud para identificar la actividad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.emails)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        emailList = mutableListOf(
            Email("Meeting at 10", "boss@example.com", listOf("samu@catmail.com"), "Please be on time"),
            Email("Project update", "team@example.com", listOf("samu@catmail.com"), "The project is on track")
        )

        val adapter = EmailAdapter(emailList)
        recyclerView.adapter = adapter

        val intent = intent
        val message = intent.getStringExtra("user_email")
        println(message)

        val new_email_button = findViewById<Button>(R.id.NewEmail)
        new_email_button.setOnClickListener {
            val intent = Intent(this, NewEmailActivity::class.java)
            startActivityForResult(intent, NEW_EMAIL_REQUEST_CODE) // Iniciar la actividad para obtener un resultado
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_EMAIL_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                val subject = it.getStringExtra("subject") ?: ""
                //val sender = it.getStringExtra("sender") ?: ""
                val recipients = it.getStringArrayListExtra("recipients") ?: arrayListOf()
                val body = it.getStringExtra("body") ?: ""
                ///val user =  intent.getStringExtra("user_email") ?: ""
                // Llamar al método newEmail con los datos recibidos
                newEmail(subject, "samu@catmail.com", recipients, body)
            }
        }
    }

    private fun newEmail(subject: String, sender: String, recipients: List<String>, body: String) {
        val newEmail = Email(subject, sender, recipients, body)
        emailList.add(newEmail)
        Toast.makeText(this, "Email sent successfully", Toast.LENGTH_LONG).show()
        // Notificar al adaptador para que actualice el RecyclerView
        //(recyclerView.adapter as EmailAdapter).notifyDataSetChanged()
    }
}
