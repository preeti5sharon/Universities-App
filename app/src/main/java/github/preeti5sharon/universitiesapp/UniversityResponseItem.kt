package github.preeti5sharon.universitiesapp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UniversityResponseItem(
    @Json(name = "alpha_two_code")
    val alphaTwoCode: String?,
    val country: String?,
    val domains: List<String?>?,
    val name: String?,
    @Json(name = "state-province")
    val stateProvince: String?,
    @Json(name = "web_pages")
    val webPages: List<String?>?
)