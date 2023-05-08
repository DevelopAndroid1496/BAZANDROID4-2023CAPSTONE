package com.example.themovieapp.domain

import android.content.Context
import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.repository.MovieRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class GetMoviesNowUseCaseTest{

    @RelaxedMockK
    private lateinit var repository: MovieRepository
    private val testDispatcher = TestCoroutineDispatcher()
    //private val mockContext: Context = androidx.test.core.app.ApplicationProvider.getApplicationContext<Context>()

    private lateinit var nowUseCase: GetMoviesNowUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        nowUseCase = GetMoviesNowUseCase(repository,testDispatcher)
    }


    @Test
    fun `when api return data then state success`(): Unit = runBlocking {

        //Given
        coEvery { repository.getNowMovies().collect() } returns

                //When
                nowUseCase().collect{ state ->
                    when(state){
                        is DataState.Success -> {
                            //Then
                            Assert.assertTrue(state.data.isNotEmpty())
                        }
                        else -> {}
                    }
                }


    }

}