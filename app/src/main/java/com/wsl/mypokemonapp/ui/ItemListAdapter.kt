package com.wsl.mypokemonapp.ui

import android.R.color
import android.graphics.Color
import android.os.Build
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BulletSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wsl.domain.pokemon.model.Pokemon
import com.wsl.mypokemonapp.R
import com.wsl.mypokemonapp.databinding.ItemCardviewBinding
import kotlinx.android.synthetic.main.item_cardview.view.*


class ItemListAdapter(private val onItemClick: (id: Int) -> Unit):
    ListAdapter<Pokemon, ItemListAdapter.ItemViewHolder>(
        ItemListAdapter.CustomDiffUtils()
    ) {

        private lateinit var binding: ItemCardviewBinding

        class ItemViewHolder(private val binding: ItemCardviewBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: Pokemon) = with(itemView) {
                val path = "${item.sprites?.frontDefault}"
                Picasso.get()
                    .load(path)
                    .fit()
                    .placeholder(R.drawable.placeholder)
                    .into(this.itemImage)

                binding.itemName.text = item.name
                binding.itemType.setStringList( item.types.map { it.type.name } )
                binding.itemCounter.text = item.id.toString()
            }

            private fun TextView.setStringList(listString: List<String>) {
                var mainText = "Type:"
                for (item in listString){
                    mainText = StringBuilder(mainText).append("\n\t-$item").toString()
                }
                this.text = mainText
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int): ItemViewHolder {
            binding = ItemCardviewBinding
                .inflate(
                    LayoutInflater
                        .from(
                            parent.context
                        ),
                    parent,
                    false
                )
            return ItemViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.itemView.setOnClickListener { onItemClick(getItem(position).id) }
            holder.bind(getItem(position))
        }

        private class CustomDiffUtils: DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(
                oldItem: Pokemon,
                newItem: Pokemon
            ): Boolean = oldItem == newItem
            override fun areContentsTheSame(
                oldItem: Pokemon,
                newItem: Pokemon
            ): Boolean = oldItem.id == newItem.id
        }

}