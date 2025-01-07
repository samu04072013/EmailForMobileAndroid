package com.example.myapplication

import EmailAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class EmailsActivity : AppCompatActivity() {

    private var emailList = mutableListOf<Email>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EmailAdapter
    private val fitredList = mutableListOf<Email>()
    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Verificamos que el código de resultado sea el esperado
        if (result.resultCode == RESULT_OK) {
            // Aquí accedemos al dato devuelto de la actividad secundaria
            val data = result.data?.getParcelableExtra<CorreoModel>("resultado")
            Log.d("MainActivity", "Resultado recibido: $data")
            val intent = intent
            val usuario = intent.getStringExtra("user_email")

            newEmail(data!!.asunto, usuario!!, data.destinatario.split(","), data.cuerpo)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.emails)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        emailList = mutableListOf(
            Email("Meeting at 10", "boss@example.com", listOf("samu@catmail.com"), "Please be on time"),
            Email("Project update", "team@example.com", listOf("samu@catmail.com"), "The project is on track")
        )

        val intent = intent
        val usuario = intent.getStringExtra("user_email")
        //println(message)

        for (email in emailList){
            for (recipent in email.recipents){
                if (recipent == usuario) {
                    fitredList.add(email)
                }
            }
        }
        adapter = EmailAdapter(fitredList)
        recyclerView.adapter = adapter



        val new_email_button = findViewById<Button>(R.id.NewEmail)
        new_email_button.setOnClickListener {
            val intent2 = Intent(this, NewEmailActivity::class.java)
            activityResultLauncher.launch(intent2)

            //val adapter = EmailAdapter(emailList)



        }
    }


     fun newEmail(subject: String, sender: String, recipients: List<String>, body: String) {
         val intent = intent
         val usuario = intent.getStringExtra("user_email")
         for (recipient in recipients){
             if(recipient == usuario){
                 val newEmail = Email(subject, sender, recipients, body)
                 fitredList.add(newEmail)
                 adapter.notifyDataSetChanged()
             }

         }
         Toast.makeText(this, "Email sent successfully", Toast.LENGTH_LONG).show()
    }
}
