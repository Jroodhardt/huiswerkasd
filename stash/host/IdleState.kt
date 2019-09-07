package com.asd2.beerdistributiongame.networking.states.host

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object IdleState : INetworkState {

    override fun enterState() {
    }

    override fun doState() {
        when {
            createGame() -> NetworkStateMachine.switchTo(LobbyState)
        }
    }

    override fun exitState() {
    }

    private fun createGame(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

}