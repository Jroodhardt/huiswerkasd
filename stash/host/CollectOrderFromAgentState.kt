package com.asd2.beerdistributiongame.networking.states.host

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object CollectOrderFromAgentState : INetworkState {

    override fun enterState() {
    }

    override fun doState() {
        when{
            allOrdersCollected() -> NetworkStateMachine.switchTo(CheckPlayerJoinedState)
        }
    }

    override fun exitState() {
    }

    private fun allOrdersCollected(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }
}
