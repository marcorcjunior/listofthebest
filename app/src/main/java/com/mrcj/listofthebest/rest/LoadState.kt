package com.mrcj.listofthebest.rest

sealed class LoadState {
    object Loading: LoadState()
    object Done: LoadState()
}