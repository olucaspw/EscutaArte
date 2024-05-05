package com.example.escutaarte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.escutaarte.utils.setOnMultiClickActions

class TotalBlindnessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_blindness)

        val leftButton: ImageButton = findViewById(R.id.LeftButton)
        val rightButton: ImageButton = findViewById(R.id.RightButton)

        leftButton.setOnMultiClickActions(
            onSingleClick = {},
            onDoubleClick = {},
            onTripleClick = {
                val blindnessLevelIntent: Intent = Intent(this, BlindnessLevelActivity::class.java)
                startActivity(blindnessLevelIntent)
            },
            onHold = {}
        )

        rightButton.setOnMultiClickActions(
            onSingleClick = {},
            onDoubleClick = {},
            onTripleClick = {
                val blindnessLevelIntent: Intent = Intent(this, BlindnessLevelActivity::class.java)
                startActivity(blindnessLevelIntent)
            },
            onHold = {}
        )




    }
}