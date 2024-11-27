package com.jihan.whatsapp.data.models

import java.util.Date

data class Message(
    val message:String?=null,
    val uid:String?=null,
    val timeStamp:Long = Date().time
)


