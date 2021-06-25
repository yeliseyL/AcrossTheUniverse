package com.eliseylobanov.acrosstheuniverse.ui.pictureofday

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eliseylobanov.acrosstheuniverse.TestCoroutineRule
import com.eliseylobanov.acrosstheuniverse.entities.PictureOfDay
import com.eliseylobanov.acrosstheuniverse.repository.FakePictureRepository
import com.nhaarman.mockito_kotlin.atLeastOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
@ExperimentalCoroutinesApi
class PictureOfDayViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private lateinit var pictureOfDayViewModel: PictureOfDayViewModel

    @Mock
    private lateinit var repository: FakePictureRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        pictureOfDayViewModel = PictureOfDayViewModel(repository)
    }

    @Test
    fun repository_FunctionCalled() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(repository.getTodayPictureOfTheDay()).thenReturn(
                PictureOfDay("image", "Mars", "url", "planet")
            )

            pictureOfDayViewModel.getTodayPictureOfDay()
            Mockito.verify(repository, atLeastOnce()).getTodayPictureOfTheDay()
        }
    }

    @Test
    fun liveData_TestReturnValueIsNotNull() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<PictureOfDay> {}
            val liveData = pictureOfDayViewModel.pictureOfDay

            Mockito.`when`(repository.getTodayPictureOfTheDay()).thenReturn(
                PictureOfDay("image", "Mars", "url", "planet")
            )

            try {
                liveData.observeForever(observer)
                pictureOfDayViewModel.getTodayPictureOfDay()
                assertNotNull(liveData.value)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

    @Test
    fun liveData_TestReturnValueIsPictureOfDay() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<PictureOfDay> {}
            val liveData = pictureOfDayViewModel.pictureOfDay

            Mockito.`when`(repository.getTodayPictureOfTheDay()).thenReturn(
                PictureOfDay("image", "Mars", "url", "planet")
            )

            try {
                liveData.observeForever(observer)
                pictureOfDayViewModel.getTodayPictureOfDay()
                assertTrue(liveData.value is PictureOfDay)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }
}