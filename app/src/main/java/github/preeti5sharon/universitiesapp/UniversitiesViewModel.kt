package github.preeti5sharon.universitiesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversitiesViewModel @Inject constructor(private val repository: UniversitiesRepository): ViewModel() {
    private var _universitiesList = MutableStateFlow<List<UniversityResponseItem>?>(emptyList())
    val universitiesList = _universitiesList.asStateFlow()
    fun getUniversitiesList() = viewModelScope.launch(Dispatchers.IO){
        _universitiesList.value = repository.getUniversities().body()
    }
    init {
        getUniversitiesList()
    }
}