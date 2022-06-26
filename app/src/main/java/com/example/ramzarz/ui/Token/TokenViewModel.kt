package com.example.ramzarz.ui.Token

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ramzarz.data.model.Token
import com.example.ramzarz.data.model.TokenItem
import com.example.ramzarz.data.until.Resource
import com.example.ramzarz.domain.useCase.GetTokenUseCase
import com.example.ramzarz.domain.useCase.TokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TokenViewModel @Inject constructor(
    private val tokenUseCase: TokenUseCase
) : ViewModel() {

    val tokenLiveData : MutableLiveData<Resource<Token>> = MutableLiveData()
    val tokensLiveData : MutableLiveData<List<TokenItem>?> = MutableLiveData()

    fun getToken() = viewModelScope.launch {
        tokenLiveData.postValue(Resource.Loading())
        try {
            val apiResult = tokenUseCase.getTokenUseCase.execute()
            tokenLiveData.postValue(apiResult)
        }catch (e: Exception) {
            tokenLiveData.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getTokens() = viewModelScope.launch {
        val tokenList = tokenUseCase.getTokensUseCase.execute()
        tokensLiveData.postValue(tokenList)
    }
    fun updateTokens() = viewModelScope.launch {
        val tokenList = tokenUseCase.updateTokenUseCase.execute()
        tokensLiveData.postValue(tokenList)
    }
}