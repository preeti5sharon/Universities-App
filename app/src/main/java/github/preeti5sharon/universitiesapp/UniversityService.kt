package github.preeti5sharon.universitiesapp

import retrofit2.Response
import retrofit2.http.GET

interface UniversityService {
    @GET("/search?country=india")
    suspend fun getUniversities(): Response<List<UniversityResponseItem>>
}