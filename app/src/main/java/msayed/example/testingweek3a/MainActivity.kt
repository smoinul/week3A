package msayed.example.testingweek3a

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    companion object{
        var name:String = ""
        var value:Int = 1
    }

    lateinit var editTextname: EditText
    lateinit var radioGroup: RadioGroup
    var lastChecked = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextname = findViewById(R.id.editTextName)
        radioGroup = findViewById(R.id.radioGroup)
    }

    fun onClickNext(view: View) {
        var radioButtonid = radioGroup.checkedRadioButtonId
        when(radioButtonid){
            R.id.radioButton -> lastChecked = 1
            R.id.radioButton2 -> lastChecked =2
            R.id.radioButton3 -> lastChecked =3
            R.id.radioButton4 -> lastChecked =4
        }
        val secondIntent: Intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("name", editTextname.text.toString())
            putExtra("value", lastChecked)
        }
        //startActivity(secondIntent)
        resulLauncher.launch(secondIntent)
    }

    val resulLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
        val resultValue = result.data!!.getIntExtra("value", 1)
        when(resultValue){
            1->radioGroup.check(R.id.radioButton)
            2->radioGroup.check(R.id.radioButton2)
            3->radioGroup.check(R.id.radioButton3)
            4->radioGroup.check(R.id.radioButton4)
        }
        when(result.resultCode){
            Activity.RESULT_OK -> {
                //Toast.makeText(this, getString(R.string.OK), Toast.LENGTH_SHORT).show()
                var view:View = findViewById(R.id.MainLayout)
                val snack = Snackbar.make(view, getString(R.string.OK), Snackbar.LENGTH_SHORT)
                snack.show()
            }
            else -> {
                Toast.makeText(this, getString(R.string.NOT_OK), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //Companion----
        /*editTextname.setText(MainActivity.name)
        when(MainActivity.value){
            1->radioGroup.check(R.id.radioButton)
            2->radioGroup.check(R.id.radioButton2)
            3->radioGroup.check(R.id.radioButton3)
            4->radioGroup.check(R.id.radioButton4)
        }*/
        //----

        //SharedPreferences
        val prefsEditor = getSharedPreferences("mySettings", Context.MODE_PRIVATE)
        editTextname.setText(prefsEditor.getString("name", "No-name"))
    }

    override fun onPause() {
        super.onPause()
        val prefsEditor = getSharedPreferences("mySettings", Context.MODE_PRIVATE).edit()
        prefsEditor.putString("name", editTextname.text.toString())
        prefsEditor.apply()
    }

}