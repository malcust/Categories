package com.example.testing

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class SQLITEHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION =1
        private const val DATABASE_NAME = "categories.db"
        private const val TBL_CATEGORY = "tbl_student"
        private const val ID = "id"
        private const val TITLE = "title"
        private const val AnimalNum = "animalNum"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblCategories = "CREATE TABLE " + TBL_CATEGORY +
                " (" + ID + " INTEGER PRIMARY KEY , " +
                TITLE + " TEXT, " +
                AnimalNum + " TEXT);"
        db?.execSQL(createTblCategories)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TBL_CATEGORY")
        onCreate(db)
    }

    fun insertCategories(cat: Cat):Long{
        val db= this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID,cat.id)
        contentValues.put(TITLE,cat.title)
        contentValues.put(AnimalNum,cat.animalNum)

        val success = db.insert(TBL_CATEGORY,null, contentValues)
        db.close()
        return success

    }

    @SuppressLint("Range")
    fun getAllCategories(): ArrayList<Cat>{
        val catList: ArrayList<Cat> =ArrayList()
        val selectQuery = "SELECT * FROM $TBL_CATEGORY"
        val db = this.readableDatabase

        val cursor: Cursor?


        try{
            cursor=db.rawQuery(selectQuery,null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }


        var id: Int
        var title: String
        var animalNum: String

        if (cursor.moveToFirst()){
            do{
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val title = cursor.getString(cursor.getColumnIndex("title"))
                val animalNum = cursor.getString(cursor.getColumnIndex("animalNum"))

                val cat = Cat(id = id, title= title,animalNum = animalNum)
                catList.add(cat)
            }while(cursor.moveToNext())
        }
        return catList
    }

}