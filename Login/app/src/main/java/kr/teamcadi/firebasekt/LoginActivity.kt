package kr.teamcadi.firebasekt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kr.teamcadi.firebasekt.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnSignin.setOnClickListener {
            val userEmail = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            auth.signInWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        startActivity(
                            Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Log.w("LoginActivity", "signInWithEmail", it.exception);
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                }
        }

        binding.btnSignup.setOnClickListener {
            val userEmail = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            auth.createUserWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        startActivity(
                        Intent(this, MainActivity::class.java))
                        finish()
                    }
                    else {
                        Log.w("LoginActivity", "signInWithEmail", it.exception);
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }
}