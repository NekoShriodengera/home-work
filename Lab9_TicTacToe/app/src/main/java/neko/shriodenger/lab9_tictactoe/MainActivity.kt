package neko.shriodenger.lab9_tictactoe

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var isPlayerOneTurn = true
    private var player1Score = 0
    private var player2Score = 0
    private val board = IntArray(9) { 0 } // 0 = empty, 1 = X, 2 = O
    private lateinit var buttons: Array<Button>
    private lateinit var scoreTextView: TextView
    private var gameOver = false

    private lateinit var timerTextView: TextView
    private lateinit var playerTurnTextView: TextView
    private var countDownTimer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val backToStartButton: Button = findViewById(R.id.backToStartButton)
        timerTextView = findViewById(R.id.timerTextView)
        playerTurnTextView = findViewById(R.id.playerTurnTextView)
        backToStartButton.setOnClickListener {
            finish() // Повертає до StartGameActivity
        }
        buttons = arrayOf(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9)
        )

        scoreTextView = findViewById(R.id.scoreTextView)

        val resetRoundButton: Button = findViewById(R.id.resetRoundButton)
        val continueButton: Button = findViewById(R.id.continueButton)

        resetRoundButton.setOnClickListener {
            resetRound()
        }

        continueButton.setOnClickListener {
            continueGame()
        }

        updateScore()
    }

    fun onCellClick(view: View) {
        if (gameOver) return

        val player1Name = intent.getStringExtra("PLAYER1_NAME")
        val player2Name = intent.getStringExtra("PLAYER2_NAME")
        val player1Symbol = intent.getStringExtra("PLAYER1_SYMBOL")
        val player2Symbol = intent.getStringExtra("PLAYER2_SYMBOL")

        val clickedButton = view as Button
        val index = buttons.indexOf(clickedButton)

        if (board[index] != 0) return

        if (isPlayerOneTurn) {
            clickedButton.text = player1Symbol
            board[index] = 1
        } else {
            clickedButton.text = player2Symbol
            board[index] = 2
        }
        startTurnTimer()
        if (checkWinner()) {
            gameOver = true
            val winnerName = if (isPlayerOneTurn) player1Name else player2Name
            Toast.makeText(this, "Переможець: $winnerName!", Toast.LENGTH_SHORT).show()

            if (isPlayerOneTurn) {
                player1Score++
            } else {
                player2Score++
            }

            updateScore()
            countDownTimer?.cancel()
            findViewById<Button>(R.id.continueButton).visibility = View.VISIBLE
        } else if (board.all { it != 0 }) {
            gameOver = true
            Toast.makeText(this, "Нічия!", Toast.LENGTH_SHORT).show()
            findViewById<Button>(R.id.continueButton).visibility = View.VISIBLE
            countDownTimer?.cancel()
        } else {
            isPlayerOneTurn = !isPlayerOneTurn
            updatePlayerTurnText()
        }
    }


    private fun startTurnTimer() {
        timeLeftInMillis = 10000 // Reset timer for each turn
        updateTimerText()

        countDownTimer?.cancel() // Cancel any existing timer

        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                isPlayerOneTurn = !isPlayerOneTurn
                startTurnTimer() // Start new turn timer
                updatePlayerTurnText() // Update turn text to show the correct player's turn
            }
        }.start()

        updatePlayerTurnText()
    }
    private fun updateTimerText() {
        val seconds = (timeLeftInMillis / 1000).toInt()
        timerTextView.text = "$seconds"
    }
    private fun updatePlayerTurnText() {
        val p1 = intent.getStringExtra("PLAYER1_NAME") ?: "Гравець 1"
        val p2 = intent.getStringExtra("PLAYER2_NAME") ?: "Гравець 2"
        val currentPlayer = if (isPlayerOneTurn) p1 else p2
        playerTurnTextView.text = "$currentPlayer ходить"
    }


    private fun checkWinner(): Boolean {
        val winComb = arrayOf(
            intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
        )

        for (combo in winComb) {
            if (board[combo[0]] != 0 &&
                board[combo[0]] == board[combo[1]] &&
                board[combo[0]] == board[combo[2]]) {
                return true
            }
        }

        return false
    }

    private fun resetRound() {
        for (i in board.indices) {
            board[i] = 0
            buttons[i].text = ""
        }
        isPlayerOneTurn = true
        gameOver = false
        countDownTimer?.cancel()
    }

    private fun continueGame() {
        resetRound()
        Toast.makeText(this, "Новий раунд!", Toast.LENGTH_SHORT).show()
        findViewById<Button>(R.id.continueButton).visibility = View.GONE
    }

    private fun updateScore() {
        val p1 = intent.getStringExtra("PLAYER1_NAME") ?: "Гравець 1"
        val p2 = intent.getStringExtra("PLAYER2_NAME") ?: "Гравець 2"
        scoreTextView.text = "Рахунок — $p1: $player1Score | $p2: $player2Score"
    }
}
