package com.example.bookappsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookappsqlite.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var bookList: List<Book>
    private lateinit var bookAdapter: BookAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: BookDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = BookDatabase.getDatabase(this)


        bookList = mutableListOf()


        bookAdapter = BookAdapter(bookList as MutableList<Book>)

        binding.recyclerView.adapter = bookAdapter


        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.addBtn.setOnClickListener {
            writeData()
        }

        binding.viewBtn.setOnClickListener {
            readData()


        }
    }


    private fun readData() {

        GlobalScope.launch(Dispatchers.Default) {
            bookList = db.bookDao().getBooks()

            bookAdapter = BookAdapter(bookList as MutableList<Book>)

        }
        binding.recyclerView.adapter = bookAdapter
    }

    private fun writeData() {

        val name = binding.editTxtName.text.toString()
        val author = binding.editTxtAuthor.text.toString()
        val year = binding.editTxtYear.text.toString()

        if (name.isNotEmpty() && author.isNotEmpty() && year.isNotEmpty()) {

            val book = Book(null, name, author, year.toInt())

            GlobalScope.launch(Dispatchers.IO) {

                db.bookDao().insert(book)

                bookList = db.bookDao().getBooks()

            }

            binding.editTxtName.text.clear()
            binding.editTxtAuthor.text.clear()
            binding.editTxtYear.text.clear()

        } else {
            Toast.makeText(this, "Please, fill out all of the fields", Toast.LENGTH_SHORT).show()
        }
    }
}