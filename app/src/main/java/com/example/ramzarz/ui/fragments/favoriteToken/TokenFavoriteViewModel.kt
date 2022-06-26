package com.example.ramzarz.ui.fragments.favoriteToken

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ramzarz.data.model.TokenItem
import com.example.ramzarz.domain.useCase.TokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TokenFavoriteViewModel @Inject constructor(
    private val tokenUseCase: TokenUseCase
) : ViewModel() {

    val favToken:LiveData<List<TokenItem>> = tokenUseCase.getFavoriteTokenUseCase.invoke().asLiveData()


}