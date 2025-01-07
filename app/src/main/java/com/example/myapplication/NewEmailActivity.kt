package com.example.myapplication



import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.CorreoModel
import com.example.myapplication.EmailsActivity

class NewEmailActivity:AppCompatActivity() {
    private lateinit var subjectEditText: EditText
    //private lateinit var senderEditText: EditText
    private lateinit var recipientsEditText: EditText
    private lateinit var bodyEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_email)

        subjectEditText = findViewById(R.id.subjectEditText)
        //senderEditText = findViewById(R.id.senderEditText)
        recipientsEditText = findViewById(R.id.recipientEditText)
        bodyEditText = findViewById(R.id.messageEditText)

        val sendButton = findViewById<Button>(R.id.sendButton)
        sendButton.setOnClickListener {
            val subject = subjectEditText.text.toString()
            //val sender = senderEditText.text.toString()
            val recipients = recipientsEditText.text.toString() // Suponiendo que los destinatarios están separados por comas
            val body = bodyEditText.text.toString()

            val resultadoIntent = Intent()
            var correo = CorreoModel(subject, recipients, body)
            resultadoIntent.putExtra("resultado",correo)
            setResult(RESULT_OK,resultadoIntent)

            finish() // Cerrar la actividad
        }
    }
}