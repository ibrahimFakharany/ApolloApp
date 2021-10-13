package com.example.apollographqltutorial.repo

import com.apollographql.apollo.api.Response
import com.example.apollographqltutorial.FetchProductsByCategoryQuery

interface ProductsRepo {
    suspend fun getProducts(catId : String): Response<FetchProductsByCategoryQuery.Data>
}