package hr.algebra.fakestoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.algebra.fakestoreapp.databinding.ActivitySplashScreenBinding
import hr.algebra.fakestoreapp.framework.*

private const val DELAY = 3000L
const val DATA_IMPORTED = "hr.algebra.nasa.data_imported"

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startAnimations()
        redirect()
    }
    private fun startAnimations() {
        binding.tvSplash.applyAnimation(R.anim.fadein)
        binding.ivSplash.applyAnimation(R.anim.fadein)
    }
    private fun redirect() {
        val booleanPreference = getBooleanPreference(DATA_IMPORTED)
        if (getBooleanPreference(DATA_IMPORTED)) {
            callDelayed(DELAY) {
                startActivity<MainHostActivity>()
                //startActivity<LoginActivity>()
            }

        } else {
            if (isOnline()) {
                //remove this
                startActivity<MainHostActivity>()
                //   NasaService.enqueue(this)
            } else {
                binding.tvSplash.text = getString(R.string.no_internet)
                callDelayed(DELAY) { finish() }
            }
        }
    }
}