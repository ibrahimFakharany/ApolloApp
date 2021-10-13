package com.example.apollographqltutorial.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.apollographqltutorial.databinding.FragmentProductsListBinding
import com.example.apollographqltutorial.util.toast
import com.example.apollographqltutorial.view.state.ViewState
import com.example.apollographqltutorial.viewModel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_products_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private lateinit var binding: FragmentProductsListBinding

    private val viewModel by viewModels<ProductsViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
button.setOnClickListener{
    viewModel.queryProducts(editText.text.toString())
}
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.charactersList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                }
                is ViewState.Success -> {
                        val results = response.value?.data?.products?.items
                        context?.toast(results?.size.toString())
                }
                is ViewState.Error -> {
context?.toast("error")
                }
            }
        }
    }

}