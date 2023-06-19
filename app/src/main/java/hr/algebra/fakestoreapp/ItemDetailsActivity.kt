package hr.algebra.fakestoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.algebra.fakestoreapp.adapter.ItemDetailsAdapter
import hr.algebra.fakestoreapp.databinding.ActivityItemDetailsBinding
import hr.algebra.fakestoreapp.framework.fetchItems
import hr.algebra.fakestoreapp.model.Item

const val POSITION = "hr.algebra.fakestoreapp.position"
class ItemDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemDetailsBinding
    private lateinit var items: MutableList<Item>
    private var itemPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPager()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    private fun initPager() {
        items=fetchItems()
        itemPosition=intent.getIntExtra(POSITION,itemPosition)
        binding.viewPager.adapter=ItemDetailsAdapter(this,items)
        binding.viewPager.currentItem = itemPosition

    }
}