package github.preeti5sharon.universitiesapp

class UniversitiesRepository(private val service: UniversityService) {
    suspend fun getUniversities() = service.getUniversities()
}
