package com.asd2.beerdistributiongame.networking.states.client

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object WaitForGamestateState : INetworkState {

    override fun enterState() {
        sendOrder()
    }

    override fun doState() {
        when{
        secondTimerHasEnded() -> checkIfHostIsAlive()
        gameStateUpdated() -> NetworkStateMachine.switchTo(OrderState)
        }
    }

    override fun exitState() {
    }

    private fun secondTimerHasEnded(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")

    }

    private fun gameStateUpdated(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun checkIfHostIsAlive() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun sendOrder() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

}