package com.asd2.beerdistributiongame.networking.states.client

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object ConnectState : INetworkState {

    override fun enterState() {
        checkConnectionInfo()
        connect()
    }

    override fun doState() {
        checkIfConnected()
        when {
            connected() -> NetworkStateMachine.switchTo(GameStatusState)
            !connected()||connectionInfoValid() -> NetworkStateMachine.switchTo(IdleState)
        }
    }

    override fun exitState() {
    }
    private fun checkConnectionInfo() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun connectionInfoValid(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun checkIfConnected() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun connected(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun connect() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

}