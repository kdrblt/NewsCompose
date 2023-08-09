package com.example.newscompose.ui.internetconnection

sealed class ConnectionState {
    object Available : ConnectionState()
    object UnAvailable : ConnectionState()
}
