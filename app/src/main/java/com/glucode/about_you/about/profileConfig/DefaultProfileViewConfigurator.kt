package com.glucode.about_you.about.profileConfig

import android.content.Context
import androidx.core.content.ContextCompat
import com.glucode.about_you.R
import com.glucode.about_you.about.views.ProfileView
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.utils.loadImageFromInternalStorage

interface ProfileViewConfigurator {
  fun configureProfileView(profileView: ProfileView, engineer: Engineer, context: Context)
}

class DefaultProfileViewConfigurator : ProfileViewConfigurator {
  override fun configureProfileView(profileView: ProfileView, engineer: Engineer, context: Context) {
    profileView.profileName = engineer.name
    profileView.profilePosition = engineer.role
    profileView.yearsOfExperience = engineer.quickStats.years
    profileView.cupOfCoffee = engineer.quickStats.coffees
    profileView.bugsNumber = engineer.quickStats.bugs
    profileView.profileImage = loadImageFromInternalStorage(context, engineer.name)
    profileView.profileNameColor = ContextCompat.getColor(context, R.color.white)
    profileView.profilePositionColor = ContextCompat.getColor(context, R.color.white)

    profileView.yearsOfExperienceColor = ContextCompat.getColor(context, R.color.black)
    profileView.cupOfCoffeeColor = ContextCompat.getColor(context, R.color.black)
    profileView.bugsNumberColor = ContextCompat.getColor(context, R.color.black)

    profileView.yearsOfExperienceLabelColor = ContextCompat.getColor(context, R.color.black)
    profileView.cupOfCoffeeLabelColor = ContextCompat.getColor(context, R.color.black)
    profileView.bugsNumberLabelColor = ContextCompat.getColor(context, R.color.black)

    profileView.yearsOfExperienceLabel = "Years"
    profileView.cupOfCoffeeLabel = "Coffees"
    profileView.bugsNumberLabel = "Bugs"
  }
}
