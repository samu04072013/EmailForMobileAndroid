package com.example.myapplication



import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.EditText


class NewEmailActivity:AppCompatActivity() {
    private lateinit var subjectEditText: EditText
    private lateinit var senderEditText: EditText
    private lateinit var recipientsEditText: EditText
    private lateinit var bodyEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_email)

        subjectEditText = findViewById(R.id.recipientEditText)
        //senderEditText = findViewById(R.id.senderEditText)
        recipientsEditText = findViewById(R.id.recipientEditText)
        bodyEditText = findViewById(R.id.messageEditText)

        val sendButton = findViewById<Button>(R.id.sendButton)
        sendButton.setOnClickListener {
            val subject = subjectEditText.text.toString()
            //val sender = senderEditText.text.toString()
            val recipients = recipientsEditText.text.toString().split(",") // Suponiendo que los destinatarios están separados por comas
            val body = bodyEditText.text.toString()

            // Crear un Intent para devolver los datos
            val resultIntent = Intent()
            resultIntent.putExtra("subject", subject)
            //resultIntent.putExtra("sender", sender)
            resultIntent.putExtra("recipients", ArrayList(recipients)) // Convertir a ArrayList
            resultIntent.putExtra("body", body)
            setResult(Activity.RESULT_OK, resultIntent)
            finish() // Cerrar la actividad
        }
    }
}