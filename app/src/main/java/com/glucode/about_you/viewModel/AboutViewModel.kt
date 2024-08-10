package com.glucode.about_you.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.repository.EngineersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
  private val engineersRepository: EngineersRepository
) : ViewModel() {

  private val _engineers = MutableLiveData<List<Engineer>>()
  val engineers: LiveData<List<Engineer>> get() = _engineers

  private val _engineer = MutableLiveData<Engineer?>()
  val engineer: LiveData<Engineer?> get() = _engineer

  private val _error = MutableLiveData<String>()
  val error: LiveData<String> get() = _error

  fun fetchEngineers() {
    viewModelScope.launch {
      try {
        _engineers.value = engineersRepository.getEngineers()
      } catch (e: Exception) {
        _error.value = "Failed to fetch engineers: ${e.localizedMessage}"
      }
    }
  }

  fun loadEngineer(name: String) {
    viewModelScope.launch {
      try {
        _engineer.value = engineersRepository.loadEngineer(name)
      } catch (e: Exception) {
        _error.value = "Failed to load engineer: ${e.localizedMessage}"
      }
    }
  }
}
