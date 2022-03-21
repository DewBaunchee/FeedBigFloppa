package by.varyvoda.matvey

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val floppa: ImageView = findViewById(R.id.floppaPoster)
        val enterButton: Button = findViewById(R.id.enterButton)

        floppa.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out))

        firebaseAuth = Firebase.auth
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            try {
                val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                    .getResult(ApiException::class.java)
                if (account != null) authenticate(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(this, "Authentication unavailable", Toast.LENGTH_SHORT).show()
            }
        }

        enterButton.setOnClickListener {
            signInGoogle()
        }
    }

    private fun toMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun signInGoogle() {
        launcher.launch(getClient().signInIntent)
    }

    private fun getClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.requestIdToken))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(this, gso)
    }

    private fun authenticate(idToken: String) {
        val credentials = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credentials).addOnCompleteListener {
            if (it.isSuccessful) {
                toMain()
            } else {
                Toast.makeText(this, "Unsuccessful authentication", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) toMain()
    }
}