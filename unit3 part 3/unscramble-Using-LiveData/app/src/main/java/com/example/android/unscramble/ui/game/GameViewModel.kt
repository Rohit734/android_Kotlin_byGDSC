package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel : ViewModel() {

    private var _score = MutableLiveData(0)
    private var _currentWordCount = MutableLiveData(0)
   private val _currentScrambledWord = MutableLiveData<String>()

    private lateinit var currentWord: String


    //wordsList, to hold a list of words you use in the game, to avoid repetitions.
    private var wordlist: MutableList<String> = mutableListOf()


    // getter
    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord

    val score: LiveData<Int>
        get() = _score

    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    //functions
    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordlist.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value =_currentWordCount.value?.inc()
            wordlist.add(currentWord)
        }
    }

    init {
        Log.d("GameFragment", "GameViewModel Created!!")
        getNextWord()
    }

    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score.value = _score.value?.plus(SCORE_INCREASE)

    }

    fun isUserWordCorrect(userWord: String): Boolean {
        return if (userWord.equals(currentWord, false)) {
            increaseScore()
            true
        } else false
    }

    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordlist.clear()
        getNextWord()
    }

}