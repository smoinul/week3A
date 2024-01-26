package msayed.example.testingweek3a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    lateinit var tvName: TextView
    lateinit var myspinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        tvName = findViewById(R.id.textView)
        myspinner = findViewById(R.id.spinner)
        var name:String = intent.extras!!.getString("name", "No-name")
        var value:Int = intent.extras!!.getInt("value", 1)
        tvName.setText(name)
        myspinner.setSelection(value-1)
    }

    fun onClickClose(view: View) {
        //Companion Object
        MainActivity.name = tvName.text.toString()
        MainActivity.value = myspinner.selectedItemPosition+1

        //Contract
        val backToLastIntent: Intent = Intent(this, MainActivity::class.java).apply {
            putExtra("name", tvName.text.toString())
            putExtra("value", myspinner.selectedItemPosition+1)
        }
        setResult(RESULT_OK,backToLastIntent )
        //

        //destroys this activity and return to the last stacked
        finish()
        //creates many instace of same activity not recommended
        //val backToLastIntent: Intent = Intent(this, MainActivity::class.java)
        //startActivity(backToLastIntent)
    }
}