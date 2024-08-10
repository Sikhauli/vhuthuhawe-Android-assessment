package com.glucode.about_you.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.repository.EngineersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EngineersViewModel @Inject constructor(
  private val repository: EngineersRepository
) : ViewModel() {

  private val _engineers = MutableLiveData<List<Engineer>>()
  val engineers: LiveData<List<Engineer>> get() = _engineers

  fun loadEngineers() {
    viewModelScope.launch(Dispatchers.IO) {
      _engineers.postValue(repository.getEngineers())
    }
  }

  fun sortEngineersBy(criteria: (Engineer) -> Int) {
    _engineers.value = _engineers.value?.sortedBy(criteria)
  }
}
