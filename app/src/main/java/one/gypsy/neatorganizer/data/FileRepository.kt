package one.gypsy.neatorganizer.data

import android.graphics.Bitmap
import android.net.Uri
import javax.inject.Inject

class FileRepository @Inject constructor(var fileDataSource: FileDataSource){
    suspend fun getImageBitmapFromUri(imageUri: Uri): Bitmap = fileDataSource.getImageBitmapFromUri(imageUri)
}