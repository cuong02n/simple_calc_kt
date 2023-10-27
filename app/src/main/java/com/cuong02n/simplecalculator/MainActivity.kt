package com.cuong02n.simplecalculator

import android.content.res.Resources
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cuong02n.simplecalculator.ui.theme.SimpleCalculatorTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {

    private val DIVIDE = 0
    private val MULTIPLE = 1
    private val MINUS = 2
    private val SUM = 3
    private val RESULT = -1
    private var previousResult = 0L
    private var previousOperator = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun getCurrentResult(): Long {
        val x = findViewById<TextView>(R.id.textResult).text.toString().toLong()
        return x
    }

    private fun _setResult(result: Long) {
        if (result.toString().length >= 15) {
            _setResult(0)
            Toast.makeText(this, "Out of range", Toast.LENGTH_SHORT).show()
        }
        findViewById<TextView>(R.id.textResult).text = result.toString()
    }

    private fun calulate(previousResult: Long, result: Long, operator: Int): Long {
        var x = 0L
        when (operator) {
            SUM -> x = previousResult + result
            MINUS -> x = previousResult - result
            MULTIPLE -> x = previousResult * result
            DIVIDE -> x = previousResult / result
        }
        return x
    }


    private fun init() {
        findViewById<Button>(R.id.divide).setOnClickListener {
            previousResult = getCurrentResult()
            _setResult(0)
            previousOperator = DIVIDE
        }
        findViewById<Button>(R.id.multiple).setOnClickListener {
            previousResult = getCurrentResult()
            _setResult(0)
            previousOperator = MULTIPLE
        }
        findViewById<Button>(R.id.minus).setOnClickListener {
            previousResult = getCurrentResult()
            _setResult(0)
            previousOperator = MINUS
        }
        findViewById<Button>(R.id.sum).setOnClickListener {
            previousResult = getCurrentResult()
            _setResult(0)
            previousOperator = SUM
        }
        findViewById<Button>(R.id.result).setOnClickListener {
            Toast.makeText(this, "$previousResult and ${getCurrentResult()} ", Toast.LENGTH_SHORT)
                .show()
            _setResult(calulate(previousResult, getCurrentResult(), previousOperator))
            previousResult = getCurrentResult()
            previousOperator = RESULT
        }

        for (i in 0..9) {
            val id = resources.getIdentifier("_$i", "id", packageName)
            findViewById<Button>(id).setOnClickListener {
                val a = findViewById<TextView>(R.id.textResult)
                _setResult((a.text.toString() + i).toLong())
            }
        }
        findViewById<Button>(R.id.CE).setOnClickListener {
            previousResult = 0
            previousOperator = RESULT
            _setResult(0)
        }
        findViewById<Button>(R.id.C).setOnClickListener {
            var x = getCurrentResult().toString()
            if (x.isNotEmpty()) {
                x = x.substring(0, x.length - 1);
            }
            _setResult(x.toLong())
        }
    }
}

