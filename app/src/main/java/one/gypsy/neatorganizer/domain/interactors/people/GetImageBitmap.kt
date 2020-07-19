package one.gypsy.neatorganizer.domain.interactors.people

import android.graphics.Bitmap
import android.net.Uri
import one.gypsy.neatorganizer.data.repositories.people.FileRepository
import one.gypsy.neatorganizer.utils.BaseUseCase
import one.gypsy.neatorganizer.utils.Either
import one.gypsy.neatorganizer.utils.Failure

class GetImageBitmap(var fileRepository: FileRepository) :
    BaseUseCase<Bitmap, GetImageBitmap.Params>() {
    override suspend fun run(params: Params): Either<Failure, Bitmap> {
        return try {
            Either.Right(fileRepository.getImageBitmapFromUri(params.imageUri))
        } catch (exp: Exception) {
            Either.Left(
                GetImageBitmapFailure(
                    exp
                )
            )
        }
    }

    data class Params(val imageUri: Uri)
    data class GetImageBitmapFailure(val error: Exception) : Failure.FeatureFailure(error)

}