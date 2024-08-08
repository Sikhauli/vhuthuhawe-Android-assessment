package com.glucode.about_you.about

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class ImagePickerFragment : Fragment() {
  private var onImageSelectedListener: ((Uri?) -> Unit)? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
      onImageSelectedListener?.invoke(uri)
    }.launch("image/*")
  }

  fun setOnImageSelectedListener(listener: (Uri?) -> Unit) {
    onImageSelectedListener = listener
  }
}
