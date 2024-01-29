package com.example.earthquake

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.earthquake.util.InstantExecutorExtension
import com.example.earthquake.util.MainCoroutineRule
import com.example.earthquake.util.TestModelsGenerator
import com.example.earthquake.viewmodel.EarthquakeResult
import com.example.earthquake.viewmodel.EarthquakeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class EarthquakeViewModelTest {
    private lateinit var earthquakeViewModel: EarthquakeViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()


    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        earthquakeViewModel = spyk(EarthquakeViewModel())
    }

    @Test
    fun `get earthquake successful`() = runBlocking {

        val earthquakes = testModelsGenerator.generateEarthquakes()
        coEvery { earthquakeViewModel.earthquakeService.getEarthquakes() }.returns(earthquakes)
        earthquakeViewModel.getEarthquakes()
        earthquakeViewModel.earthquake.observeForever { }

        assertEquals(earthquakeViewModel.failed.value, false)
        assertTrue(earthquakeViewModel.earthquake.value is EarthquakeResult)
        assertEquals(earthquakes, earthquakeViewModel.earthquake.value)
    }

    @Test
    fun `get earthquake failed`() = runBlocking {

        coEvery { earthquakeViewModel.earthquakeService.getEarthquakes() }.returns(null)

        earthquakeViewModel.getEarthquakes()
        earthquakeViewModel.earthquake.observeForever { }

        assertEquals(earthquakeViewModel.failed.value, true)
    }

}
