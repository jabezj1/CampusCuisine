package com.example.campuscuisine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        findViewById<Button>(R.id.signupbtn).setOnClickListener {
//            val fullName = findViewById<EditText>(R.id.FullName).text.toString()
            val username = findViewById<EditText>(R.id.UsernameSU).text.toString()
            val password = findViewById<EditText>(R.id.PasswordSU).text.toString()
            userSignUp(username, password)
        }

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun userSignUp(username: String, password: String) {
        // Create the ParseUser
        val user = ParseUser()
//
//        val query: ParseQuery<User> = ParseQuery.getQuery(User::class.java)
//        query.include(User.KEY_USER)
//        // Set fields for the user to be created

        user.setUsername(username)
        user.setPassword(password)
//        user.getString(fullName)

        user.signUpInBackground { e ->
            if (e == null) {
                // User has created an account
                //TODO:Show logcat/ toast
                Toast.makeText(this, "Sign Up was Successful! Please Log in", Toast.LENGTH_SHORT)
                    .show()
//                goToLoginActivity()
            } else {
                // TODO: Show Toast for error
                Log.i(TAG, "Error logging in ")
                e.printStackTrace()
                Toast.makeText(this, "Failed to Sign up. Please Try again", Toast.LENGTH_SHORT)
                    .show()
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
