package com.example.breakingbad.characters_feature.peresentation.activity.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.core.util.Constant.Companion.SPLASH_SCREEN_TIME_OUT
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _event = Channel<SplashScreenEvent>()
    val event = _event.receiveAsFlow()


    init {
        triggerNavigateEvent()
    }

    private fun triggerNavigateEvent() = viewModelScope.launch {
        delay(SPLASH_SCREEN_TIME_OUT)
        _event.send(SplashScreenEvent.Navigate)
    }

    sealed class SplashScreenEvent {
        object Navigate : SplashScreenEvent()
    }
}
