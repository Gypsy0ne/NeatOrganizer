package one.gypsy.neatorganizer.data.repositories

import android.graphics.Bitmap
import android.net.Uri
import one.gypsy.neatorganizer.domain.datasource.FileDataSource
import javax.inject.Inject

class FileRepository @Inject constructor(var fileDataSource: FileDataSource){
    suspend fun getImageBitmapFromUri(imageUri: Uri): Bitmap = fileDataSource.getImageBitmapFromUri(imageUri)
}