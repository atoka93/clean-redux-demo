package net.attilaszabo.reduxdemo.state

import java.io.Serializable

data class Repository(
        val id: Int,
        val name: String? = null
) : Serializable
