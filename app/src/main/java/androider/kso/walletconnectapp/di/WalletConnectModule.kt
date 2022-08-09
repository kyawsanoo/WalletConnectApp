package androider.kso.walletconnectapp.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.walletconnect.impls.FileWCSessionStore
import org.walletconnect.impls.WCSessionStore
import java.io.File
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WalletConnectModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideSessionStorage(
        @ApplicationContext context: Context,
        moshi: Moshi,
    ): WCSessionStore = FileWCSessionStore(File(context.cacheDir, "session_store.json").apply { createNewFile() },
        moshi)
}