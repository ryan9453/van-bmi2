package com.example.bmi2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bmi2.databinding.ActivityMainBinding
import com.example.bmi2.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private val TAG = ResultActivity::class.java.simpleName
    lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showBmi()
        binding.btDone.setOnClickListener {
            val name = binding.edName.text.toString()
            val data = Intent()
            data.putExtra(Extras.NAME, name)
            setResult(RESULT_OK, data)
            finish()
        }
    }

    private fun showBmi() {
        val bmi = intent.getFloatExtra(Extras.BMI, 0f)
        Log.d(TAG, "BMI = $bmi")
        binding.tvShowbmi.text = bmi.toString()
    }
}