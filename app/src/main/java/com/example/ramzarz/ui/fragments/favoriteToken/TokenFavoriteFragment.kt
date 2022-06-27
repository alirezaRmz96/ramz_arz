package com.example.ramzarz.ui.fragments.favoriteToken

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ramzarz.R
import com.example.ramzarz.databinding.FragmentFavoriteTokenBinding
import com.example.ramzarz.databinding.FragmentTokenBinding
import com.example.ramzarz.ui.MainActivity
import com.example.ramzarz.ui.fragments.Token.TokenViewModel
import com.example.ramzarz.ui.adapter.TokensAdapter

class TokenFavoriteFragment : Fragment() {

    private lateinit var _binding: FragmentFavoriteTokenBinding
    private lateinit var tokensAdapter: TokensAdapter


    private val tokenFavoriteViewModel : TokenFavoriteViewModel by lazy {
        (activity as MainActivity?)!!.getSharedViewModel(TokenFavoriteViewModel::class.java)
    }
    private val tokenViewModel : TokenViewModel by lazy {
        (activity as MainActivity?)!!.getSharedViewModel(TokenViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_favorite_token,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteTokenBinding.bind(view)
        initRecyclerView()


    }
    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.showBottomNavigationView()
        }

    }
    private fun initRecyclerView(){
        tokensAdapter = TokensAdapter()
        _binding.rlFavorite.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = tokensAdapter
        }

        tokensAdapter.setOnItemClickListenerForFav { token ->
            token.favoriteToken = !token.favoriteToken
            Log.d("TAG", "display in fav initRecyclerView: " + token.favoriteToken)
            tokenViewModel.update(token)
        }
        tokensAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_token",it)
            }
            if (requireActivity() is MainActivity) {
                (activity as MainActivity?)!!.hideBottomNavigationView()
            }
            findNavController().navigate(
                R.id.action_navigation_favorite_to_detailsFragment,
                bundle
            )
            Log.d("TAG", "display: id " + it.favoriteToken)
        }
        displayTokens()
    }
    private fun displayTokens(){
//        tokenViewModel.getTokens()
        tokenFavoriteViewModel.favToken.observe(viewLifecycleOwner){ toktok->
            if (toktok.isNotEmpty()){
                tokensAdapter.differ.submitList(toktok)
            }else{
                _binding.tv.visibility = View.VISIBLE
                _binding.rlFavorite.visibility = View.GONE
            }

            Log.d("TAG", "display: " + toktok )
        }
    }
}