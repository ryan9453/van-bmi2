package com.example.bmi2

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AlertDialog
import com.example.bmi2.databinding.ActivityMainBinding
import android.widget.Toast.makeText as makeText1

class MainActivity : AppCompatActivity() {
    val REQEST_DISPLAY_BMI = 16
    private val TAG = MainActivity::class.java.simpleName
    lateinit var binding: ActivityMainBinding
    val calculation = Calculation()
    var launcher = registerForActivityResult(NameContract()) { name ->
        Toast.makeText(this,name, Toast.LENGTH_LONG).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: ")
        // OnClickListener是一個介面
        /*binding.bHelp.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
            }
        })*/
        // 上面是最一開始的嚴謹寫法
        // 這行是簡潔版
        binding.bHelp.setOnClickListener {
            Log.d("MainActivity","Need help!")
            Log.d("MainActivty", "onCreate: ")
        }

    }

    fun bmi(view: View) {
        /*
        用 log 顯示的字串函式，依嚴重程度分成下列幾個等級
        Log.v (verbose）
        Log.d (debug)
        Log.i (information)
        Log.w (warning)
        log.e (error)
        log.wtf (what a terrible mistake)
         */
        var weight = binding.edWeight.text.toString().toFloat()
        var height = binding.edHeight.text.toString().toFloat()
        var bmi = weight/(height*height)
//        Log.d("MainActivity", bmi.toString())

        // 浮動訊息
        Toast.makeText(this, "Your BMI $bmi", Toast.LENGTH_LONG).show()


        // 須重複使用的對話框，存到一個變數
        // 生成建置器
        val builder = AlertDialog.Builder(this)
        // 設定「設定值」
        builder.setTitle("Hello")
        builder.setMessage("Your BMI is $bmi")
        builder.setPositiveButton("OK", null)
        // 根據前面設定值生 對話框
        val dialog = builder.create()
        // 秀出視窗
//        dialog.show()

        // 不須重複使用的對話框，秀完即不見
        AlertDialog.Builder(this)
            .setTitle("Hi")
            .setMessage("Your BMI is $bmi")
//            .setPositiveButton("cool", null)
            .setPositiveButton("OK") { dialog, which ->
                binding.edWeight.setText("")
                binding.edHeight.setText("")
            }

//            .show()
        binding.tvBmi.text = "Your BMI is $bmi"
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(Extras.BMI,bmi)


//        startActivity(intent)  // 不須回傳值的方法
//        startActivityForResult(intent, REQEST_DISPLAY_BMI) // 需要回傳值的方法


        launcher.launch(bmi)

    }
    // 設計專門處理 Intent的合約, class NameContract 為抽象類別, 功能介於類別跟介面之間
    // 合約後面的<>
    class NameContract : ActivityResultContract<Float, String>() {
        override fun createIntent(context: Context, input: Float?): Intent {
            return Intent(context, ResultActivity::class.java)
                .putExtra(Extras.BMI, input)
        }
        override fun parseResult(resultCode: Int, intent: Intent?): String {
            if (resultCode == RESULT_OK) {
                val name = intent?.getStringExtra(Extras.NAME)
                return name!!
            } else {
                return "No name"
            }
        }

    }

    // 只有接收別的 Activity帶資料回來專用的類別
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //  代表從 MainActivity過去且有按 Done回來
        if (requestCode == REQEST_DISPLAY_BMI && resultCode == RESULT_OK) {
            Log.d(TAG, "onActivityResult")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onCreate: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onCreate: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onCreate: ")
    }
}

