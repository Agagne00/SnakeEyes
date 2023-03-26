package com.example.snakeeyes

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.snakeeyes.databinding.ActivityMainBinding
import kotlin.random.Random
// TODO: change background color
class MainActivity : AppCompatActivity(){
    private var alertDialog: AlertDialog? = null
private lateinit var binding: ActivityMainBinding
    //initialize the dice results
    var results = IntArray(6)
    var totalDice = 0
    var avgDice = 0f
    // TODO: save these results somewhere when the app closes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
     setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        //val rollbtn = findViewById<Button>(R.id.rollButton)
        //val recordbtn = findViewById<Button>(R.id.recordButton)
       // val resetbtn = findViewById<Button>(R.id.resetButton)
        //rollbtn.setOnClickListener(this)
        //recordbtn.setOnClickListener(this)
        //resetbtn.setOnClickListener(this)
    }

    fun rollResults() {
        try {
            val numDice = findViewById<EditText>(R.id.numberOfDice).text.toString().toInt()
            val hitRange = findViewById<EditText>(R.id.numberToHit).text.toString().toInt()
            val critRange = findViewById<EditText>(R.id.numberToCrit).text.toString().toInt()
        }
        catch (e:NumberFormatException) {
            // TODO: shout at user to format properly
        }
        finally {
            val numDice = findViewById<EditText>(R.id.numberOfDice).text.toString().toInt()
            val hitRange = findViewById<EditText>(R.id.numberToHit).text.toString().toInt()
            val critRange = findViewById<EditText>(R.id.numberToCrit).text.toString().toInt()
            val record = findViewById<CheckBox>(R.id.recordCheckBox).isChecked
            var newResults = IntArray(6)
            var newRolls = List(numDice) { Random.nextInt(1, 7) }.sorted()
            var numHits = 0
            var numCrits = 0
            for (i in newRolls) {
                newResults[i - 1]++
                if (i >= hitRange) numHits++
                if (i >= critRange) numCrits++
            }
            numHits -= numCrits

            if (record) {
                for (i in IntRange(0, newResults.size - 1)) results[i] += newResults[i]
            }
            //build string to be displayed
            var str = ""
            for (i in newRolls) {
                str = "$str $i"
            }
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Results")
            alertDialogBuilder.setMessage("Results: $str\n$numHits hits\n$numCrits crits")
            alertDialog = alertDialogBuilder.create()
            alertDialog!!.show()
            updateResults()
        }
    }

    fun addResults() {

        val values = findViewById<EditText>(R.id.batchAdd).text.toString()
        for (i in 0 until values.length) {
            if (values[i].digitToInt() in listOf(1,2,3,4,5,6)) results[values[i].digitToInt()-1]++
        }
        findViewById<EditText>(R.id.batchAdd).setText("")
        updateResults()
    }

    fun resetResults() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Warning")
        alertDialogBuilder.setMessage("This will delete all data from the current session.  Are you sure you want to do this?")
        alertDialogBuilder.setPositiveButton("Delete") { _: DialogInterface, _: Int -> run {for (i in IntRange(0,results.size-1)) results[i]=0
            updateResults()
            findViewById<TextView>(R.id.numValues)?.text = totalDice.toString()
            findViewById<TextView>(R.id.avgRecorded)?.text = "%.3f".format(avgDice)}}
        alertDialogBuilder.setNegativeButton("Cancel") { _: DialogInterface, _: Int -> }
        alertDialog = alertDialogBuilder.create()
        alertDialog!!.show()
    }
    private fun updateResults() {
        // update the text to reflect the changes to the # of values and the average value
        var totalSoFar = 0
        var sumSoFar = 0
        for (i in IntRange(0,results.size-1)) {
            totalSoFar+=results[i]
            sumSoFar+=(i+1)*results[i]
        }
        totalDice = totalSoFar
        avgDice = sumSoFar.toFloat() / totalSoFar.toFloat()
    }
}