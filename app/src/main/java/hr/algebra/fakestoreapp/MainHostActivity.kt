package hr.algebra.fakestoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.firebase.auth.FirebaseAuth
import hr.algebra.fakestoreapp.databinding.ActivityMainBinding
import hr.algebra.fakestoreapp.fragment.AboutFragment
import hr.algebra.fakestoreapp.fragment.ShopItemsFragment
import hr.algebra.fakestoreapp.fragment.StoreMapFragment
import hr.algebra.fakestoreapp.framework.startActivity

class MainHostActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        replaceFragment(ShopItemsFragment())
        initBottomNavigationMenu()
        initHamburgerMenu()
        initProfileNavigation()
    }



    private fun initHamburgerMenu() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    private fun initBottomNavigationMenu(){
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
    private fun initProfileNavigation(){
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menuLogin->startActivity<LoginActivity>()
                R.id.menuRegister->startActivity<RegisterActivity>()
                R.id.menuLogout->{
                    firebaseAuth.signOut()
                    binding.navView.menu.getItem(0).title=""
                }

            }
            true
        }
    }
/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                toggleDrawer()
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }
    private fun toggleDrawer() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawers()
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
            setLoggedInUserText()
        }
    }

    private fun setLoggedInUserText() {
        val user=firebaseAuth.currentUser
        user?.let {
            binding.navView.menu.getItem(0).title=user.email
        }
    }
}