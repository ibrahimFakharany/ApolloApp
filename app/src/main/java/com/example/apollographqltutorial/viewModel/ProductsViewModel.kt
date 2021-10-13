package com.example.apollographqltutorial.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.annotation.ExperimentalCoilApi
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.apollographqltutorial.FetchProductsByCategoryQuery
import com.example.apollographqltutorial.repo.ProductsRepo
import com.example.apollographqltutorial.view.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoilApi
@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductsRepo
) : ViewModel() {

    private val _productsList by lazy { MutableLiveData<ViewState<Response<FetchProductsByCategoryQuery.Data>>>() }

    val charactersList: LiveData<ViewState<Response<FetchProductsByCategoryQuery.Data>>>
        get() = _productsList

    fun queryProducts(catId: String) = viewModelScope.launch {
        _productsList.postValue(ViewState.Loading())
        try {
            val response = repository.getProducts(catId)
            _productsList.postValue(ViewState.Success(response))
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _productsList.postValue(ViewState.Error("Error fetching Products"))
        }
    }

}