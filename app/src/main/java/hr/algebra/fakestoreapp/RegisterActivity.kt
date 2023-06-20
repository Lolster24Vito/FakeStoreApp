package hr.algebra.fakestoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hr.algebra.fakestoreapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater);
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        initRegisterButton()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun initRegisterButton() {
        binding.goToLoginTv.setOnClickListener{
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegister.setOnClickListener{
            val email=binding.emailEt.text.toString()
            val pass=binding.passwordEt.text.toString()
            val confirmPass=binding.confirmPasswordEt.text.toString()

            if(email.isNotEmpty()&&pass.isNotEmpty()&&confirmPass.isNotEmpty()){
                if(pass.equals(confirmPass))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent= Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty fields are not allowed", Toast.LENGTH_SHORT).show()

            }
        }
    }
}