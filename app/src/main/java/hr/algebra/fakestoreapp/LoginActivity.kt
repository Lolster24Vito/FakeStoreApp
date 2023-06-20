package hr.algebra.fakestoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hr.algebra.fakestoreapp.databinding.ActivityLoginBinding
import hr.algebra.fakestoreapp.framework.startActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        initButtons()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun initButtons() {
        binding.btnLogin.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passwordEt.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // val intent = Intent(this, LoginActivity::class.java)
                        // startActivity(intent)
                        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show()
                        startActivity<MainHostActivity>()
                    } else {
                        Toast.makeText(this, "Error:"+it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }

                }
            } else {
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()

            }


        }
        binding.textView.setOnClickListener {
            startActivity<RegisterActivity>()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}