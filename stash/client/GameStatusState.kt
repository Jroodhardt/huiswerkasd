package com.asd2.beerdistributiongame.networking.states.client

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object GameStatusState : INetworkState {

    override fun enterState() {
        checkGameStarted()
        checkPlayerParticipation()
    }

    override fun doState() {
        when{
            playerParticipated() -> NetworkStateMachine.switchTo(OrderState)
            !playerParticipated()||!gameStarted() -> NetworkStateMachine.switchTo(CheckAvailableRolesState)
        }
    }

    override fun exitState() {
    }

    private fun playerParticipated(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun gameStarted(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun checkGameStarted() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun checkPlayerParticipation() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }
}