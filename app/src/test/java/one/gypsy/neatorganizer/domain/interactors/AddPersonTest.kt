package one.gypsy.neatorganizer.domain.interactors

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import one.gypsy.neatorganizer.TestCoroutineRule
import one.gypsy.neatorganizer.data.repositories.PeopleRepository
import one.gypsy.neatorganizer.domain.dto.Person
import one.gypsy.neatorganizer.mock
import one.gypsy.neatorganizer.whenever
import org.junit.After
import org.junit.Rule
import org.junit.Test
import java.util.*

class AddPersonTest {

    @Rule
    @JvmField
    val syncExecuteRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule = TestCoroutineRule()

    val testScope = TestCoroutineScope()


    private val peopleRepository: PeopleRepository = mock<PeopleRepository>()
    private var person: Person = mock<Person>()
    private val personParams: AddPerson.Params = mock<AddPerson.Params>()

    val addPersonUseCase by lazy { AddPerson(peopleRepository) }

    @After
    fun cleanUp() {
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun testAddPersonUseCaseCompletion() {
        testScope.runBlockingTest {
            whenever(peopleRepository.addPerson(person)).thenReturn(Unit)
        }
        addPersonUseCase(testScope, personParams) {
            assert(it.isRight)
        }
    }

//    @Test
//    fun testAddPersonUseCaseResult() {
//        testScope.runBlockingTest {
//            peopleRepository.getAllPeople().observeForever {
//                it.first()
//            }
////            whenever(peopleRepository.getAllPeople()).then()
//            whenever(peopleRepository.addPerson(person))
//        }
//
////        val expectedPerson = Person(0, "", Person.Sex.MALE, null, 0, Date())
////
////
////        addPersonUseCase(testScope, personParams) {
////            assert(it.isRight)
////        }
//    }
}
