package uz.shokirov.addermyporflios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var skilss = findViewById<Button>(R.id.skills)
        var portfolios = findViewById<Button>(R.id.portfolios)
        skilss.setOnClickListener {
            startActivity(Intent(this,AddSkillsActivity::class.java))
        }
        portfolios.setOnClickListener {
            startActivity(Intent(this,AddPortfoliActivity::class.java))
        }


    }
}