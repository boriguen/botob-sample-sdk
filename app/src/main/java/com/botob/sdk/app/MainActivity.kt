package com.botob.sdk.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.botob.samplesdk.equation.reader.EquationSolver
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSolveButtonClick(view: View?) {
        val path = findViewById<EditText>(R.id.text_filepath).text.toString()
        findViewById<TextView>(R.id.text_result).text = EquationSolver(FileInputStream(path)).formatSolution()
    }
}
