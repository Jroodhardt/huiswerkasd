package com.asd2.beerdistributiongame.networking.states.client

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object IdleState : INetworkState {

    override fun enterState() {
    }

    override fun doState() {
        when{
        connect() -> NetworkStateMachine.switchTo(ConnectState)
        }
    }

    override fun exitState() {
    }

    private fun connect(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

}