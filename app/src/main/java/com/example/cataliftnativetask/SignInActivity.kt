package com.example.cataliftnativetask

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class SignInActivity : AppCompatActivity() {

    private lateinit var etPhoneNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignIn: Button
    private lateinit var tvSignUp: TextView
    private lateinit var tvForgotPassword: TextView
    private lateinit var tvPrivacyPolicy: TextView
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val tvPrivacyPolicy = findViewById<TextView>(R.id.tvPrivacyPolicy)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)
        // Apply underline to "Privacy Policy"
        val privacyPolicySpan = SpannableString("Privacy Policy")
        privacyPolicySpan.setSpan(UnderlineSpan(), 0, privacyPolicySpan.length, 0)
        tvPrivacyPolicy.text = privacyPolicySpan
        // Apply underline to "Sign-Up"
        val signUpSpan = SpannableString("Sign-Up")
        signUpSpan.setSpan(UnderlineSpan(), 0, signUpSpan.length, 0)
        tvSignUp.text = signUpSpan

        initializeViews()
        setupGoogleSignIn()
        setupClickListeners()
    }

    private fun setupGoogleSignIn() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Add Google Sign-In button or link
        findViewById<ImageView>(R.id.ivGoogleIcon).setOnClickListener{
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Google Sign In was successful, proceed to app
            Toast.makeText(this, "Signed in as: ${account.email}", Toast.LENGTH_SHORT).show()

            // Navigate to Personal Details
            val intent = Intent(this, PersonalDetailsActivity::class.java)
            startActivity(intent)

        } catch (e: ApiException) {
            // Google Sign In failed
            Toast.makeText(this, "Google sign in failed: ${e.statusCode}", Toast.LENGTH_SHORT).show()
        }
        }

    private fun initializeViews() {
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etPassword = findViewById(R.id.etPassword)
        btnSignIn = findViewById(R.id.btnSignIn)
        tvSignUp = findViewById(R.id.tvSignUp)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        tvPrivacyPolicy = findViewById(R.id.tvPrivacyPolicy)
    }

    private fun setupClickListeners() {
        btnSignIn.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateInputs(phoneNumber, password)) {
                // For demo purposes, just navigate to next screen
                // In a real app, you would authenticate with your backend
                Toast.makeText(this, "Sign-in successful", Toast.LENGTH_SHORT).show()

                // Optional: Implement sign-in with Google here

                // Navigate to Personal Details screen
                val intent = Intent(this, PersonalDetailsActivity::class.java)
                startActivity(intent)
            }
        }

        tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        tvForgotPassword.setOnClickListener {
            // Handle forgot password functionality
            Toast.makeText(this, "Forgot Password clicked", Toast.LENGTH_SHORT).show()
        }

        tvPrivacyPolicy.setOnClickListener {
            startActivity(Intent(this, PrivacyPolicyActivity::class.java))
        }
    }

    private fun validateInputs(phoneNumber: String, password: String): Boolean {
        if (phoneNumber.isEmpty()) {
            etPhoneNumber.error = "Phone number is required"
            return false
        }

        if (password.isEmpty()) {
            etPassword.error = "Password is required"
            return false
        }

        return true
    }
}