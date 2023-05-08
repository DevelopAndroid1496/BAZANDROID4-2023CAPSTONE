package com.example.themovieapp.domain

import com.example.remote.di.model.latest.LatestMovieResponse
import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetMovieLatestUseCaseTest{

    @RelaxedMockK
    private lateinit var useCaseFromRx: GetAllMoviesFromRxUseCase
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var latesUseCase: GetMovieLatestUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        latesUseCase = GetMovieLatestUseCase(useCaseFromRx,testDispatcher)
    }

    @Test
    fun `when api return data then state success`(): Unit = runBlocking {

        //Given
        coEvery { useCaseFromRx.invoke() } returns null

                //When
                latesUseCase().collect{ state ->
                    when(state){
                        is DataState.Success -> {
                            //Then
                            Assert.assertNull(state.data)
                        }
                        else -> {}
                    }
                }


    }

}