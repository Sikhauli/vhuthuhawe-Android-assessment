package com.glucode.about_you.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import androidx.core.content.ContextCompat
import com.glucode.about_you.R

class ImageUtils {

  companion object {

    fun saveImageToInternalStorage(context: Context, imageUri: Uri, userName: String): File? {
      return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri)
        val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)

        val file = File(context.filesDir, "${userName}_profile_image.jpg")
        val outputStream = FileOutputStream(file)

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()

        file
      } catch (e: Exception) {
        e.printStackTrace()
        null
      }
    }
  }
}

fun loadImageFromInternalStorage(context: Context, userName: String): Drawable? {

  val fileName = "${userName}_profile_image.jpg"
  val file = File(context.filesDir, fileName)
  return if (file.exists()) {
    val bitmap: Bitmap = BitmapFactory.decodeFile(file.absolutePath)
    BitmapDrawable(context.resources, bitmap)
  } else {
    ContextCompat.getDrawable(context, R.drawable.ic_person)
  }
}
