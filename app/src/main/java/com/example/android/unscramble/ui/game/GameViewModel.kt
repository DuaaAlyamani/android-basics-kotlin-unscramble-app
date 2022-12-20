package com.example.android.unscramble.ui.game

import androidx.lifecycle.ViewModel


class GameViewModel : ViewModel() {
    private var _counter = 0
    private var _currentScrambledWord = "test"
    private var _currentWordCount = 0

    val counter: Int
        get() = _counter

    val currentScrambledWord: String
        get() = _currentScrambledWord

    val currentWordCount: Int
        get() = _currentWordCount
    // hold a list of words you use in the game, to avoid repetitions
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String // counter

    private var _score = 0
    val score: Int
        get() = _score

    init {
        getNextWord()
    }
    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)
        }
    }

    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }
    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }
    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordsList.clear()
        getNextWord()
    }


}