package com.asd2.beerdistributiongame.networking.states.host

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object CheckPlayerJoinedState : INetworkState {

    override fun enterState() {
    }

    override fun doState() {
        checkPlayerJoined()
        when{
            playersJoined() -> NetworkStateMachine.switchTo(SyncPlayerInfoState)
            !playersJoined() -> NetworkStateMachine.switchTo(SyncGamestateState)
        }
    }

    override fun exitState() {

    }

    private fun playersJoined(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun checkPlayerJoined() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

}