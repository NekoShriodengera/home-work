package neko.shriodenger.lab9_tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class StartGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)

        val player1Name = findViewById<EditText>(R.id.player1Name)
        val player2Name = findViewById<EditText>(R.id.player2Name)
        val player1Symbol = findViewById<EditText>(R.id.player1Symbol)
        val player2Symbol = findViewById<EditText>(R.id.player2Symbol)
        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            val player1Name = player1Name.text.toString().takeIf { it.isNotEmpty() } ?: "Гравець 1"
            val player2Name = player2Name.text.toString().takeIf { it.isNotEmpty() } ?: "Гравець 2"
            val player1Symbol = player1Symbol.text.toString().takeIf { it.isNotEmpty() } ?: "X"
            val player2Symbol = player2Symbol.text.toString().takeIf { it.isNotEmpty() } ?: "O"

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("PLAYER1_NAME", player1Name)
                putExtra("PLAYER2_NAME", player2Name)
                putExtra("PLAYER1_SYMBOL", player1Symbol)
                putExtra("PLAYER2_SYMBOL", player2Symbol)
            }
            startActivity(intent)
        }
    }
}