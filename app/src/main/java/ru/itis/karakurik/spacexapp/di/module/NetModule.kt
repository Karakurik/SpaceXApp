package ru.itis.karakurik.spacexapp.di.module

import androidx.viewbinding.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.itis.karakurik.spacexapp.data.network.SpaceXApi
import ru.itis.karakurik.spacexapp.di.qualifier.BaseUrl
import ru.itis.karakurik.spacexapp.di.qualifier.LoggingInterceptor
import java.io.File
import javax.inject.Qualifier

private const val BASE_URL = "https://api.spacexdata.com"

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Provides
    fun provideOkHttpClient(
        @LoggingInterceptor loggingInterceptor: Interceptor,
        cache: Cache,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .cache(cache)
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(
                        loggingInterceptor
                    )
                }
            }
            .build()

    @Provides
    fun provideApi(
        okHttpClient: OkHttpClient,
        @BaseUrl baseUrl: String,
        converterFactory: GsonConverterFactory,
        callAdapterFactory: CallAdapter.Factory
    ): SpaceXApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
            .create(SpaceXApi::class.java)

    @Provides
    fun provideGsonFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava3CallAdapterFactory.create()
    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideCacheDirectory(): File {
        return File("cache");
    }

    @Qualifier
    annotation class CacheMaxSize

    @CacheMaxSize
    @Provides
    fun provideCacheMaxSize(): Long {
        return 50 * 1024 * 1024;
    }

    @Provides
    fun provideCache(
        cacheDirectory: File,
        @CacheMaxSize cacheMaxSize: Long,
    ): Cache {
        return Cache(cacheDirectory, cacheMaxSize)
    }
}
