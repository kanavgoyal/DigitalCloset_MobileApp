package com.smartcloset.india.di

import android.content.Context
import androidx.room.Room
import com.smartcloset.india.data.local.SmartClosetDatabase
import com.smartcloset.india.data.local.dao.ClosetDao
import com.smartcloset.india.data.local.dao.ClosetLayoutDao
import com.smartcloset.india.data.remote.LlmApi
import com.smartcloset.india.data.repository.ClosetRepositoryImpl
import com.smartcloset.india.domain.repository.ClosetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SmartClosetDatabase =
        Room.databaseBuilder(context, SmartClosetDatabase::class.java, "smart_closet.db").build()

    @Provides
    fun provideClosetDao(db: SmartClosetDatabase): ClosetDao = db.closetDao()

    @Provides
    fun provideLayoutDao(db: SmartClosetDatabase): ClosetLayoutDao = db.layoutDao()

    @Provides
    @Singleton
    fun provideRepository(impl: ClosetRepositoryImpl): ClosetRepository = impl

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideLlmApi(retrofit: Retrofit): LlmApi = retrofit.create(LlmApi::class.java)
}
