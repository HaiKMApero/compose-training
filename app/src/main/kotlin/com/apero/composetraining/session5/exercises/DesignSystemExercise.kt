package com.apero.composetraining.session5.exercises

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * ⭐⭐⭐⭐⭐ BÀI TẬP NÂNG CAO BUỔI 5: Custom Design System — Production-level pattern
 *
 * Mô tả: Xây dựng custom design system với @Immutable, CompositionLocal,
 *         và design tokens — pattern được dùng trong production apps
 *
 * Key concepts:
 * - @Immutable: báo với Compose rằng class không thay đổi sau khi tạo
 *   → Compose SKIP recompose khi instance không đổi
 * - staticCompositionLocalOf: CompositionLocal cho values ít thay đổi (tokens)
 * - CompositionLocalProvider: inject design tokens vào composition tree
 * - object AppTheme { val colors: AppColorTokens }: access pattern quen thuộc
 */

// ─── Design Tokens ────────────────────────────────────────────────────────────

/**
 * @Immutable annotation — TẠI SAO CẦN?
 *
 * Vấn đề không có @Immutable:
 *   Compose không thể biết AppColorTokens có thay đổi hay không
 *   → Khi parent recompose, Compose cũng recompose tất cả children dù tokens không đổi
 *
 * Với @Immutable:
 *   Compose biết: "Object này KHÔNG BAO GIỜ thay đổi sau khi tạo"
 *   → Compose CÓ THỂ SKIP recompose nếu object reference không đổi
 *
 * Điều kiện để @Immutable đúng:
 *   1. Tất cả properties phải là val (không phải var)
 *   2. Tất cả properties phải là immutable types (Color, Int, String...)
 *   3. KHÔNG được mutate object sau khi tạo
 */
@androidx.compose.runtime.Immutable
data class AppColorTokens(
    // Brand colors
    val brand: Color,
    val brandVariant: Color,
    val brandOnBrand: Color,

    // Semantic colors
    val success: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
    val warning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,
    val error: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,

    // Surface tokens
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,

    // Gradient token
    val gradient: List<Color>,
)

// ─── Light Token Set ──────────────────────────────────────────────────────────

private val LightAppColors = AppColorTokens(
    brand = Color(0xFF7C3AED),
    brandVariant = Color(0xFFEDE9FE),
    brandOnBrand = Color.White,
    success = Color(0xFF16A34A),
    successContainer = Color(0xFFDCFCE7),
    onSuccessContainer = Color(0xFF14532D),
    warning = Color(0xFFD97706),
    warningContainer = Color(0xFFFEF3C7),
    onWarningContainer = Color(0xFF92400E),
    error = Color(0xFFDC2626),
    errorContainer = Color(0xFFFEE2E2),
    onErrorContainer = Color(0xFF7F1D1D),
    surface = Color.White,
    onSurface = Color(0xFF1C1C1E),
    surfaceVariant = Color(0xFFF9FAFB),
    gradient = listOf(Color(0xFF7C3AED), Color(0xFF2563EB), Color(0xFF06B6D4)),
)

// ─── Dark Token Set ───────────────────────────────────────────────────────────

private val DarkAppColors = AppColorTokens(
    brand = Color(0xFFA78BFA),
    brandVariant = Color(0xFF2E1065),
    brandOnBrand = Color(0xFF1C1C2E),
    success = Color(0xFF4ADE80),
    successContainer = Color(0xFF14532D),
    onSuccessContainer = Color(0xFFBBF7D0),
    warning = Color(0xFFFBBF24),
    warningContainer = Color(0xFF78350F),
    onWarningContainer = Color(0xFFFDE68A),
    error = Color(0xFFF87171),
    errorContainer = Color(0xFF7F1D1D),
    onErrorContainer = Color(0xFFFECACA),
    surface = Color(0xFF1C1C1E),
    onSurface = Color(0xFFF9FAFB),
    surfaceVariant = Color(0xFF2C2C2E),
    gradient = listOf(Color(0xFF4C1D95), Color(0xFF1E3A8A), Color(0xFF164E63)),
)

// ─── CompositionLocal ─────────────────────────────────────────────────────────

/**
 * staticCompositionLocalOf vs compositionLocalOf:
 *
 * compositionLocalOf: khi value thay đổi, CHỈ recompose phần sử dụng value đó
 * staticCompositionLocalOf: khi value thay đổi, TOÀN BỘ subtree recompose
 *
 * Dùng staticCompositionLocalOf khi:
 * - Value ít thay đổi (design tokens, theme → chỉ đổi khi dark/light mode)
 * - Muốn performance tốt hơn ở trường hợp KHÔNG thay đổi
 */
val LocalAppColors = staticCompositionLocalOf<AppColorTokens> {
    error("AppColorTokens chưa được cung cấp. Hãy wrap trong AppDesignTheme { }")
}

// ─── AppTheme Access Object ───────────────────────────────────────────────────

