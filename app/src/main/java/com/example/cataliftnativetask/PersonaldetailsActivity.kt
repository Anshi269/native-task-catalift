package com.example.cataliftnativetask

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.example.cataliftnativetask.R

class PersonalDetailsActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etStudentId: EditText
    private lateinit var spinnerDay: Spinner
    private lateinit var spinnerMonth: Spinner
    private lateinit var spinnerYear: Spinner
    private lateinit var spinnerMotherTongue: Spinner
    private lateinit var spinnerGender: Spinner
    private lateinit var etPhoneNumber: EditText
    private lateinit var etEmail: EditText
    private lateinit var etLinkedIn: EditText
    private lateinit var submitButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personaldetails)
        setAsteriskRed(findViewById(R.id.tvFullNameLabel), "Full Name *")
        setAsteriskRed(findViewById(R.id.tvDobLabel), "DOB *")
        setAsteriskRed(findViewById(R.id.tvMotherTongueLabel), "Mother Tongue *")
        setAsteriskRed(findViewById(R.id.tvGenderLabel), "Gender *")
        setAsteriskRed(findViewById(R.id.tvPhoneNumberLabel), "Phone Number *")
        setAsteriskRed(findViewById(R.id.tvEmailLabel), "Email Address *")

        initializeViews()
        setupSpinners()
        submitButton = findViewById(R.id.btnSubmit)

        submitButton.setOnClickListener {
            validateForm()
        }

        // Pre-fill data for demo purposes
        etFullName.setText("Aman Singh")
        etStudentId.setText("190909512")
    }

    private fun validateForm() {
        var isValid = true
        // Validate Full Name
        if (etFullName.text.isNullOrEmpty()) {
            etFullName.error = "This field is required"
            isValid = false
        }
        // Validate Phone Number
        if (etPhoneNumber.text.isNullOrEmpty()) {
            etPhoneNumber.error = "This field is required"
            isValid = false
        }

        // Validate Email
        if (etEmail.text.isNullOrEmpty()) {
            etEmail.error = "This field is required"
            isValid = false
        }
        if (isValid) {
            //Submit
        }
    }

    fun setAsteriskRed(label: TextView, text: String) {
            val formatted = HtmlCompat.fromHtml(
                text.replace("*", "<font color='#FF0000'>*</font>"),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            label.text = formatted
        }

    private fun initializeViews() {
        etFullName = findViewById(R.id.etFullName)
        etStudentId = findViewById(R.id.etStudentId)
        spinnerDay = findViewById(R.id.spinnerDay)
        spinnerMonth = findViewById(R.id.spinnerMonth)
        spinnerYear = findViewById(R.id.spinnerYear)
        spinnerMotherTongue = findViewById(R.id.spinnerMotherTongue)
        spinnerGender = findViewById(R.id.spinnerGender)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etEmail = findViewById(R.id.etEmail)
        etLinkedIn = findViewById(R.id.etLinkedIn)
    }

    private fun setupSpinners() {
        // Setup Day Spinner
        val days = (1..31).map { it.toString() }.toTypedArray()
        val dayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, days)
        spinnerDay.adapter = dayAdapter

        // Setup Month Spinner
        val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        val monthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, months)
        spinnerMonth.adapter = monthAdapter

        // Setup Year Spinner
        val currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
        val years = (currentYear - 100..currentYear).reversed().map { it.toString() }.toTypedArray()
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, years)
        spinnerYear.adapter = yearAdapter

        // Setup Mother Tongue Spinner
        val languages = arrayOf("English", "Hindi", "Bengali", "Telugu", "Marathi", "Tamil", "Urdu", "Kannada", "Gujarati", "Other")
        val languageAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, languages)
        spinnerMotherTongue.adapter = languageAdapter
        // Set default to English
        spinnerMotherTongue.setSelection(0)

        // Setup Gender Spinner
        val genders = arrayOf("Male", "Female", "Non-binary", "Prefer not to say")
        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genders)
        spinnerGender.adapter = genderAdapter
        // Set default to Male
        spinnerGender.setSelection(0)
    }
}