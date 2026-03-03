package com.apero.composetraining.session2.exercises

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apero.composetraining.common.AppTheme

/**
 * ⭐⭐⭐⭐ BÀI TẬP NÂNG CAO BUỔI 2: Chat Bubble Layout
 *
 * Mô tả: Build chat UI với bubble alignment khác nhau cho sent/received messages
 *
 * Received (trái):               Sent (phải):
 * [Avatar] ┌─────────────┐       ┌─────────────┐
 *          │ Tin nhắn    │       │ Tin nhắn    │ [Avatar]
 *          │ nhận được   │       │ đã gửi      │
 *          └─────────────┘       └─────────────┘ ✓✓ 10:30
 *          10:29
 *
 * Yêu cầu:
 * 1. Row với Alignment khác nhau:
 *    - Received: Avatar trái + Bubble + space bên phải
 *    - Sent: space bên trái + Bubble + Avatar phải
 * 2. Bubble shape: RoundedCornerShape bất đối xứng
 *    (sent: bo tất cả trừ góc dưới phải)
 * 3. Timestamp + Read receipt (checkmark) — align cuối row
 * 4. Dùng Modifier.align() per-child trong Box
 * 5. Arrangement.End / Arrangement.Start cho Row sent/received
 *
 * Khái niệm Buổi 2:
 * - Row horizontalArrangement (SpaceBetween → Arrangement.End/Start)
 * - Modifier.align() — per-child alignment trong parent
 * - Asymmetric RoundedCornerShape
 * - weight(1f) + wrapContentWidth()
 */

// ─── Data Model ──────────────────────────────────────────────────────────────

data class ChatMessage(
    val id: String,
    val senderName: String,
    val content: String,
    val time: String,
    val isSent: Boolean,       // true = mình gửi, false = người khác gửi
    val isRead: Boolean = false
)

// ─── Main Composable ─────────────────────────────────────────────────────────

/**
 * Dispatcher — chọn sent vs received bubble
 */
@Composable
fun ChatBubble(
    message: ChatMessage,
    modifier: Modifier = Modifier
) {
    // TODO: Implement ChatBubble dispatcher
    // - Nếu message.isSent = true → gọi SentBubble
    // - Ngược lại → gọi ReceivedBubble
    Box {}
}

/**
 * Received bubble — Avatar trái, bubble phải có max width 75%
 *
 * TODO: [Buổi 2] Implement layout:
 * Row {
 *     SenderAvatar(...)                          // Avatar bên trái
 *     Spacer(8dp)
 *     Column(weight(0.75f)) {
 *         Text senderName (labelSmall, primary)  // Tên người gửi
 *         BubbleContent(isSent = false, ...)     // Bubble
 *         Text time (labelSmall, secondary)      // Thời gian
 *     }
 *     Spacer(weight(0.25f))                      // Đẩy bubble sang trái, max 75% width
 * }
 */
@Composable
fun ReceivedBubble(
    message: ChatMessage,
    modifier: Modifier = Modifier
) {
    // TODO: Implement ReceivedBubble
    // - Row với fillMaxWidth + padding(horizontal=12, vertical=4), verticalAlignment = Bottom
    // - SenderAvatar(senderName, size=32.dp)
    // - Spacer(8.dp)
    // - Column (weight(0.75f)):
    //   → Text senderName (labelSmall, primary, Medium, padding start=4dp)
    //   → BubbleContent(content, isSent = false)
    //   → Text time (labelSmall, onSurfaceVariant, padding start=4dp)
    // - Spacer(weight(0.25f)) ← đẩy bubble không chiếm full width
    // GỢI Ý: Tại sao dùng weight thay vì fillMaxWidth(0.75f)?
    // → weight chia sẻ không gian còn lại sau siblings (Avatar + gaps)
    Box {}
}

/**
 * Sent bubble — space trái, bubble phải, Avatar phải
 *
 * TODO: [Buổi 2] Implement layout:
 * Row(horizontalArrangement = Arrangement.End) {
 *     Spacer(weight(0.25f))                      // Push về bên phải
 *     Column(weight(0.75f), horizontalAlignment = Alignment.End) {
 *         BubbleContent(isSent = true, ...)
 *         Row { Timestamp + ReadReceiptIcon }    // Align cuối
 *     }
 *     Spacer(8dp)
 *     SenderAvatar(...)                          // Avatar bên phải
 * }
 */
