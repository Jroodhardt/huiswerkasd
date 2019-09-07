package com.asd2.beerdistributiongame.networking.states

interface INetworkState {
    fun enterState()
    fun doState()
    fun exitState()
}