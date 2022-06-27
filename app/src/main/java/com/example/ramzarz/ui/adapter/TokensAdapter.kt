package com.example.ramzarz.ui.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ramzarz.R
import com.example.ramzarz.data.model.token.TokensItem
import com.example.ramzarz.databinding.TokenItemBinding
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class TokensAdapter(
    ) : RecyclerView.Adapter<TokensAdapter.TokensViewHolder>() {

    private val callback = object :DiffUtil.ItemCallback<TokensItem>(){
        override fun areItemsTheSame(oldItem: TokensItem, newItem: TokensItem): Boolean {
            return oldItem.currency == newItem.currency
        }

        override fun areContentsTheSame(oldItem: TokensItem, newItem: TokensItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TokensViewHolder {
        val binding = TokenItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TokensViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(tokens:TokensItem){

            Glide.with(binding.imgBit.context)
                .load(tokens.logo_url)
                .into(binding.imgBit)

            var dateTime = ZonedDateTime.parse(tokens.price_date)

            binding.tokenPerPrice.text = binding.root.context.getString(R.string.range_per_dominance,tokens.market_cap_dominance?.toDouble())
            binding.txtBit.text = tokens.name
            binding.txtDollar.text = binding.root.context.getString(R.string.today_dollar,tokens.price?.toDouble())
            binding.timeStamp.text = dateTime.withZoneSameInstant(ZoneId.of("UTC")).format(
                DateTimeFormatter.ofPattern("hh:mm:ss a")
            )

            val day = tokens.oneD?.price_change_pct!!.toDouble() * 100

            tokens.oneD.let {
                    d ->
                if (d.price_change_pct!!.contains("-")){
                    binding.tokenPerPrice.text = binding.root.context.getString(R.string.range_neg_main, day)
                    binding.tokenPerPrice.setTextColor(Color.parseColor("#ED2020"))
                    binding.deOrIn.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.deOrIn.context,
                            R.drawable.down
                        )
                    )
                }
                else{
                    binding.tokenPerPrice.text = binding.root.context.getString(R.string.range_main, day)
                    binding.tokenPerPrice.setTextColor(Color.parseColor("#4CAF50"))
                    binding.deOrIn.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.deOrIn.context,
                            R.drawable.up
                        )
                    )
                }
            }

            if (tokens.favoriteToken){
                binding.favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.favorite.context,
                        R.drawable.ic_star_24
                    )
                )
            }
            else{
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

    private var onItemClickListenerForFav: ((TokensItem) -> Unit)? = null
    fun setOnItemClickListenerForFav(listener: (TokensItem) -> Unit) {
        onItemClickListenerForFav = listener
    }
    private var onItemClickListener: ((TokensItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (TokensItem) -> Unit) {
        onItemClickListener = listener
    }

}