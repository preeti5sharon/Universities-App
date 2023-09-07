package github.preeti5sharon.universitiesapp

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesMoshi() = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi) =
        Retrofit.Builder().baseUrl("http://universities.hipolabs.com").addConverterFactory(
            MoshiConverterFactory.create(moshi),
        ).build()

    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit) = retrofit.create(UniversityService::class.java)

    @Provides
    @Singleton
    fun providesRepository(service: UniversityService) = UniversitiesRepository(service)
}
