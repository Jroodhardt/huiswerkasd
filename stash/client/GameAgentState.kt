package com.asd2.beerdistributiongame.networking.states.client

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object GameAgentState : INetworkState {

    override fun enterState() {
        toggleGameAgent()
    }

    override fun doState() {
        when {
            !gameAgentEnabled() -> NetworkStateMachine.switchTo(OrderState)
        }
    }

    override fun exitState() {
    }

    private fun gameAgentEnabled(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun toggleGameAgent() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

}