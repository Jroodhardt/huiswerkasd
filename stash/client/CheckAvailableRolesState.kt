package com.asd2.beerdistributiongame.networking.states.client

import com.asd2.beerdistributiongame.networking.NetworkStateMachine
import com.asd2.beerdistributiongame.networking.states.INetworkState

object CheckAvailableRolesState : INetworkState {

    override fun enterState() {
        getAmountFreeRoles()
    }

    override fun doState() {
        when{
            noFreeRoles() -> NetworkStateMachine.switchTo(IdleState)
            !noFreeRoles() -> NetworkStateMachine.switchTo(ChooseRoleState)
        }
    }

    override fun exitState() {
    }

    private fun getAmountFreeRoles() {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

    private fun noFreeRoles(): Boolean {
        TODO("This function needs to be implemented during the development tasks of the states")
    }

}