@Composable
fun SentBubble(
    message: ChatMessage,
    modifier: Modifier = Modifier
) {
    // TODO: Implement SentBubble
    // - Row với fillMaxWidth + padding(horizontal=12, vertical=4), verticalAlignment = Bottom,
    //   horizontalArrangement = Arrangement.End
    //   GỢI Ý: Arrangement.End + Spacer(weight) làm bubble đẩy về phải
    // - Spacer(weight(0.25f))
    // - Column (weight(0.75f), horizontalAlignment = Alignment.End):
    //   → BubbleContent(content, isSent = true)
    //   → Row (padding end=4dp, spacedBy=4dp): Text time + ReadReceiptIcon(isRead)
    // - Spacer(8.dp)
    // - SenderAvatar("Me", size=32.dp, backgroundColor = primary)
    Box {}
}

/**
 * Bubble content với asymmetric RoundedCornerShape
 *
 * TODO: [Buổi 2] Thay đổi shape theo isSent:
 * - Received: RoundedCornerShape(topStart=2dp, topEnd=12dp, bottomStart=12dp, bottomEnd=12dp)
 * - Sent:     RoundedCornerShape(topStart=12dp, topEnd=12dp, bottomStart=12dp, bottomEnd=2dp)
 *
 * Đây là "tail" của bubble — góc nhỏ chỉ về phía người gửi
 */
@Composable
fun BubbleContent(
    content: String,
    isSent: Boolean,
    modifier: Modifier = Modifier
) {
    // TODO: Implement BubbleContent
    // - Xác định bubbleShape:
    //   → isSent: tất cả 12.dp trừ bottomEnd = 2.dp (tail)
    //   → !isSent: tất cả 12.dp trừ topStart = 2.dp (tail)
    // - Xác định bubbleColor: primary nếu sent, surfaceVariant nếu received
    // - Xác định textColor: onPrimary nếu sent, onSurfaceVariant nếu received
    // - Box với clip(bubbleShape) + background(bubbleColor) + padding(horizontal=12, vertical=8)
    // - Text bên trong với textColor và lineHeight = 20.sp
    Box {}
}

@Composable
fun SenderAvatar(
    name: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary
) {
    // TODO: Implement SenderAvatar
    // - Box với clip(CircleShape) + background(backgroundColor)
    // - Text chữ cái đầu của name (uppercaseChar, White, labelMedium, Bold)
    Box {}
}

@Composable
fun ReadReceiptIcon(
    isRead: Boolean,
    modifier: Modifier = Modifier
) {
    // TODO: Implement ReadReceiptIcon
    // - Icon: DoneAll nếu isRead, Done nếu chưa read — size(14.dp)
    // - Tint: primary nếu isRead, onSurfaceVariant nếu chưa
    Box {}
}

// ─── Sample Data ──────────────────────────────────────────────────────────────

private val sampleMessages = listOf(
    ChatMessage(
        id = "1",
        senderName = "Khắc Minh",
        content = "Bro ơi, cái IntrinsicSize.Max dùng thế nào vậy? Tự nhiên 2 card của tao height khác nhau 😅",
        time = "10:28",
        isSent = false
    ),
    ChatMessage(
        id = "2",
        senderName = "Me",
        content = "Dùng Row(modifier = Modifier.height(IntrinsicSize.Max)) rồi mỗi card thêm .fillMaxHeight() là xong bro",
        time = "10:29",
        isSent = true,
        isRead = true
    ),
    ChatMessage(
        id = "3",
        senderName = "Khắc Minh",
        content = "EZ luôn! Cảm ơn bro. Mà sao phải cần fillMaxHeight() nữa nhỉ?",
        time = "10:30",
        isSent = false
    ),
    ChatMessage(
        id = "4",
        senderName = "Me",
        content = "IntrinsicSize.Max set constraint cho Row. fillMaxHeight() bảo card 'hãy chiếm hết height của Row đó'. Thiếu 1 trong 2 thì không work đâu 😄",
        time = "10:30",
        isSent = true,
        isRead = false
    )
)

// ─── Preview ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, name = "Chat — Light Mode")
@Composable
private fun ChatBubbleLightPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            sampleMessages.forEach { message ->
                ChatBubble(message = message)
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "Chat — Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ChatBubbleDarkPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            sampleMessages.forEach { message ->
                ChatBubble(message = message)
            }
        }
    }
}

// ─── Câu Hỏi Thảo Luận ───────────────────────────────────────────────────────
/*
 * Q1: Tại sao dùng weight(0.75f) thay vì fillMaxWidth(0.75f)?
 * Q2: Asymmetric RoundedCornerShape — tại sao "tail" chỉ 2dp?
 * Q3: Arrangement.End trong SentBubble Row — có thể bỏ không?
 * Q4: Modifier.align() per-child vs horizontalAlignment của Column?
 */
