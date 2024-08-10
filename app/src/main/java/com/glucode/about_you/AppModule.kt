package com.glucode.about_you

import com.glucode.about_you.about.profileConfig.DefaultProfileViewConfigurator
import com.glucode.about_you.about.profileConfig.ProfileViewConfigurator
import com.glucode.about_you.repository.EngineersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideEngineersRepository(): EngineersRepository {
    return EngineersRepository()
  }

  @Provides
  fun provideProfileViewConfigurator(): ProfileViewConfigurator {
    return DefaultProfileViewConfigurator()
  }
}