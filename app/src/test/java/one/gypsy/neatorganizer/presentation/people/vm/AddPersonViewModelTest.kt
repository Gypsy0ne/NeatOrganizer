package one.gypsy.neatorganizer.presentation.people.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.polyak.iconswitch.IconSwitch
import one.gypsy.neatorganizer.InstantExecutorExtension
import one.gypsy.neatorganizer.TestCoroutineRule
import one.gypsy.neatorganizer.domain.dto.people.Person
import one.gypsy.neatorganizer.domain.interactors.people.AddPerson
import one.gypsy.neatorganizer.domain.interactors.people.GetImageBitmap
import one.gypsy.neatorganizer.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.reset
import java.util.*


@ExtendWith(InstantExecutorExtension::class)
internal class AddPersonViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    val addPersonUseCase = mock<AddPerson>()
    val getBitmapUseCase = mock<GetImageBitmap>()

    lateinit var viewModel: AddPersonViewModel


    @Before
    fun initTest() {
        reset(addPersonUseCase, getBitmapUseCase)
        viewModel = AddPersonViewModel(addPersonUseCase, getBitmapUseCase)
    }


    @Test
    fun testOnBirthDateChanged() {
        val expectedDate = Date()
        viewModel.onBirthDateChanged(expectedDate)
        viewModel.birthDate.observeForever {
            assertEquals(expectedDate.time, it.time)
        }
    }

    @Test
    fun testOnSexMaleChosen() {
        val expectedSexChoice = IconSwitch.Checked.LEFT
        viewModel.onSexChanged(expectedSexChoice)
        viewModel.sex.observeForever {
            assertEquals(Person.Sex.MALE, it)
        }
    }

    @Test
    fun testOnSexFemaleChosen() {
        val expectedSexChoice = IconSwitch.Checked.RIGHT
        viewModel.onSexChanged(expectedSexChoice)
        viewModel.sex.observeForever {
            assertEquals(Person.Sex.FEMALE, it)
        }
    }

}