package com.example.themovieapp.domain

import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class GetAllGenderesMoviesUseCaseTest{

    @RelaxedMockK
    private lateinit var repository: MovieRepository
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var gendersUseCase: GetAllGenderesMoviesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        gendersUseCase = GetAllGenderesMoviesUseCase(repository,testDispatcher)
    }


    @Test
    fun `when api return data then state success`(): Unit = runBlocking {

        //Given
        coEvery { repository.getNowMovies().collect() } returns

                //When
                gendersUseCase().collect{ state ->
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