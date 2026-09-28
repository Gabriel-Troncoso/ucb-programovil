package org.ucb.appp1.catalog.screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.ucb.appp1.catalog.domain.repository.CatalogRepository

class CatalogViewModel(
    private val repository: CatalogRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CatalogState())
    val state: StateFlow<CatalogState> = _state.asStateFlow()

    private val _effect = Channel<CatalogEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    init {
        onEvent(CatalogEvent.LoadCatalog)
    }

    fun onEvent(event: CatalogEvent) {
        when (event) {
            CatalogEvent.LoadCatalog,
            CatalogEvent.Retry -> loadFirstPage()
            CatalogEvent.LoadNextPage -> loadNextPage()
        }
    }

    private fun loadFirstPage() {
        if (_state.value.isLoading && _state.value.people.isNotEmpty()) return

        viewModelScope.launch {
            _state.value = CatalogState(isLoading = true)

            repository.getPeople(page = 1)
                .onSuccess { page ->
                    _state.value = CatalogState(
                        people = page.people,
                        isLoading = false,
                        nextPage = page.nextPage,
                        totalCount = page.totalCount
                    )
                }
                .onFailure { error -> showInitialError(error) }
        }
    }

    private fun loadNextPage() {
        val current = _state.value
        val pageNumber = current.nextPage ?: return
        if (current.isLoading || current.isLoadingMore) return

        viewModelScope.launch {
            _state.value = current.copy(isLoadingMore = true)

            repository.getPeople(pageNumber)
                .onSuccess { page ->
                    _state.value = _state.value.copy(
                        people = (_state.value.people + page.people).distinctBy { it.id },
                        isLoadingMore = false,
                        nextPage = page.nextPage,
                        totalCount = page.totalCount
                    )
                }
                .onFailure { error ->
                    val message = error.message ?: "No se pudo cargar la siguiente página"
                    _state.value = _state.value.copy(isLoadingMore = false)
                    _effect.send(CatalogEffect.ShowMessage(message))
                }
        }
    }

    private suspend fun showInitialError(error: Throwable) {
        val message = error.message ?: "No se pudo cargar el catálogo"
        _state.value = CatalogState(isLoading = false, errorMessage = message)
        _effect.send(CatalogEffect.ShowMessage(message))
    }
}
