import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Email
import com.example.myapplication.R

class EmailAdapter(private val emailList: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        try {
            val email = emailList[position]
            holder.subject.text = email.subject
            holder.sender.text = email.sender
            holder.recipents.text = email.recipents.joinToString(",")
            holder.preview.text = email.preview
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount() = emailList.size

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subject: TextView = itemView.findViewById(R.id.email_subject)
        val sender: TextView = itemView.findViewById(R.id.email_sender)
        val recipents: TextView = itemView.findViewById(R.id.email_recipents)
        val preview: TextView = itemView.findViewById(R.id.email_preview)
    }
}
