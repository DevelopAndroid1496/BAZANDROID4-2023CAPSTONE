package com.example.themovieapp.domain

import com.example.themovieapp.common.DataState
import com.example.themovieapp.common.di.app.IoDispatcher
import com.example.themovieapp.data.model.genders.GenderMovie

import com.example.themovieapp.data.repository.MovieRepository
import com.example.themovieapp.domain.model.GenderDom
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllGenderesMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(): Flow<DataState<List<GenderDom>>> = flow {
        repository.getMoviesGenders()
            .catch { e -> e.printStackTrace() }
            .collect {
               emit(it)
            }
    }.flowOn(ioDispatcher)


    suspend fun getGendersFromDB(id: Int): Flow<DataState<GenderDom>>{
        return repository.getGendersById(id)
    }

}