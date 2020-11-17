package one.gypsy.neatorganizer.domain.datasource.people

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeviceFileDataSource(private val context: Context) : FileDataSource {

    override suspend fun getImageBitmapFromUri(imageUri: Uri): Bitmap =
        withContext(Dispatchers.IO) {
            val stream = context.contentResolver.openInputStream(imageUri)
            BitmapFactory.decodeStream(stream)
        }
}