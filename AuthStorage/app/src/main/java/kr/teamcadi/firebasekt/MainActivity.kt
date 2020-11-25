package kr.teamcadi.firebasekt

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kr.teamcadi.firebasekt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var storage: FirebaseStorage


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Firebase.auth.currentUser ?: finish()


        //Storage 부분
        storage = Firebase.storage
        val storageRef = storage.reference // reference to root
        val imageRef1 = storageRef.child("/spring.PNG")
        val imageRef2 = storageRef.child("/summer.PNG")
        val imageRef3 = storageRef.child("/fall.PNG")
        val imageRef4 = storageRef.child("/winter.PNG")
        displayImageRef(imageRef1, binding.imgSeason) // 이 함수는 맨 아래에 선언


        //config 부분
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 1 // For test purpose only, 3600 seconds for production
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        binding.buttonFetchActivate.setOnClickListener {
            remoteConfig.fetchAndActivate()
                    .addOnCompleteListener(this) {
                        val season = remoteConfig.getString("season")
                        if (season.toString().equals("spring")) {
                            Toast.makeText(this, "계절은 봄", Toast.LENGTH_SHORT).show()
                            displayImageRef(imageRef1, binding.imgSeason)

                        } else if (season.toString().equals("summer")) {
                            Toast.makeText(this, "계절은 여름", Toast.LENGTH_SHORT).show()
                              displayImageRef(imageRef2, binding.imgSeason)

                        } else if (season.toString().equals("fall")) {
                            Toast.makeText(this, "계절은 가을", Toast.LENGTH_SHORT).show()
                             displayImageRef(imageRef3, binding.imgSeason)

                        } else if (season.toString().equals("winter")) {
                            Toast.makeText(this, "계절은 겨울", Toast.LENGTH_SHORT).show()
                             displayImageRef(imageRef4, binding.imgSeason)

                        }
                    }
        }







        ///////////////////////////////////////////////
        //지난 과제 로그인 파트 부분
        auth = Firebase.auth
        if (auth.currentUser == null) {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
            finish()
        }
        else {
            binding.txtUid.text = auth.currentUser?.uid ?: "No User"
        }

        binding.btnSignout.setOnClickListener {
            auth.signOut()
            startActivity(
                Intent(this, LoginActivity::class.java))
            finish()
        }
    }


    ///////////////////////////////////////////////
    //이미지 변환 함수

    private fun displayImageRef(imageRef: StorageReference?, view: ImageView) {
        imageRef?.getBytes(Long.MAX_VALUE)?.addOnSuccessListener {
            val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
            view.setImageBitmap(bmp)
        }?.addOnFailureListener {
            // Failed to download the image
        }
    }

}