package com.lts.codetest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lts.codetest.data.Music
import com.lts.codetest.repository.MusicRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * <pre>
 *     author : liutangsheng
 *     time   : 2024/02/04
 * </pre>
 */
class MainViewModel : ViewModel() {

    val music = MutableLiveData<List<Music>>()
    val errorMessage = MutableLiveData<String>()

    fun getMusic() {
        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            errorMessage.postValue(throwable.message)
        }
        viewModelScope.launch(handler) {
            val response = MusicRepository.getMusics()
            music.postValue(response.results.sortedBy { it.releaseDate })
        }
    }

}