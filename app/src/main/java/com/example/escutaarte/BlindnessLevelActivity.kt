package com.example.escutaarte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.escutaarte.utils.setOnMultiClickActions

class BlindnessLevelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blindness_level)

        val adminButton: ImageButton = findViewById(R.id.AdminButton)
        val partialBlindnessButton: Button = findViewById(R.id.PartialBlindButton)
        val totalBlindButton: Button = findViewById(R.id.TotalBlindButton)

        partialBlindnessButton.setOnMultiClickActions(
            onSingleClick = {},
            onDoubleClick = {
                val partialBlindnessIntent: Intent = Intent(this, PartialBlindnessActivity::class.java)
                startActivity(partialBlindnessIntent)
            },
            onTripleClick = {},
            onHold = {}
        )

        totalBlindButton.setOnMultiClickActions(
            onSingleClick = {},
            onDoubleClick = {
                val totalBlindnessIntent: Intent = Intent(this, TotalBlindnessActivity::class.java)
                startActivity(totalBlindnessIntent)
            },
            onTripleClick = {},
            onHold = {Toast.makeText(this@BlindnessLevelActivity, "Hold", Toast.LENGTH_SHORT).show()}
        )

    }
}


