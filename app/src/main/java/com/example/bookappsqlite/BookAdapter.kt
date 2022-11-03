package com.example.bookappsqlite

import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections.addAll

class BookAdapter(private var bookList: MutableList<Book> = mutableListOf<Book>()) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {



    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.bookNameTV)
        val authorView: TextView = itemView.findViewById(R.id.bookAuthorTV)
        val yearView: TextView = itemView.findViewById(R.id.bookYearTV)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.nameView.text = book.name
        holder.authorView.text = book.author
        holder.yearView.text = book.year.toString()
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}