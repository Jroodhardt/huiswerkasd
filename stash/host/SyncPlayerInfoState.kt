package com.asd2.beerdistributiongame.networking.states.host

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object SyncPlayerInfoState : INetworkState {

    override fun enterState() {
        getGameAgents()
        updateHostList()
    }

    override fun doState() {
        checkPlayerInfoSynced()
        when{
            playerInfoSynced() -> NetworkStateMachine.switchTo(SyncGamestateState)
        }
    }

    override fun exitState() {
    }

    private fun getGameAgents() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun updateHostList() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun checkPlayerInfoSynced() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun playerInfoSynced(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

}