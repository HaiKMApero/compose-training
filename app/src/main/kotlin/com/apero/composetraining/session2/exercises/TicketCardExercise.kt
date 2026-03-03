package com.apero.composetraining.session2.exercises

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.QrCode2
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apero.composetraining.common.AppTheme

/**
 * ⭐⭐⭐ BÀI TẬP NÂNG CAO BUỔI 2: Event Ticket Card
 *
 * Mô tả: Build ticket card có divider đặc biệt — 2 nửa hình tròn "cắt" vào 2 bên
 *
 * ┌─────────────────────────────────────────┐
 * │  🎵  TECH SUMMIT 2026                   │
 * │      Android & Compose Workshop         │
 * │                                         │
 * │  📍 Hà Nội Convention Center           │
 * │  Sat, Mar 7 · 09:00 AM                 │
 * ├──── ◐ ─────────────────── ◑ ────────────┤  ← Notched divider
 * │  ADMIT ONE       │  QR CODE             │
 * │  Row A - Seat 12 │  [     ]             │
 * │  VIP Section     │  [  📱 ]             │
 * │                  │  [     ]             │
 * └─────────────────────────────────────────┘
 *
 * Yêu cầu kỹ thuật:
 * 1. Dashed divider bằng Canvas + PathEffect.dashPathEffect
 * 2. Notched circles ở 2 bên bằng Box + CircleShape (Modifier.offset)
 * 3. Bottom section: Row với Divider dọc (VerticalDivider) giữa 2 cột
 * 4. Modifier chain: clip, background, shadow — ORDER MATTERS
 * 5. Tất cả text dùng MaterialTheme.typography
 *
 * Khái niệm Buổi 2:
 * - Box: notch circles xếp chồng lên divider
 * - Row/Column: layout 2 section
 * - Modifier order: clip → background (không phải ngược lại)
 * - Alignment: Alignment.CenterStart, Alignment.CenterEnd cho notches
 */

// ─── Main Component ──────────────────────────────────────────────────────────

@Composable
fun EventTicketCard(
    eventName: String,
    eventSubtitle: String,
    location: String,
    dateTime: String,
    seatInfo: String,
    section: String,
    modifier: Modifier = Modifier
) {
    // TODO: Implement EventTicketCard
    // - Card với fillMaxWidth, padding(16.dp), RoundedCornerShape(16.dp), elevation(8.dp)
    // - Column bên trong
    // - Gọi TicketTopSection(eventName, eventSubtitle, location, dateTime)
    // - Gọi NotchedDivider()
    // - Gọi TicketBottomSection(seatInfo, section)
    // GỢI Ý: Tại sao clip phải TRƯỚC khi set background Card?
    // → clip xác định vùng vẽ, background fill vùng đó → clip trước để corners bo đúng
    Box {}
}

@Composable
fun TicketTopSection(
    eventName: String,
    eventSubtitle: String,
    location: String,
    dateTime: String,
    modifier: Modifier = Modifier
) {
    // TODO: Implement TicketTopSection
    // - Column với fillMaxWidth + background(primary) + padding(20.dp)
    // - Row header: Text "🎵" (28.sp) + Column(eventName bold + eventSubtitle onPrimary.alpha(0.8f))
    // - Spacer(16.dp)
    // - Row location + dateTime: SpaceBetween, CenterVertically
    //   → Bên trái: Row với Icon(LocationOn, 16.dp) + Text location
    //   → Bên phải: Surface pill (RoundedCornerShape(50)) + Text dateTime
    Box {}
}

/**
 * Notched Divider — điểm nâng cao nhất của bài này
 *
 * Kỹ thuật: Box với 2 CircleShape "cắt" vào 2 bên + Canvas dashed line ở giữa
 *
 * TODO: [Nâng cao] Implement đúng như hình:
 * Box {
 *     DashedDivider(modifier = Modifier.align(Alignment.Center))
 *
 *     // Notch trái — half outside left edge
 *     Box(modifier = Modifier
 *         .size(24.dp)
 *         .offset(x = (-12).dp)
 *         .clip(CircleShape)
 *         .background(MaterialTheme.colorScheme.background)
 *         .align(Alignment.CenterStart)
 *     )
 *
 *     // Notch phải — half outside right edge
 *     Box(modifier = Modifier
 *         .size(24.dp)
 *         .offset(x = 12.dp)
 *         .clip(CircleShape)
 *         .background(MaterialTheme.colorScheme.background)
 *         .align(Alignment.CenterEnd)
 *     )
 * }
 *
 * Trick: background của notch circles = background của màn hình
 * → tạo ảo giác "cắt vào" card
 */
