package com.asd2.beerdistributiongame.networking.states.host

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object SyncGamestateState : INetworkState {

    override fun enterState() {
        syncGameStates()
    }

    override fun doState() {
        checkGamestatesSynced()
        when{
            !noRoundsLeft()&&statesSynced() -> NetworkStateMachine.switchTo(GameState)
            noRoundsLeft()&&statesSynced() -> NetworkStateMachine.switchTo(IdleState)
        }
    }

    override fun exitState() {
    }

    private fun noRoundsLeft(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun statesSynced(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun syncGameStates() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun checkGamestatesSynced() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

}