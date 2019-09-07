package com.asd2.beerdistributiongame.networking.states.host

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object SyncLobbyState : INetworkState {

    override fun enterState() {
    }

    override fun doState() {
        syncLobbyStates()
        when{
            statesSynchronized() -> NetworkStateMachine.switchTo(LobbyState)
        }
    }

    override fun exitState() {
    }

    private fun statesSynchronized(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun syncLobbyStates() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

}