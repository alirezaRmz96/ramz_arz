package com.example.ramzarz.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ramzarz.R
import com.example.ramzarz.data.model.TokenItem
import com.example.ramzarz.databinding.TokenItemBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.coroutineContext

class TokensAdapter(
    ) : RecyclerView.Adapter<TokensAdapter.TokensViewHolder>() {

    private val callback = object :DiffUtil.ItemCallback<TokenItem>(){
        override fun areItemsTheSame(oldItem: TokenItem, newItem: TokenItem): Boolean {
            return oldItem.currency == newItem.currency
        }

        override fun areContentsTheSame(oldItem: TokenItem, newItem: TokenItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TokensViewHolder {
        val binding = TokenItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TokensViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TokensViewHolder, position: Int) {
        val tokens = differ.currentList[position]
        holder.bind(tokens)
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class TokensViewHolder(
        private val binding:TokenItemBinding
    ):RecyclerView.ViewHolder(binding.root){
        val sdf = SimpleDateFormat("hh:mm:ss")
        val currentDate = sdf.format(Date())
        @SuppressLint("StringFormatInvalid")
        fun bind(tokens:TokenItem){

            binding.tokenPerPrice.text = binding.root.context.getString(R.string.range_per_dominance,tokens.market_cap_dominance)
            binding.txtBit.text = tokens.name
            binding.timeStamp.text = currentDate

            Glide.with(binding.imgBit.context)
                .load(tokens.logo_url)
                .into(binding.imgBit)

            if (tokens.favoriteToken){
                binding.favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.favorite.context,
                        R.drawable.ic_star_24
                    )
                )
            } else{
                binding.favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.favorite.context,
                        R.drawable.ic_star_border
                    )
                )
            }
            binding.favorite.setOnClickListener{
                onItemClickListenerForFav?.let {
                    //tokens.favoriteToken != tokens.favoriteToken
                    if (tokens.favoriteToken){
                        binding.favorite.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.favorite.context,
                                R.drawable.ic_star_border
                            )
                        )
                    } else{
                        binding.favorite.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.favorite.context,
                                R.drawable.ic_star_24
                            )
                        )
                    }
                    it(tokens)
                }
            }
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(tokens)
                }
            }
        }
    }

    private var onItemClickListenerForFav: ((TokenItem) -> Unit)? = null
    fun setOnItemClickListenerForFav(listener: (TokenItem) -> Unit) {
        onItemClickListenerForFav = listener
    }
    private var onItemClickListener: ((TokenItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (TokenItem) -> Unit) {
        onItemClickListener = listener
    }

}