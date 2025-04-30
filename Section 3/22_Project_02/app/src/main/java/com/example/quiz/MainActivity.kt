package com.example.quiz

import android.os.Bundle
import android.graphics.Color
import androidx.activity.enableEdgeToEdge
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: QuizDbHelper
    private lateinit var questions: List<Question>
    private var currentIndex = 0
    private var score = 0

    private lateinit var questionText: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton
    private lateinit var option4: RadioButton
    private lateinit var submitBtn: Button
    private lateinit var scoreText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        dbHelper = QuizDbHelper(this)
        questions = dbHelper.getAllQuestions().shuffled()

        questionText = findViewById(R.id.questionText)
        radioGroup = findViewById(R.id.radioGroup)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)
        submitBtn = findViewById(R.id.submitBtn)
        scoreText = findViewById(R.id.scoreText)

        loadQuestion()

        submitBtn.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val selectedOption = when (selectedId) {
                R.id.option1 -> 1
                R.id.option2 -> 2
                R.id.option3 -> 3
                R.id.option4 -> 4
                else -> 0
            }
            checkAnswer(selectedOption)
        }


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    private fun loadQuestion() {
        if (currentIndex >= questions.size) {
            showFinalScore()
            return
        }
        val q = questions[currentIndex]
        questionText.text = q.question
        option1.text = q.option1
        option2.text = q.option2
        option3.text = q.option3
        option4.text = q.option4
        radioGroup.clearCheck()
        resetOptionColors()
        submitBtn.isEnabled = true
    }

    private fun checkAnswer(selectedOption: Int) {
        val q = questions[currentIndex]
        if (selectedOption == q.answer) {
            score++
            scoreText.text = "Score: $score"
            highlightOption(selectedOption, true)
        } else {
            highlightOption(selectedOption, false)
            highlightOption(q.answer, true)
        }
        submitBtn.isEnabled = false
        radioGroup.postDelayed({
            currentIndex++
            loadQuestion()
        }, 1000)
    }

    private fun highlightOption(option: Int, correct: Boolean) {
        val color = if (correct) ContextCompat.getColor(this, R.color.correct)
        else ContextCompat.getColor(this, R.color.incorrect)
        when (option) {
            1 -> option1.setBackgroundColor(color)
            2 -> option2.setBackgroundColor(color)
            3 -> option3.setBackgroundColor(color)
            4 -> option4.setBackgroundColor(color)
        }
    }

    private fun resetOptionColors() {
        option1.setBackgroundColor(Color.TRANSPARENT)
        option2.setBackgroundColor(Color.TRANSPARENT)
        option3.setBackgroundColor(Color.TRANSPARENT)
        option4.setBackgroundColor(Color.TRANSPARENT)
    }

    private fun showFinalScore() {
        questionText.text = "Quiz Finished!\nYour Score: $score/${questions.size}"
        radioGroup.visibility = RadioGroup.GONE
        submitBtn.visibility = Button.GONE
    }
}