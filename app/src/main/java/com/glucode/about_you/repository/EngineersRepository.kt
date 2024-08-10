package com.glucode.about_you.repository

import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EngineersRepository @Inject constructor() {

  suspend fun getEngineers(): List<Engineer> {
    return withContext(Dispatchers.IO) {
      MockData.engineers
    }
  }

  suspend fun loadEngineer(name: String): Engineer? {
    return withContext(Dispatchers.IO) {
      MockData.engineers.find { it.name == name }
    }
  }


}
