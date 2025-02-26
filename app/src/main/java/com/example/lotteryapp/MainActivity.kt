package com.example.lotteryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisplayNumbers()
        }
    }
}

@Composable
fun DisplayNumbers(){
    var result by remember { mutableStateOf(mutableListOf<Int>(0,0,0,0,0,0)) }
    var num1 by remember { mutableIntStateOf(0) }
    var num2 by remember { mutableIntStateOf(0) }
    var num3 by remember { mutableIntStateOf(0) }
    var num4 by remember { mutableIntStateOf(0) }
    var num5 by remember { mutableIntStateOf(0) }
    var num6 by remember { mutableIntStateOf(0) }

    var sortButtonClicks by remember { mutableIntStateOf(0) }

    Column (
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            Text(num1.toString())
            Text(num2.toString())
            Text(num3.toString())
            Text(num4.toString())
            Text(num5.toString())
            Text(num6.toString())

        }
        Row{
            Button(onClick = {
                result = calculate()
                num1 = result[0]
                num2 = result[1]
                num3 = result[2]
                num4 = result[3]
                num5 = result[4]
                num6 = result[5]
            }) {
                Text("Generate")
            }
            Button(
                onClick = {
                    sortButtonClicks++
                    result = sortResult(result, sortButtonClicks % 2 != 0)
                    num1 = result[0]
                    num2 = result[1]
                    num3 = result[2]
                    num4 = result[3]
                    num5 = result[4]
                    num6 = result[5]
            }) {
                Text("Sort")
            }
        }

        Row {
            Button(onClick = {
                val number = generateNumber(0, result)
                num1 = number[0]
            }) {
                Text("1")
            }
            Button(onClick = {
                val number = generateNumber(1, result)
                num2 = number[1]
            }) {
                Text("2")
            }
            Button(onClick = {
                val number = generateNumber(2, result)
                num3 = number[2]
            }) {
                Text("3")
            }
            Button(onClick = {
                val number = generateNumber(3, result)
                num4 = number[3]
            }) {
                Text("4")
            }
            Button(onClick = {
                val number = generateNumber(4, result)
                num5 = number[4]
            }) {
                Text("5")
            }
            Button(onClick = {
                val number = generateNumber(5, result)
                num6 = number[5]
            }) {
                Text("6")
            }
        }
    }
}


fun calculate(): MutableList<Int>{
    val numberList = mutableListOf<Int>()
    while (numberList.size < 6){
        val randomNumber = Random.nextInt(59) + 1
        if(randomNumber !in numberList){
            numberList.add(randomNumber)
        }
    }
    return numberList
}

fun generateNumber(index: Int, list: MutableList<Int>): MutableList<Int> {
    var newNumber = Random.nextInt(60)

    while (newNumber in list) {
        newNumber = Random.nextInt(60)
    }
    list[index] = newNumber
    return list
}

fun sortResult(result: MutableList<Int>, ascending: Boolean): MutableList<Int>{
    var newResult = result.toMutableList()
    newResult.sort()

    if (!ascending){
        newResult.reverse()
    }

    return newResult

}
