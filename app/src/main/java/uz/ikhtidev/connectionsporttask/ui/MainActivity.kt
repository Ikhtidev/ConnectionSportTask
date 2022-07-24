package uz.ikhtidev.connectionsporttask.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.ikhtidev.connectionsporttask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, FirstLevelActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            Toast.makeText(this, "Sozlamalar", Toast.LENGTH_SHORT).show()
        }

        binding.btnLevel.setOnClickListener {
            Toast.makeText(this, "O'yin darajasi", Toast.LENGTH_SHORT).show()
        }

    }
}