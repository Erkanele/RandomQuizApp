package com.bignerdranch.anroid.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var questionTextView: TextView

    private val correctList = ArrayList<Int>()
    private val incorrectList = ArrayList<Int>()

    private val questionBank = listOf(
        Question(R.string.question_africat, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_sweden, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_ocean, true))

    private var currentIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val quizViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")


        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)

        questionTextView.setOnClickListener{view: View ->
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        trueButton.setOnClickListener { view: View ->
           checkAnswer(true)
            isAnswered()
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            isAnswered()
        }

        nextButton.setOnClickListener { view: View ->
            currentIndex = (currentIndex + 1 ) % questionBank.size
            refreshButtons()
            updateQuestion()
        }
        previousButton.setOnClickListener{view: View ->
            currentIndex = (currentIndex - 1) % questionBank.size

            updateQuestion()
        }

        updateQuestion()

        }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onStart() called")
    }

    private fun updateQuestion (){
        Log.d(TAG, "Current question index: $currentIndex")
        try {
        val questionTextResId = questionBank[currentIndex].textResId
            questionTextView.setText(questionTextResId)
        } catch (ex: ArrayIndexOutOfBoundsException) {
            Log.d(TAG, "Index was out of bounds", ex)
        }

    }
    private fun checkAnswer (userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer  == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()

    }
    private fun restrictMultipleAnswers () {

    }
    private fun sumCorrectAndIncorrect () {
   //TODO: Add function for storing answers in the arrays

    }
    private fun displayScore () {
    //TODO: Add function for display scores at end

    }
    private fun isAnswered () {
        falseButton.isEnabled = false
        trueButton.isEnabled = false
    }
    private fun refreshButtons () {
        falseButton.isEnabled = true
        trueButton.isEnabled = true
    }



    }
