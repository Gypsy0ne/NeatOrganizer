package one.gypsy.neatorganizer.data

import android.graphics.Bitmap
import android.net.Uri
import javax.inject.Inject

interface FileDataSource  {
    suspend fun getImageBitmapFromUri(imageUri: Uri): Bitmap
}