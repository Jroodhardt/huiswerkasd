package com.asd2.beerdistributiongame.networking.states.client

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object ChooseRoleState : INetworkState {

    override fun enterState() {
    }

    override fun doState() {
        selectRole()
        checkLobbyStateChanged()
        when {
            lobbyStateChangesMade() -> NetworkStateMachine.switchTo(CheckAvailableRolesState)
            gameStarted() -> NetworkStateMachine.switchTo(OrderState)
        }
    }


    override fun exitState() {
    }

    private fun checkLobbyStateChanged() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun lobbyStateChangesMade(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun selectRole() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun gameStarted(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

}