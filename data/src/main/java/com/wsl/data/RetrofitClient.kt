package com.wsl.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wsl.data.pokemon.service.PokemonApiService
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import kotlin.collections.ArrayList

fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun provideGsonInstance(): Gson = GsonBuilder().create()

fun provideService(retrofit: Retrofit): PokemonApiService {
    return retrofit.create(PokemonApiService::class.java)
}

fun providePlainOkHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient().newBuilder()
        .cookieJar(SessionCookieJar())
    okHttpClientBuilder.networkInterceptors().add(UserAgentInterceptor())

    return okHttpClientBuilder.build()
}

//fun provideTokensInterceptor(): Interceptor =
//    Interceptor { chain ->
//        val timestamp = Date().time
//
//        val original: Request = chain.request()
//        val originalURL: HttpUrl = original.url()
//
//        val usurperURL: HttpUrl = originalURL.newBuilder()
//            .addQueryParameter("ts", timestamp.toString())
//            .addQueryParameter("apikey", API_KEY)
//            .addQueryParameter("hash", "${timestamp}$PRIVATE_API_KEY$API_KEY".createHash())
//            .build()
//
//        val usurperRequest = original.newBuilder()
//            .url(usurperURL)
//            .build()
//
//        chain.proceed(usurperRequest)
//    }

fun provideCookieJar(): CookieJar = SessionCookieJar()

private class SessionCookieJar : CookieJar {

    private var cookies: List<Cookie>? = null

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        if (url.encodedPath().endsWith("login")) {
            this.cookies = ArrayList(cookies)
        }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return if (!url.encodedPath().endsWith("login") && cookies != null) {
            this.cookies!!
        } else listOf<Cookie>()
    }
}

fun String.createHash(): String {
    val crypt = MessageDigest.getInstance("MD5");
    crypt.update(this.toByteArray())
    return BigInteger(1, crypt.digest()).toString(16)
}
