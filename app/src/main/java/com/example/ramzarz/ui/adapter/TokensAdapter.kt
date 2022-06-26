package com.example.ramzarz.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ramzarz.data.model.TokenItem
import com.example.ramzarz.databinding.TokenItemBinding
import java.text.SimpleDateFormat
import java.util.*

class TokensAdapter : RecyclerView.Adapter<TokensAdapter.TokensViewHolder>() {

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
        fun bind(tokens:TokenItem){

            binding.tokenPerPrice.text = tokens.market_cap_dominance
            binding.tokenName.text = tokens.name
            binding.timeStamp.text = currentDate

            Glide.with(binding.tokenImage.context)
                .load(tokens.logo_url)
                .into(binding.tokenImage)

            binding.root.setOnClickListener{
                onItemClickListener?.let {
                    it(tokens)
                }
            }
        }
    }

    private var onItemClickListener: ((TokenItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (TokenItem) -> Unit) {
        onItemClickListener = listener
    }

}