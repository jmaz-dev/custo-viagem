package com.example.gastoviagem

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gastoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.buttonSubmit.setOnClickListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_submit) {
            calculate()
        }


    }

    fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()

    }

    private fun isValid(): Boolean {

        if (
            binding.editDistance.text.isNullOrBlank() ||
            binding.editPrice.text.isNullOrBlank() ||
            binding.editAuto.text.isNullOrBlank()

        ) {
            showToast(getString(R.string.errPreencher))
            return true
        }
        if (
            binding.editDistance.text.toString() == "." ||
            binding.editPrice.text.toString() == "." ||
            binding.editAuto.text.toString() == "."
        ) {
            showToast(getString(R.string.errPreencher))
            return true
        }
        if (binding.editAuto.text.toString().toFloat() < 1) {
            showToast(getString(R.string.errAutonomia))
            return true
        }


        return false
    }

    private fun calculate() {
        try {
            if (isValid()) return

            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomia = binding.editAuto.text.toString().toFloat()


            val totalValue = (distance * price) / autonomia


            binding.textTotalPrice.text = "R$ ${String.format("%.2f", totalValue)}"

            Toast.makeText(this, "R$ ${String.format("%.2f", totalValue)}", Toast.LENGTH_SHORT)
                .show()
        } catch (ex: Exception) {
            print(ex)
            Toast.makeText(this, getString(R.string.errInesperado), Toast.LENGTH_SHORT).show()

        }
    }
}