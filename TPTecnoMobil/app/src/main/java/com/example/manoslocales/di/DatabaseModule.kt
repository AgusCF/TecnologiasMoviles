package com.example.manoslocales.di

import android.content.Context
import androidx.room.Room
import com.example.manoslocales.data.local.AppDatabase
import com.example.manoslocales.data.local.dao.ProductDao
import com.example.manoslocales.data.local.dao.SellerDao
import com.example.manoslocales.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "manos_locales_db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideSellerDao(database: AppDatabase): SellerDao {
        return database.sellerDao()
    }
}