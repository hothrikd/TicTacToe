package com.example.tictactoe
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener{
    var PLAYER = 1
    var TURN_COUNT = 0
    var boardStatus = Array(3){IntArray(3)}
    lateinit var board : Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        initializeBoardStatus()
        for(i in board)
        {
            for(button in i)
            {
                button.setOnClickListener(this)
            }
        }
        val resbtn = findViewById<Button>(R.id.resetBtn)

        resbtn.setOnClickListener {
            TURN_COUNT=0
            initializeBoardStatus()
            if(PLAYER==1)
            {
                updateDisplay("Player X Turn")
            }
            else{
                updateDisplay("Player O Turn")
            }
        }
    }

    private fun initializeBoardStatus() {
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                boardStatus[i][j]=-1
                board[i][j].isEnabled = true
                board[i][j].text=""
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.button1->{
                updateValue(0,0,PLAYER)
            }
            R.id.button2->{
                updateValue(0,1,PLAYER)
            }
            R.id.button3->{
                updateValue(0,2,PLAYER)
            }
            R.id.button4->{
                updateValue(1,0,PLAYER)
            }
            R.id.button5->{
                updateValue(1,1,PLAYER)
            }
            R.id.button6->{
                updateValue(1,2,PLAYER)
            }
            R.id.button7->{
                updateValue(2,0,PLAYER)
            }
            R.id.button8->{
                updateValue(2,1,PLAYER)
            }
            R.id.button9-> {
                updateValue(2,2,PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER=1-PLAYER
        if(PLAYER==1)
        {
            updateDisplay("Player X Turn")
        }
        else{
            updateDisplay("Player O Turn")
        }
        if(TURN_COUNT==9)
        {
            updateDisplay("Game Draw")
        }
        checkWinner()
    }

    private fun checkWinner() {
        for(i in 0..2)
        {
            if(boardStatus[i][0]==boardStatus[i][1]&&boardStatus[i][0]==boardStatus[i][2])
            {
                if(boardStatus[i][0]==1)
                {
                    updateDisplay("Player X is Winner")
                    break
                }
                else if(boardStatus[i][0]==0)
                {
                    updateDisplay("Player O is Winner")
                    break
                }
            }
        }
        for(i in 0..2)
        {
            if(boardStatus[0][i]==boardStatus[1][i]&&boardStatus[0][i]==boardStatus[2][i])
            {
                if(boardStatus[0][i]==1)
                {
                    updateDisplay("Player X is Winner")
                }
                else if(boardStatus[0][i]==0){
                    updateDisplay("Player O is Winner")
                }
            }
        }
        if(boardStatus[0][0]==boardStatus[1][1]&&boardStatus[0][0]==boardStatus[2][2]&&boardStatus[1][1]!=-1||boardStatus[0][2]==boardStatus[1][1]&&boardStatus[0][2]==boardStatus[2][0]&&boardStatus[1][1]!=-1)
        {
            if(boardStatus[1][1]==1)
            {
                updateDisplay("Player X is Winner")
            }
            else if(boardStatus[1][1]==0)
            {
                updateDisplay("Player O is Winner")
            }
        }
    }

    private fun disableButton() {
        for(i in board)
        {
            for(button in i)
            {
                button.isEnabled = false
            }
        }
    }

    private fun updateDisplay(s: String) {
        val displayTv = findViewById<TextView>(R.id.displayTv)
        displayTv.text = s
        if(s.contains("Winner"))
        {
            disableButton()
        }
    }

    private fun updateValue(row: Int, col: Int, player: Int) {
        val txt = if(player==1) "X" else "O"
        board[row][col].apply {
            isEnabled = false
            setText(txt)
        }
        boardStatus[row][col]=player
    }
}