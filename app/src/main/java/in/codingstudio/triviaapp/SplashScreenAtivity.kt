package `in`.codingstudio.triviaapp

import `in`.codingstudio.triviaapp.data.PersonViewModel
import `in`.codingstudio.triviaapp.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class SplashScreenAtivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_ativity)

        Executors.newSingleThreadScheduledExecutor().schedule({

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, 2, TimeUnit.SECONDS)

    }

}