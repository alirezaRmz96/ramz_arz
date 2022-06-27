package com.example.ramzarz.ui.fragments.details

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
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

        val tokens = args.selectedToken

        _binding.apply {
            Glide.with(ivBtn.context)
                .load(tokens.logo_url)
                .into(ivBtn)
            tvBtn.text = tokens.name
            tvBazarD.text = getString(R.string.price_bazar,tokens.price)
            detailToolbar.setNavigationOnClickListener { v->
                Navigation.findNavController(v).navigateUp()
            }
            val day = tokens.oneD?.price_change_pct!!.toDouble() * 100

            val year = tokens.yearD?.price_change_pct!!.toDouble() * 100
            Log.d("TAG", "show me: " + year)

            val month = tokens.monthD?.price_change_pct!!.toDouble() * 100

            Log.d("TAG1", "onViewCreated: $day")
            tokens.oneD.let {
                d ->
                if (d.price_change_pct!!.contains("-")){
                    tvRangeDay.text = getString(R.string.range_neg, day)
                    tvRangeDay.setTextColor(Color.parseColor("#ED2020"))
                    ivRangeDay.setImageDrawable(
                        ContextCompat.getDrawable(
                            ivRangeDay.context,
                            R.drawable.down
                        )
                    )
                }
                else{
                    tvRangeDay.text = getString(R.string.range, day)
                    tvRangeDay.setTextColor(Color.parseColor("#4CAF50"))
                    ivRangeDay.setImageDrawable(
                        ContextCompat.getDrawable(
                            ivRangeDay.context,
                            R.drawable.up
                        )
                    )
                }
            }

            tokens.yearD.let {
                dxxx ->
                if (dxxx?.price_change_pct!!.contains("-")){
                    tvRangeYear.text = getString(R.string.range_neg,year)
                    tvRangeYear.setTextColor(Color.parseColor("#ED2020"))
                    ivRangeYear.setImageDrawable(
                        ContextCompat.getDrawable(
                            ivRangeYear.context,
                            R.drawable.down
                        )
                    )
                }
                else{
                    tvRangeYear.text = getString(R.string.range, year)
                    tvRangeYear.setTextColor(Color.parseColor("#4CAF50"))
                    ivRangeYear.setImageDrawable(
                        ContextCompat.getDrawable(
                            ivRangeYear.context,
                            R.drawable.up
                        )
                    )
                }
            }

            tokens.monthD.let {
                dx ->
                if (dx.price_change_pct!!.contains("-")){
                    tvRangeMonth.text = getString(R.string.range_neg, month)
                    tvRangeMonth.setTextColor(Color.parseColor("#ED2020"))
                    ivRangeMonth.setImageDrawable(
                        ContextCompat.getDrawable(
                            ivRangeMonth.context,
                            R.drawable.down
                        )
                    )
                }
                else{
                    tvRangeMonth.text = getString(R.string.range, month)
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