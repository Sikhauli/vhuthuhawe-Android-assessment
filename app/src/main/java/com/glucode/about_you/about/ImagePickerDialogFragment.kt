package com.glucode.about_you.about

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.android.volley.toolbox.ImageLoader

class ImagePickerDialogFragment : DialogFragment() {

  private var onImageSelectedListener: ((Uri?) -> Unit)? = null
  private var userName: String? = null
  private lateinit var imageLoader: ImageLoader

  companion object {
    private const val REQUEST_IMAGE_PICKER = 1

    fun show(fragmentManager: FragmentManager, userName: String, imageLoader: ImageLoader) {
      val dialog = ImagePickerDialogFragment().apply {
        this.userName = userName
        this.imageLoader = imageLoader
      }
      dialog.show(fragmentManager, "ImagePickerDialogFragment")
    }
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return super.onCreateDialog(savedInstanceState).apply {
      // Setup the dialog here if needed
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_IMAGE_PICKER && resultCode == -1) {
      val uri: Uri? = data?.data
      onImageSelectedListener?.invoke(uri)
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    // Handle the attachment logic if needed
  }

  fun setOnImageSelectedListener(listener: (Uri?) -> Unit) {
    onImageSelectedListener = listener
  }

  private fun openImagePicker() {
    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    startActivityForResult(intent, REQUEST_IMAGE_PICKER)
  }
}
