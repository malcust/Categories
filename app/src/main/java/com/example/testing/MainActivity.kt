package com.example.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var edTitle: EditText
    private lateinit var editTextNumber: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var recyclerView: RecyclerView
    private  var adapter: CatAdapt? = null

    private lateinit var sqliteHelper: SQLITEHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()
        sqliteHelper = SQLITEHelper(this)
        btnAdd.setOnClickListener { addCategories()}
        btnView.setOnClickListener { getCategories()}
    }
    private fun getCategories(){
        val catList = sqliteHelper.getAllCategories()
        Log.e("pppp","${catList.size}")

    //Display data in Recycler View
        adapter?.addItems(catList)

    }


    private fun addCategories() {
        val title= edTitle.text.toString()
        val animalNum= editTextNumber.text.toString()

        if (title.isEmpty() || animalNum.isEmpty()) {
            Toast.makeText(this, "Please enter both fields", Toast.LENGTH_SHORT).show()
        } else {
            val cat = Cat(title = title, animalNum=animalNum)
            val status = sqliteHelper.insertCategories(cat)
            if (status > -1){
                Toast.makeText(this, "Student added....", Toast.LENGTH_SHORT).show()
                clearEditText()
                getCategories()
            }else{
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun clearEditText() {
        edTitle.setText("")
        editTextNumber.setText("")
        edTitle.requestFocus()
    }
    private fun initRecyclerView(){
        recyclerView.layoutManager= LinearLayoutManager(this)
        adapter = CatAdapt()
        recyclerView.adapter = adapter
    }

    private fun initView() {
        edTitle= findViewById(R.id.edTitle)
        editTextNumber= findViewById(R.id.editTextNumber)
        btnAdd= findViewById(R.id.btnAdd)
        btnView= findViewById(R.id.btnView)
        recyclerView =findViewById(R.id.recyclerView)
    }
}