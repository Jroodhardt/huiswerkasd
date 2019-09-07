package com.asd2.beerdistributiongame.networking.states.host

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object GameState : INetworkState {

    override fun enterState() {
        startTimer()
    }

    override fun doState() {
        receiveOrders()
        when{
            timerHasEnded()&&allOrdersCollected() -> NetworkStateMachine.switchTo(CheckPlayerJoinedState)
            timerHasEnded()&&notAllOrdersCollected() -> NetworkStateMachine.switchTo(CollectOrderFromAgentState)
        }
    }

    override fun exitState() {
    }

    private fun notAllOrdersCollected(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun timerHasEnded(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun allOrdersCollected(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun startTimer() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun receiveOrders() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun checkAllOrdersReceived() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

}