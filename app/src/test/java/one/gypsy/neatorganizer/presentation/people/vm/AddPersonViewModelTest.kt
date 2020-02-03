package one.gypsy.neatorganizer.presentation.people.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import one.gypsy.neatorganizer.InstantExecutorExtension
import one.gypsy.neatorganizer.TestCoroutineRule
import one.gypsy.neatorganizer.domain.interactors.AddPerson
import one.gypsy.neatorganizer.domain.interactors.GetImageBitmap
import one.gypsy.neatorganizer.mock
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.reset

@ExtendWith(InstantExecutorExtension::class)
internal class AddPersonViewModelTest {


    val addPersonUseCase = mock<AddPerson>()
    val getBitmapUseCase = mock<GetImageBitmap>()

    val viewModel by lazy {
        AddPersonViewModel(addPersonUseCase, getBitmapUseCase)
    }

    val selectThumbnailActionRequestCode = (viewModel.javaClass.getDeclaredField("selectThumbnailPhotoActionRequestCode")).let {
        it.isAccessible = true
        it.get(viewModel) as Int
    }

    @Before
    fun initTest() {
        reset(addPersonUseCase, getBitmapUseCase)
    }

    @Test
    fun testStartSelectThumbnailPhotoAction() {
        viewModel.selectThumbnailPhoto.observeForever {
            assertEquals(selectThumbnailActionRequestCode, it)
        }
        viewModel.startSelectThumbnailPhotoAction()
    }
}