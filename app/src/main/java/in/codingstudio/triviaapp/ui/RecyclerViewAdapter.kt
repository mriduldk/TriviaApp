package `in`.codingstudio.triviaapp.ui

import `in`.codingstudio.triviaapp.R
import `in`.codingstudio.triviaapp.data.Person
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView

class RecyclerViewAdapter(val context : Context?) : RecyclerView.Adapter<RecyclerViewAdapter.View_Holder>() {

    private val allPersons = ArrayList<Person>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): View_Holder {
        return View_Holder(LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return allPersons.size
    }

    override fun onBindViewHolder(holder: View_Holder, position: Int) {
        val person = allPersons[position]

        holder.game_and_date.text = "GAME ${holder.adapterPosition + 1} : ${person.createdDateFormat}"
        holder.name.text = "Name : ${person.name}"
        holder.cricketer.text = "Answer : ${person.cricketer}"
        holder.flag.text = "Answer : ${person.flag}"
    }


    class View_Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: MaterialTextView = itemView.findViewById<MaterialTextView>(R.id.textView_ans_name)
        val game_and_date: MaterialTextView = itemView.findViewById<MaterialTextView>(R.id.textView_ans_game_no_and_date)
        val cricketer: MaterialTextView = itemView.findViewById<MaterialTextView>(R.id.textView_ans_cricketer)
        val flag: MaterialTextView = itemView.findViewById<MaterialTextView>(R.id.textView_ans_flag)
    }

    fun updateList(newList : List<Person>){
        allPersons.clear()
        allPersons.addAll(newList)

        notifyDataSetChanged()
    }

}