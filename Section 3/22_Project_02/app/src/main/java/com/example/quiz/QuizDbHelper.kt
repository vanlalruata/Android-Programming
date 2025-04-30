package com.example.quiz

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class QuizDbHelper(context: Context) : SQLiteOpenHelper(context, "quiz.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE questions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                question TEXT NOT NULL,
                option1 TEXT NOT NULL,
                option2 TEXT NOT NULL,
                option3 TEXT NOT NULL,
                option4 TEXT NOT NULL,
                answer INTEGER NOT NULL
            )
        """.trimIndent())

        // Insert sample questions
        insertQuestion(db, "What is the capital of India?", "Goa", "Mumbai", "New Delhi", "Delhi", 3)
        insertQuestion(db, "Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Saturn", 2)
        insertQuestion(db, "Who wrote 'Hamlet'?", "Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen", 2)
        insertQuestion(db, "What is 2 + 2?", "3", "4", "5", "6", 2)
        insertQuestion(db, "Which is the largest ocean?", "Atlantic", "Indian", "Arctic", "Pacific", 4)
    }

    private fun insertQuestion(
        db: SQLiteDatabase,
        question: String,
        option1: String,
        option2: String,
        option3: String,
        option4: String,
        answer: Int
    ) {
        val values = ContentValues().apply {
            put("question", question)
            put("option1", option1)
            put("option2", option2)
            put("option3", option3)
            put("option4", option4)
            put("answer", answer)
        }
        db.insert("questions", null, values)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS questions")
        onCreate(db)
    }

    fun getAllQuestions(): List<Question> {
        val questions = mutableListOf<Question>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM questions", null)
        if (cursor.moveToFirst()) {
            do {
                questions.add(
                    Question(
                        id = cursor.getInt(0),
                        question = cursor.getString(1),
                        option1 = cursor.getString(2),
                        option2 = cursor.getString(3),
                        option3 = cursor.getString(4),
                        option4 = cursor.getString(5),
                        answer = cursor.getInt(6)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return questions
    }
}
