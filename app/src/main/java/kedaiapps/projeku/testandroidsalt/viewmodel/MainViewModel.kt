package kedaiapps.projeku.testandroidsalt.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kedaiapps.projeku.testandroidsalt.BuildConfig
import kedaiapps.projeku.testandroidsalt.common.ActionLiveData
import kedaiapps.projeku.testandroidsalt.common.UiState
import kedaiapps.projeku.testandroidsalt.ext.errorMesssage
import kedaiapps.projeku.testandroidsalt.services.entity.ResponseHome
import kedaiapps.projeku.testandroidsalt.services.rest.MainRest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRest: MainRest,
) : ViewModel() {

    val loadState = ActionLiveData<UiState>()

    val responseHome = ActionLiveData<List<ResponseHome>>()

    fun home() {
        viewModelScope.launch {
            loadState.sendAction(UiState.Loading)
            try {
                val response = mainRest.home("us", BuildConfig.API_KEY)
                responseHome.value = response.articles
                loadState.sendAction(UiState.Success)
            } catch (e: Exception) {
                loadState.sendAction(UiState.Error(e.errorMesssage))
            }
        }
    }
}