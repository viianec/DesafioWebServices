package com.example.marvel.comic.repository

class HqRepository () {
    private val _client = IHqEndpoint.endpoint
    suspend fun getHqs(offset: Int? = 0) = _client.getHqs(offset)
}