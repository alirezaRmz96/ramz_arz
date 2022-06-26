package com.example.ramzarz.ui.fragments.details

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ramzarz.R
import com.example.ramzarz.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var _binding : FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)
        val args: DetailsFragmentArgs by navArgs()
        val token = args.selectedToken

        _binding.apply {
            Glide.with(ivBtn.context)
                .load(token.logo_url)
                .into(ivBtn)
            tvBtn.text = token.name
            tvBazarD.text = getString(R.string.price_bazar,token.price)

            token.oneD.let {
                d ->
                if (d!!.price_change_pct!!.contains("-")){
                    tvRangeDay.text = getString(R.string.range_neg, token.oneD?.price_change_pct)
                    tvRangeDay.setTextColor(Color.parseColor("#ED2020"))
                    ivRangeDay.setImageDrawable(
                        ContextCompat.getDrawable(
                            ivRangeDay.context,
                            R.drawable.down
                        )
                    )
                }
                else{
                    tvRangeDay.text = getString(R.string.range, token.oneD?.price_change_pct)
                    tvRangeDay.setTextColor(Color.parseColor("#4CAF50"))
                    ivRangeDay.setImageDrawable(
                        ContextCompat.getDrawable(
                            ivRangeDay.context,
                            R.drawable.up
                        )
                    )
                }
            }

            token.weekD.let {
                dxxx ->
                if (dxxx!!.price_change_pct!!.contains("-")){
                    tvRangeWeek.text = getString(R.string.range_neg, token.weekD?.price_change_pct)
                    tvRangeWeek.setTextColor(Color.parseColor("#ED2020"))
                    ivRangeWeek.setImageDrawable(
                        ContextCompat.getDrawable(
                            ivRangeWeek.context,
                            R.drawable.down
                        )
                    )
                }
                else{
                    tvRangeWeek.text = getString(R.string.range, token.weekD?.price_change_pct)
                    tvRangeWeek.setTextColor(Color.parseColor("#4CAF50"))
                    ivRangeWeek.setImageDrawable(
                        ContextCompat.getDrawable(
                            ivRangeWeek.context,
                            R.drawable.up
                        )
                    )
                }
            }

            token.monthD.let {
                dx ->
                if (dx!!.price_change_pct!!.contains("-")){
                    tvRangeMonth.text = getString(R.string.range_neg, token.monthD?.price_change_pct)
                    tvRangeMonth.setTextColor(Color.parseColor("#ED2020"))
                    ivRangeMonth.setImageDrawable(
                        ContextCompat.getDrawable(
                            ivRangeMonth.context,
                            R.drawable.down
                        )
                    )
                }
                else{
                    tvRangeMonth.text = getString(R.string.range, token.monthD?.price_change_pct)
                    tvRangeMonth.setTextColor(Color.parseColor("#4CAF50"))
                    ivRangeMonth.setImageDrawable(
                        ContextCompat.getDrawable(
                            ivRangeMonth.context,
                            R.drawable.up
                        )
                    )
                }
            }

        }
    }
}