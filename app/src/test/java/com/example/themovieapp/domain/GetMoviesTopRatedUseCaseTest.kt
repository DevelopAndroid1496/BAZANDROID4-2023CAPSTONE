package com.example.themovieapp.domain

import androidx.compose.foundation.isSystemInDarkTheme
import com.example.themovieapp.common.DataState
import com.example.themovieapp.data.repository.MovieRepository
import com.example.themovieapp.domain.model.Movie
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class GetMoviesTopRatedUseCaseTest{

    @RelaxedMockK
    private lateinit var repository: MovieRepository
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var topRatedUseCase: GetMoviesTopRatedUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        topRatedUseCase = GetMoviesTopRatedUseCase(repository,testDispatcher)
    }

    @Test
    fun `when api return data then state success`(): Unit = runBlocking {

        //Given
        coEvery { repository.getTopRatedMovies().collect() } returns

        //When
        topRatedUseCase().collect{ state ->
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