package com.shopping_list.common.models

import com.shopping_list.common.IResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
@JvmInline
value class MessageId(private val id: String) : IResult {
    constructor(id: Int) : this(id.toString())

    fun toInt() = id.toInt()

    companion object {
        val NONE = MessageId("")
    }
}

object MessageIdSerializer : KSerializer<MessageId> {
    override fun deserialize(decoder: Decoder): MessageId {
        return MessageId(decoder.decodeInline(descriptor).decodeInt())
    }

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("MessageId", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: MessageId) {
        encoder.encodeInline(descriptor).encodeInt(value.toInt())
    }
}