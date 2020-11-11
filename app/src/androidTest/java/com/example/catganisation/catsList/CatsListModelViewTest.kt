package com.example.catganisation.catsList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import com.example.catganisation.model.Breed
import com.example.catganisation.repository.CatRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import java.io.InputStreamReader


@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class CatsListModelViewTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var breedObserver: Observer<List<Breed>>

    @Mock
    private lateinit var repository: CatRepository

    private lateinit var modelView: CatsListModelView

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        modelView = CatsListModelView(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun getAllBreed_return_SuccessfulResponse() = runBlocking {
        modelView.catBreeds.observeForever(breedObserver)

        // given
        val response = MockResponseFileReader("responseSuccessful.json")
        val sType = object : TypeToken<List<Breed>>() {}.type
        val list = Gson().fromJson<List<Breed>>(response.content, sType)

        Mockito.`when`(repository.getBreeds()).thenReturn(list)
        //when
        launch(Dispatchers.Main) {
            modelView.getBreeds()
        }

        //then
       // Assert.assertEquals(list, modelView.catBreeds.value)
        verify(breedObserver, times(0)).onChanged(any())
    }
}

class MockResponseFileReader(path: String) {
    val content: String

    init {
        val reader = InputStreamReader(this.javaClass.classLoader.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }
}