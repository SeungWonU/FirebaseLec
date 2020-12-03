package com.example.cloudfirestore


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.cloudfirestore.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        auth = Firebase.auth
//        if (auth.currentUser == null) {
//            startActivity(
//                    Intent(this, LoginActivity::class.java))
//            finish()
//        }
//
//        binding.textUID.text = auth.currentUser?.uid ?: "No User"
//
//        binding.buttonSignout.setOnClickListener {
//            auth.signOut()
//            finish()
//        }


    }

}