package com.asd2.beerdistributiongame.networking.states.host

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object LobbyState : INetworkState {

    override fun enterState() {
    }

    override fun doState() {
        allowChooseRole()
        when {
            startGame() -> NetworkStateMachine.switchTo(SyncGamestateState)
            lobbyChangesMade() -> NetworkStateMachine.switchTo(SyncLobbyState)
        }
    }

    override fun exitState() {
    }

    private fun lobbyChangesMade(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun allowChooseRole() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun startGame(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

}