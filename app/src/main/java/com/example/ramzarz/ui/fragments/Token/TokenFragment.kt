package com.example.ramzarz.ui.fragments.Token

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ramzarz.ui.MainActivity
import com.example.ramzarz.R
import com.example.ramzarz.databinding.FragmentTokenBinding
import com.example.ramzarz.ui.adapter.TokensAdapter

class TokenFragment : Fragment() {

    private lateinit var _binding: FragmentTokenBinding
    private lateinit var tokensAdapter: TokensAdapter

    private val tokenViewModel : TokenViewModel by lazy {
        (activity as MainActivity?)!!.getSharedViewModel(TokenViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return inflater.inflate(R.layout.fragment_token,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTokenBinding.bind(view)

        tokenViewModel.getTokens()
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
        _binding.rlTokens.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = tokensAdapter
        }

        tokensAdapter.setOnItemClickListenerForFav { token->
            token.favoriteToken = !token.favoriteToken
            Log.d("TAG", "display for fav initRecyclerView: " + token.favoriteToken)
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
                R.id.action_navigation_token_to_detailsFragment,
                bundle
            )
            Log.d("TAG", "display: id " + it.favoriteToken)
        }
        displayTokens()
    }


    private fun displayTokens(){
        _binding.progressBar.visibility = View.VISIBLE


        tokenViewModel.tokensLiveData.observe(viewLifecycleOwner){
            if (it!=null){
                Log.d("TAG", "display Tokens: " + it.size)
                _binding.progressBar.visibility = View.GONE
                tokensAdapter.differ.submitList(it)
            }
            else{
                Toast.makeText(activity,"something went wrong",Toast.LENGTH_SHORT).show()
                _binding.progressBar.visibility = View.GONE
            }
        }
    }
}