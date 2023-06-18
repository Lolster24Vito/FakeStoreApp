package hr.algebra.fakestoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import hr.algebra.fakestoreapp.databinding.ActivityMainBinding
import hr.algebra.fakestoreapp.fragment.AboutFragment
import hr.algebra.fakestoreapp.fragment.ShopItemsFragment
import hr.algebra.fakestoreapp.fragment.StoreMapFragment

class MainHostActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ShopItemsFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                 R.id.shopAbout->replaceFragment(AboutFragment())
                R.id.shopItems->replaceFragment(ShopItemsFragment())
                R.id.shopMap-> replaceFragment(StoreMapFragment())
            }
            true
        }
    }
    private fun replaceFragment(fragment:Fragment) {
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}