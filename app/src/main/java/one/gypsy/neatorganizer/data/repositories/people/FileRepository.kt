package one.gypsy.neatorganizer.data.repositories.people

import android.graphics.Bitmap
import android.net.Uri
import one.gypsy.neatorganizer.domain.datasource.people.FileDataSource

class FileRepository(var fileDataSource: FileDataSource) {
    suspend fun getImageBitmapFromUri(imageUri: Uri): Bitmap =
        fileDataSource.getImageBitmapFromUri(imageUri)
}