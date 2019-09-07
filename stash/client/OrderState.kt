package com.asd2.beerdistributiongame.networking.states.client

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object OrderState : INetworkState {

    override fun enterState() {
        getGameState()
        checkAmountOfRoundsLeft()
    }

    override fun doState() {
    }

    override fun exitState() {
        sendOrder()
        when {
            orderSent() -> NetworkStateMachine.switchTo(WaitForGamestateState)
            GameAgentEnabled() -> NetworkStateMachine.switchTo(GameAgentState)
            noRoundsLeft() -> NetworkStateMachine.switchTo(IdleState)
        }
    }

    private fun noRoundsLeft(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun GameAgentEnabled(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")

    }

    private fun orderSent(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")

    }

    private fun getGameState() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun checkAmountOfRoundsLeft() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun sendOrder() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }
}