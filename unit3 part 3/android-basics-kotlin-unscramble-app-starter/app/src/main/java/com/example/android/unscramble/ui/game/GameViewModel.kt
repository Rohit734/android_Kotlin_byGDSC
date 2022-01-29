package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel : ViewModel() {

    private var _score = 0
    private var _currentWordCount = 0
    private lateinit var _currentScrambledWord: String
    private lateinit var currentWord: String


    //wordsList, to hold a list of words you use in the game, to avoid repetitions.
    private var wordlist: MutableList<String> = mutableListOf()


    // getter
    val currentScrambledWord: String
        get() = _currentScrambledWord
    val score: Int
        get() = _score
    val currentWordCount: Int
        get() = _currentWordCount

    private fun getNextWord() {
        currentWord = allWordsList.random()
        Log.w("GameFragment", currentWord)
        val tempWord = currentWord.toCharArray()
        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordlist.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordlist.add(currentWord)
        }
    }

    init {
        Log.d("GameFragment", "GameViewModel Created!!")
        getNextWord()
    }

    fun nextWord(): Boolean {
        return if (_currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE

    }

    fun isUserWordCorrect(userWord: String): Boolean {
        return if (userWord.equals(currentWord, false)) {
            increaseScore()
            true
        } else false
    }

    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordlist.clear()
        getNextWord()
    }

}