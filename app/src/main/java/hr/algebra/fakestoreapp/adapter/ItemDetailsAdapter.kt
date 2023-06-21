package hr.algebra.fakestoreapp.adapter

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.fakestoreapp.FAKESTORE_PROVIDER_CONTENT_URI
import hr.algebra.fakestoreapp.R
import hr.algebra.fakestoreapp.model.Item
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File

class ItemDetailsAdapter (private val context: Context, private val items: MutableList<Item>)
    : RecyclerView.Adapter<ItemDetailsAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivItem = itemView.findViewById<ImageView>(R.id.ivItem)
      //  val btnBuy = itemView.findViewById<Button>(R.id.btnBuy)

        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvPriceNumber = itemView.findViewById<TextView>(R.id.tvPriceNumber)
        private val tvDesciption = itemView.findViewById<TextView>(R.id.tvDescription)
         val etRating = itemView.findViewById<EditText>(R.id.etRating)
         val btnUpdateRating = itemView.findViewById<Button>(R.id.btnUpdateRating)


        fun bind(item: Item) {
            Picasso.get()
                .load(File(item.picturePath))
                .error(R.drawable.nasa)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivItem)
            tvTitle.text = item.title
            tvPriceNumber.text = item.price.toString()+" $"
            tvDesciption.text = item.description
            etRating.setText(item.rating.toString())

            //btnBuy.setText(if(item.bought) "unbuy" else "buy")

            //ivRead.setImageResource(if(item.read) R.drawable.green_flag else R.drawable.red_flag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_details, parent, false)
        )
    }

    override fun getItemCount()=items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.btnUpdateRating.setOnClickListener {
            val newRating = holder.etRating.text.toString().toDouble()

            context.contentResolver.update(
                ContentUris.withAppendedId(FAKESTORE_PROVIDER_CONTENT_URI,item._id!!),
                ContentValues().apply {
                    put(Item::rating.name,newRating)
                },
                null, null
            )
            items[position].rating=newRating
            notifyItemChanged(position)
        }
/*        holder.btnBuy.setOnClickListener {
            // update
            item.bought = !item.bought

            context.contentResolver.update(
                ContentUris.withAppendedId(FAKESTORE_PROVIDER_CONTENT_URI,item._id!!),
                ContentValues().apply {
                    put(Item::bought.name, item.bought)
                },
                null, null
            )
            notifyItemChanged(position)
        }
*/
        holder.bind(item)
    }

    }
