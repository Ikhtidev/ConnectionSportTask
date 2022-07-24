package uz.ikhtidev.connectionsporttask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.ikhtidev.connectionsporttask.databinding.ActivitySecondLevelBinding

class SecondLevelActivity : AppCompatActivity() {

    lateinit var binding:ActivitySecondLevelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}