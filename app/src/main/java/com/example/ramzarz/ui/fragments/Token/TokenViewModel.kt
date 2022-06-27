package com.example.ramzarz.ui.fragments.Token

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ramzarz.data.model.token.Tokens
import com.example.ramzarz.data.model.token.TokensItem
import com.example.ramzarz.data.until.Resource
import com.example.ramzarz.domain.useCase.TokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TokenViewModel @Inject constructor(
    private val tokenUseCase: TokenUseCase
) : ViewModel() {


    val tokensLiveData : MutableLiveData<List<TokensItem>?> = MutableLiveData()


    fun getTokens() = viewModelScope.launch {
        val tokenList = tokenUseCase.getTokensUseCase.execute()
        tokensLiveData.postValue(tokenList)
    }

    fun update(tokenItem: TokensItem) = viewModelScope.launch(Dispatchers.IO) {
        tokenUseCase.updateUseCase.execute(tokenItem)
    }

    // i just write for if we need refresh button then we can use to update or cache and database with new data
    fun updateTokens() = viewModelScope.launch {
        val tokenList = tokenUseCase.updateTokenUseCase.execute()
        tokensLiveData.postValue(tokenList)
    }
}