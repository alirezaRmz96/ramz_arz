package com.example.ramzarz.ui.fragments.favoriteToken

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.ramzarz.data.model.token.TokensItem
import com.example.ramzarz.domain.useCase.TokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TokenFavoriteViewModel @Inject constructor(
    private val tokenUseCase: TokenUseCase
) : ViewModel() {

    val favToken:LiveData<List<TokensItem>> = tokenUseCase.getFavoriteTokenUseCase.invoke().asLiveData()


}