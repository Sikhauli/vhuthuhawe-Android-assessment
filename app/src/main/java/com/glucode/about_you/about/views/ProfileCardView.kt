package com.glucode.about_you.about.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.glucode.about_you.R
import com.glucode.about_you.about.ImagePickerFragment
import com.glucode.about_you.utils.ImageUtils

class ProfileCardView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  private val binding: ViewProfileBinding = ViewProfileBinding.inflate(LayoutInflater.from(context), this)
  private val fragmentManager: FragmentManager = (context as? FragmentActivity)?.supportFragmentManager
    ?: throw IllegalStateException("Context is not a FragmentActivity")

  var profileName: String? = null
    set(value) {
      field = value
      binding.profileName.text = value
    }

  var profilePosition: String? = null
    set(value) {
      field = value
      binding.profilePosition.text = value
    }

  var yearsOfExperienceLabel: String? = null
    set(value) {
      field = value
      binding.yearsOfExperienceLabel.text = value
    }

  var cupOfCoffeeLabel: String? = null
    set(value) {
      field = value
      binding.cupOfCoffeeLabel.text = value
    }

  var bugsNumberLabel: String? = null
    set(value) {
      field = value
      binding.bugsNumberLabel.text = value
    }

  var yearsOfExperience: Int? = null
    set(value) {
      field = value
      binding.yearsOfExperienceNumber.text = value?.toString() ?: ""
    }

  var cupOfCoffee: Int? = null
    set(value) {
      field = value
      binding.cupOfCoffeeNumber.text = value?.toString() ?: ""
    }

  var bugsNumber: Int? = null
    set(value) {
      field = value
      binding.bugsNumber.text = value?.toString() ?: ""
    }

  var profileImage: Drawable? = null
    set(value) {
      field = value
      binding.profileImage.setImageDrawable(value)
    }

  var profileNameColor: Int = Color.WHITE
    set(value) {
      field = value
      binding.profileName.setTextColor(value)
    }

  var profilePositionColor: Int = Color.RED
    set(value) {
      field = value
      binding.profilePosition.setTextColor(value)
    }

  var yearsOfExperienceColor: Int = Color.WHITE
    set(value) {
      field = value
      binding.yearsOfExperienceNumber.setTextColor(value)
    }

  var cupOfCoffeeColor: Int = Color.BLACK
    set(value) {
      field = value
      binding.cupOfCoffeeNumber.setTextColor(value)
    }

  var bugsNumberColor: Int = Color.BLACK
    set(value) {
      field = value
      binding.bugsNumber.setTextColor(value)
    }

  var yearsOfExperienceLabelColor: Int = Color.BLACK
    set(value) {
      field = value
      binding.yearsOfExperienceLabel.setTextColor(value)
    }

  var cupOfCoffeeLabelColor: Int = Color.BLACK
    set(value) {
      field = value
      binding.cupOfCoffeeLabel.setTextColor(value)
    }

  var bugsNumberLabelColor: Int = Color.BLACK
    set(value) {
      field = value
      binding.bugsNumberLabel.setTextColor(value)
    }


  init {

    binding.profileImage.setOnClickListener {
      openImagePicker()
    }

    context.theme.obtainStyledAttributes(
      attrs,
      R.styleable.ProfileView,
      0, 0
    ).apply {
      try {
        profileName = getString(R.styleable.ProfileView_profileName)
        profilePosition = getString(R.styleable.ProfileView_profilePosition)
        yearsOfExperienceLabel = getString(R.styleable.ProfileView_yearsOfExperienceLabel)
        cupOfCoffeeLabel = getString(R.styleable.ProfileView_cupOfCoffeeLabel)
        bugsNumberLabel = getString(R.styleable.ProfileView_bugsNumberLabel)
        yearsOfExperience = getInt(R.styleable.ProfileView_yearsOfExperience, 0)
        cupOfCoffee = getInt(R.styleable.ProfileView_cupOfCoffee, 0)
        bugsNumber = getInt(R.styleable.ProfileView_bugsNumber, 0)
        profileImage = getDrawable(R.styleable.ProfileView_profileImage)
        profileNameColor = getColor(R.styleable.ProfileView_profileNameColor, Color.WHITE)
        profilePositionColor = getColor(R.styleable.ProfileView_profilePositionColor, Color.RED)
        yearsOfExperienceColor = getColor(R.styleable.ProfileView_yearsOfExperienceColor, Color.WHITE)
        cupOfCoffeeColor = getColor(R.styleable.ProfileView_cupOfCoffeeColor, Color.WHITE)
        bugsNumberColor = getColor(R.styleable.ProfileView_bugsNumberColor, Color.WHITE)
        yearsOfExperienceLabelColor =  getColor(R.styleable.ProfileView_yearsOfExperienceLabelColor, Color.BLACK)
        cupOfCoffeeLabelColor =  getColor(R.styleable.ProfileView_cupOfCoffeeLabelColor, Color.BLACK)
        bugsNumberLabelColor =  getColor(R.styleable.ProfileView_bugsNumberLabelColor, Color.BLACK)
        cupOfCoffeeLabelColor =  getColor(R.styleable.ProfileView_bugsNumberLabelColor, Color.BLACK)
        yearsOfExperienceLabelColor =  getColor(R.styleable.ProfileView_bugsNumberLabelColor, Color.BLACK)

      } finally {
        recycle()
      }
    }
  }

  private fun openImagePicker() {
    profileName?.let { user ->
      val fragment = ImagePickerFragment()
      fragment.setOnImageSelectedListener { uri ->
        uri?.let {
          val file = ImageUtils.saveImageToInternalStorage(context, it, user)
          if (file != null) {
            binding.profileImage.setImageURI(it)
          }
        }
      }
      fragmentManager.beginTransaction()
        .add(fragment, "ImagePickerFragment")
        .commit()
    }
  }
}