package nl.avtolz

import kotlinx.serialization.Serializable

@Serializable
data class BoolArg(val arg: Boolean)

@Serializable
data class IntArg(val arg: Int)

@Serializable
data class DoubleArg(val arg: Double)

@Serializable
data class StringArg(val arg: String)