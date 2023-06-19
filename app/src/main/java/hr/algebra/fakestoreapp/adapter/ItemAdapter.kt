package hr.algebra.fakestoreapp.adapter

import android.content.ContentUris
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.fakestoreapp.FAKESTORE_PROVIDER_CONTENT_URI
import hr.algebra.fakestoreapp.ItemDetailsActivity
import hr.algebra.fakestoreapp.POSITION
import hr.algebra.fakestoreapp.R
import hr.algebra.fakestoreapp.framework.startActivity
import hr.algebra.fakestoreapp.model.Item
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File

class ItemAdapter(private val context: Context, private val items: MutableList<Item>)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivItem = itemView.findViewById<ImageView>(R.id.ivItem)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvPriceNumber = itemView.findViewById<TextView>(R.id.tvPriceNumber)

        fun bind(item: Item) {
            Picasso.get()
                .load(File(item.picturePath))
                .error(R.drawable.nasa)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivItem)
            tvTitle.text = item.title
            tvPriceNumber.text=item.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    override fun getItemCount()=items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.itemView.setOnLongClickListener {
            deleteItem(position)
            true
        }
        holder.itemView.setOnClickListener {
            context.startActivity<ItemDetailsActivity>(POSITION, position)
        }

        holder.bind(item)
    }
    private fun deleteItem(position: Int) {
        val item = items[position]
        context.contentResolver.delete(
            ContentUris.withAppendedId(FAKESTORE_PROVIDER_CONTENT_URI, item._id!!),
            null,
            null
        )
        File(item.picturePath).delete()
        items.removeAt(position)
        notifyDataSetChanged()
    }

}