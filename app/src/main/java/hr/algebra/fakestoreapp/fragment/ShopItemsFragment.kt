package hr.algebra.fakestoreapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.fakestoreapp.R
import hr.algebra.fakestoreapp.adapter.ItemAdapter
import hr.algebra.fakestoreapp.api.ProductItem
import hr.algebra.fakestoreapp.databinding.FragmentShopItemsBinding
import hr.algebra.fakestoreapp.framework.fetchItems
import hr.algebra.fakestoreapp.model.Item


class ShopItemsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var  binding:FragmentShopItemsBinding
    private lateinit var items:MutableList<Item>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        items=requireContext().fetchItems()
        binding= FragmentShopItemsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvItems.apply{
            layoutManager=LinearLayoutManager(requireContext())
            adapter= ItemAdapter(requireContext(),items)
        }
    }

}