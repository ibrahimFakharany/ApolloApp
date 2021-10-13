package com.example.apollographqltutorial.repo

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.example.apollographqltutorial.FetchProductsByCategoryQuery
import com.example.apollographqltutorial.networking.ProductsApi
import javax.inject.Inject

class ProductsRepoImpl @Inject constructor(private val webService: ProductsApi) : ProductsRepo {
    override suspend fun getProducts(catId: String): Response<FetchProductsByCategoryQuery.Data> {
        return webService.getApolloClient().query(FetchProductsByCategoryQuery(listOf(catId)))
            .await()
    }


}