@Composable
fun NotchedDivider(
    notchSize: Dp = 24.dp,
    modifier: Modifier = Modifier
) {
    // TODO: Implement NotchedDivider
    // - Box với fillMaxWidth + height(notchSize)
    // - DashedDivider ở giữa (align = Alignment.Center)
    // - Box notch trái: size(notchSize), offset(x = -(notchSize/2)), clip(CircleShape),
    //   background(MaterialTheme.colorScheme.background), align(Alignment.CenterStart)
    // - Box notch phải: tương tự nhưng offset(x = notchSize/2), align(Alignment.CenterEnd)
    // GỢI Ý: Tại sao dùng offset() thay vì padding âm?
    // → Compose không hỗ trợ padding âm. offset() di chuyển visual không ảnh hưởng layout.
    Box {}
}

/**
 * Dashed divider dùng Canvas + PathEffect
 *
 * TODO: [Nâng cao] Tại sao cần Canvas thay vì HorizontalDivider thông thường?
 * → HorizontalDivider chỉ vẽ solid line.
 * → Canvas cho phép custom PathEffect.dashPathEffect với interval tùy chọn.
 */
@Composable
fun DashedDivider(
    color: Color = MaterialTheme.colorScheme.outlineVariant,
    dashWidth: Float = 12f,
    gapWidth: Float = 8f,
    strokeWidth: Float = 2f,
    modifier: Modifier = Modifier
) {
    // TODO: Implement DashedDivider
    // - Canvas với fillMaxWidth + height(strokeWidth.dp)
    // - Bên trong Canvas: gọi drawDashedLine(color, dashWidth, gapWidth, strokeWidth)
    Box {}
}

private fun DrawScope.drawDashedLine(
    color: Color,
    dashWidth: Float,
    gapWidth: Float,
    strokeWidth: Float
) {
    // TODO: Implement drawDashedLine
    // - Tạo pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, gapWidth), phase=0f)
    // - drawLine từ (0, size.height/2) đến (size.width, size.height/2)
    //   với color, strokeWidth, cap=StrokeCap.Round, pathEffect
}

@Composable
fun TicketBottomSection(
    seatInfo: String,
    section: String,
    modifier: Modifier = Modifier
) {
    // TODO: Implement TicketBottomSection
    // - Row với fillMaxWidth + padding(20.dp), verticalAlignment = CenterVertically
    // - Bên trái (weight(1f)): Column với "ADMIT ONE" text (labelSmall, Bold, letterSpacing 2sp)
    //   + Text seatInfo (titleMedium, Bold) + Text section (bodySmall, onSurfaceVariant)
    // - VerticalDivider với height(80.dp) + padding(horizontal=16.dp)
    //   GỢI Ý: Tại sao dùng VerticalDivider thay vì Box(width=1.dp)?
    //   → VerticalDivider tự fill height parent, không cần set height thủ công
    // - Bên phải (weight(1f)): Column + Box QR placeholder (80x80, Icon QrCode2) + Text "Scan to enter"
    Box {}
}

// ─── Preview ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, name = "Ticket — Light")
@Composable
private fun TicketCardLightPreview() {
    AppTheme {
        EventTicketCard(
            eventName = "TECH SUMMIT 2026",
            eventSubtitle = "Android & Compose Workshop",
            location = "Hà Nội Convention Center",
            dateTime = "Sat, Mar 7 · 09:00 AM",
            seatInfo = "Row A - Seat 12",
            section = "VIP Section"
        )
    }
}

@Preview(
    showBackground = true,
    name = "Ticket — Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun TicketCardDarkPreview() {
    AppTheme {
        EventTicketCard(
            eventName = "TECH SUMMIT 2026",
            eventSubtitle = "Android & Compose Workshop",
            location = "Hà Nội Convention Center",
            dateTime = "Sat, Mar 7 · 09:00 AM",
            seatInfo = "Row A - Seat 12",
            section = "VIP Section"
        )
    }
}

// ─── Câu Hỏi Thảo Luận ───────────────────────────────────────────────────────
/*
 * Q1: Notch circles dùng background = màn hình để tạo ảo giác "cắt vào"
 *     → Điều gì xảy ra nếu đặt ticket card lên nền màu khác?
 *     → Cách fix proper: dùng custom Shape với path carving (nâng cao)
 *
 * Q2: Tại sao dùng Canvas cho dashed line thay vì một Row lặp nhiều Box nhỏ?
 *     → Performance: Canvas = single draw call. Row+Box = N composables.
 *
 * Q3: VerticalDivider vs Modifier.width(1.dp).background()
 *     → VerticalDivider semantic rõ hơn, height tự fill parent
 *
 * Q4: Modifier.offset() trong notch — tại sao không dùng padding âm?
 *     → padding âm không exist trong Compose. offset() di chuyển visual
 *       mà không ảnh hưởng layout của siblings.
 */
