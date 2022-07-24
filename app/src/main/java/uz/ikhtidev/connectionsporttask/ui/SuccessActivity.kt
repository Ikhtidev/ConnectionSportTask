package uz.ikhtidev.connectionsporttask.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.ikhtidev.connectionsporttask.databinding.ActivitySuccessBinding

class SuccessActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMain.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
            Toast.makeText(this, "Keyngi daraja", Toast.LENGTH_SHORT).show()
        }

    }
}