/**
 * AppTheme object — access point cho design tokens
 *
 * Cách dùng:
 *   AppTheme.colors.brand  // Lấy brand color
 *   AppTheme.colors.success // Lấy success color
 *
 * Tại sao dùng object thay vì gọi LocalAppColors.current trực tiếp?
 * → API gọn hơn, consistent với MaterialTheme.colorScheme pattern
 */
object AppTheme {
    val colors: AppColorTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current
}

// ─── AppDesignTheme Wrapper ───────────────────────────────────────────────────

/**
 * AppDesignTheme — wrapper theme cho toàn app
 *
 * Tại sao WRAP thêm vào MaterialTheme thay vì thay thế?
 * → MaterialTheme cung cấp M3 composables (Button, Card, etc.)
 * → AppDesignTheme cung cấp custom tokens ngoài M3
 * → 2 layer theme: M3 layer + Custom layer
 */
@Composable
fun AppDesignTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    // TODO: Implement AppDesignTheme
    // 1. Chọn color tokens dựa vào darkTheme:
    //    val appColors = if (darkTheme) DarkAppColors else LightAppColors
    //
    // 2. CompositionLocalProvider inject tokens:
    //    CompositionLocalProvider(LocalAppColors provides appColors) {
    //        MaterialTheme(
    //            colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme(),
    //            content = content
    //        )
    //    }
    //
    // GỢI Ý: CompositionLocalProvider phải wrap bên ngoài MaterialTheme
    // để children có thể access cả AppTheme.colors VÀ MaterialTheme.colorScheme
    Box { content() }
}

// ─── Demo Screen ──────────────────────────────────────────────────────────────

@Composable
fun DesignSystemDemoScreen(modifier: Modifier = Modifier) {
    // TODO: Implement DesignSystemDemoScreen
    // - Column(fillMaxSize, background = AppTheme.colors.surface, verticalScroll)
    // - GradientHeader()
    // - Spacer(16.dp)
    // - Column(padding horizontal=16.dp, spacedBy=16.dp):
    //   → AiRecommendedCard()
    //   → StatusCardsRow()
    //   → TokenReferenceCard()
    // - Spacer(32.dp)
    Box {}
}

// ─── Gradient Header ──────────────────────────────────────────────────────────

@Composable
private fun GradientHeader(modifier: Modifier = Modifier) {
    // TODO: Implement GradientHeader
    // - Box(fillMaxWidth, height=160.dp, background = Brush.linearGradient(AppTheme.colors.gradient))
    // - Column (Center, CenterHorizontally):
    //   → Text "Design System Demo" (headlineMedium, Bold, White)
    //   → Spacer(8.dp)
    //   → Surface pill (White.alpha0.2) với Text "✨ PREMIUM" (White, Bold)
    Box {}
}

// ─── AI Recommended Card ──────────────────────────────────────────────────────

@Composable
private fun AiRecommendedCard(modifier: Modifier = Modifier) {
    // TODO: Implement AiRecommendedCard
    // - Card(fillMaxWidth, brandVariant color, shapes.large)
    // - Column(padding=16.dp):
    //   → Row header: Text title + Surface badge "AI RECOMMENDED" (brand color bg)
    //   → Spacer + Text description (onSurface.alpha0.7)
    //   → Spacer + Row pills: TRENDING pill (warningContainer) + Rating pill (brand.alpha0.1)
    Box {}
}

// ─── Status Cards ─────────────────────────────────────────────────────────────

@Composable
private fun StatusCardsRow(modifier: Modifier = Modifier) {
    // TODO: Implement StatusCardsRow
    // - Column(spacedBy=8.dp):
    //   → Success card: Surface(successContainer) với Row (✅ icon + Column text)
    //   → Error card: Surface(errorContainer) với Row (❌ icon + Column text)
    //   → Warning card: Surface(warningContainer) với Row (⚠️ icon + Column text)
    Box {}
}

// ─── Token Reference Card ──────────────────────────────────────────────────────

@Composable
private fun TokenReferenceCard(modifier: Modifier = Modifier) {
    // TODO: Implement TokenReferenceCard
    // - Card(fillMaxWidth, surfaceVariant color)
    // - Column(padding=16.dp):
    //   → Text "💡 Token Reference" (titleSmall, Bold)
    //   → Spacer(8.dp)
    //   → List of token rows: (tokenName, color, description) → Row với color dot + text
    // GỢI Ý: Hiển thị AppTheme.colors.brand, success, warning, error
    Box {}
}

// ─── Previews ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, name = "Design System - Light")
@Composable
private fun DesignSystemLightPreview() {
    AppDesignTheme(darkTheme = false) {
        DesignSystemDemoScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Design System - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun DesignSystemDarkPreview() {
    AppDesignTheme(darkTheme = true) {
        DesignSystemDemoScreen()
    }
}

@Preview(showBackground = true, name = "AI Recommended Card Preview")
@Composable
private fun AiRecommendedCardPreview() {
    AppDesignTheme {
        AiRecommendedCard(modifier = Modifier.padding(16.dp))
    }
}
