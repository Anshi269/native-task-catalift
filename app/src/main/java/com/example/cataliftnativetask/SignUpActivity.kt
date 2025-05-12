package com.example.cataliftnativetask

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var etPhoneNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etPassword = findViewById(R.id.etPassword)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val phone = etPhoneNumber.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (phone.isEmpty()) {
                etPhoneNumber.error = "Enter phone number"
            } else if (password.isEmpty()) {
                etPassword.error = "Enter password"
            } else {
                Toast.makeText(this, "Sign up successful (demo)", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
