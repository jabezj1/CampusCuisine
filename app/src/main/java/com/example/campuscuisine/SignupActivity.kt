package com.example.campuscuisine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class SignupActivity : AppCompatActivity() {

//    var allPosts: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        findViewById<Button>(R.id.signupbtn).setOnClickListener {
            val username = findViewById<EditText>(R.id.UsernameSU).text.toString()
            val password = findViewById<EditText>(R.id.PasswordSU).text.toString()
            userSignUp(username, password)
//            goToLoginActivity()
        }

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()
        }

//        queryName()
    }

    private fun userSignUp(username: String, password: String) {
        val user = ParseUser()

// Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // Hooray! Let them use the app now.
                Toast.makeText(this,"Sign Up was Successful! Please Log in", Toast.LENGTH_SHORT). show()
                goToLoginActivity()
            } else {
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
                e.printStackTrace()
                Toast.makeText(this,"Failed to Sign up. Please Try again", Toast.LENGTH_SHORT). show()
            }
        }
    }


    private fun goToLoginActivity(){
        val intent = Intent(this@SignupActivity, LoginActivity:: class.java)
        startActivity(intent)
        finish()

    }


    companion object {
        const val TAG = "SignupActivity"
    }
}
