import com.shopping_list.common.models.MessageId
import com.shopping_list.common.models.UserId
import java.io.Serializable

data class MessageIdRow(
    val userId: UserId,
    val messageId: MessageId
) : Serializable {
    fun toInternal(): MessageId = messageId
}
