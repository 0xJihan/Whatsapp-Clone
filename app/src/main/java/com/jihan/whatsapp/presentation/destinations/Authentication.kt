package com.jihan.whatsapp.presentation.destinations

import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination {
    @Serializable
    data object Login : Destination
    @Serializable
    data object Signup : Destination

    @Serializable
    data object Home : Destination

    @Serializable
    data class ChatDetail(
        val senderId: String,
        val receiverId:String,
        val receiverName:String,
        val receiverImage:String?=null
    ) : Destination


}