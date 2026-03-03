package com.apero.composetraining.session5.exercises

import android.content.res.Configuration
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apero.composetraining.common.AppTheme

/**
 * ⭐⭐⭐⭐ BÀI TẬP NÂNG CAO BUỔI 5: Dynamic Color + Font Showcase
 *
 * Mô tả: Demo dynamic color (Android 12+) và Material 3 color roles/typography
 *
 * Key concepts:
 * - dynamicLightColorScheme(context): lấy màu từ wallpaper Android 12+
 * - Build.VERSION.SDK_INT >= Build.VERSION_CODES.S: check API level
 * - MaterialTheme.colorScheme: tất cả 25 color roles của Material 3
 * - Typography scale: displayLarge → labelSmall (15 styles)
 */

// ─── Main Screen ──────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicColorScreen(modifier: Modifier = Modifier) {
    // TODO: Implement DynamicColorScreen
    // 1. Get context: val context = LocalContext.current
    //    val systemInDark = isSystemInDarkTheme()
    //
    // 2. State toggles:
    //    var useDynamicColor by remember { mutableStateOf(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) }
    //    var isDarkMode by remember { mutableStateOf(systemInDark) }
    //
    // 3. Tính color scheme dựa theo toggles:
    //    val selectedColorScheme = when {
    //        useDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S → {
    //            if (isDarkMode) dynamicDarkColorScheme(context)
    //            else dynamicLightColorScheme(context)
    //        }
    //        isDarkMode → darkColorScheme()
    //        else → lightColorScheme()
    //    }
    //    GỢI Ý: Tại sao cần SDK_INT check?
    //    → dynamicLightColorScheme() chỉ có trên Android 12 (API 31)
    //    → Gọi trên API thấp hơn → crash
    //
    // 4. MaterialTheme(colorScheme = selectedColorScheme) {
    //        Surface(fillMaxSize) {
    //            Column(fillMaxSize, padding=16.dp, verticalScroll) {
    //                Text header + ToggleControls
    //                if (SDK < S) ApiWarningCard() else if (useDynamicColor) DynamicColorInfoCard()
    //                HorizontalDivider
    //                Text "Material 3 Color Roles" + ColorPaletteGrid()
    //                HorizontalDivider
    //                Text "Typography Scale" + TypographyShowcase()
    //            }
    //        }
    //    }
    Box {}
}

// ─── Toggle Controls ──────────────────────────────────────────────────────────

@Composable
private fun ToggleControls(
    useDynamicColor: Boolean,
    isDarkMode: Boolean,
    onDynamicToggle: (Boolean) -> Unit,
    onDarkModeToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement ToggleControls
    // - Card(fillMaxWidth, surfaceVariant color)
    // - Column(padding=16.dp):
    //   → Row 1: Column("Dynamic Color" + subtitle về API support) + Switch
    //     Switch disabled nếu SDK < S
    //   → HorizontalDivider(padding vertical=8.dp)
    //   → Row 2: Text "Dark Mode" + Switch
    Box {}
}

// ─── Warning Cards ────────────────────────────────────────────────────────────

@Composable
private fun ApiWarningCard(modifier: Modifier = Modifier) {
    // TODO: Implement ApiWarningCard
    // - Card(fillMaxWidth, errorContainer color)
    // - Row(padding=16.dp, spacedBy=12.dp):
    //   → Text "⚠️" (titleLarge)
    //   → Column: Text title (titleSmall, Bold, onErrorContainer)
    //     + Text "Your device runs Android ${SDK_INT}. Dynamic Color requires API 31+..."
    Box {}
}

@Composable
private fun DynamicColorInfoCard(modifier: Modifier = Modifier) {
    // TODO: Implement DynamicColorInfoCard
    // - Card(fillMaxWidth, primaryContainer color)
    // - Row(padding=16.dp, spacedBy=12.dp):
    //   → Text "✨"
    //   → Column: Text "Dynamic Color Active" + Text description về Monet engine
    Box {}
}

// ─── Color Palette Grid ───────────────────────────────────────────────────────

/**
 * Hiển thị tất cả 25 Material 3 color roles dạng grid
 *
 * Material 3 có 5 key colors × 5 roles = 25 color roles:
 * Primary, Secondary, Tertiary, Error, Neutral
 */
@Composable
private fun ColorPaletteGrid(modifier: Modifier = Modifier) {
    // TODO: Implement ColorPaletteGrid
    // 1. Định nghĩa list of ColorRole:
    //    val colorRoles = listOf(
    //        ColorRole("primary", colorScheme.primary, colorScheme.onPrimary),
    //        ColorRole("onPrimary", colorScheme.onPrimary, colorScheme.primary),
    //        ColorRole("primaryContainer", colorScheme.primaryContainer, colorScheme.onPrimaryContainer),
    //        // ... 22 colors còn lại (secondary, tertiary, error, surface groups)
    //    )
    //
    // 2. LazyVerticalGrid(columns = GridCells.Fixed(3), height=420.dp, userScrollEnabled=false):
    //    items(colorRoles) { role → ColorSwatch(role) }
    //
    // GỢI Ý: Cần fixed height vì grid nằm trong outer scroll Column
    Box {}
}

data class ColorRole(
    val name: String,
    val color: Color,
    val textColor: Color,
)

@Composable
private fun ColorSwatch(
    role: ColorRole,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement ColorSwatch
    // - Box(height=60.dp, clip shapes.small, background = role.color, padding=4.dp)
    // - Text role.name.take(12) (labelSmall, textColor, maxLines=2)
    Box {}
}

// ─── Typography Showcase ──────────────────────────────────────────────────────

@Composable
private fun TypographyShowcase(modifier: Modifier = Modifier) {
    // TODO: Implement TypographyShowcase
    // - Column(fillMaxWidth, spacedBy=4.dp)
    // - Show all 15 type styles with TypographyItem:
    //   displayLarge, displayMedium, displaySmall
    //   headlineLarge, headlineMedium, headlineSmall
    //   titleLarge, titleMedium, titleSmall
    //   bodyLarge, bodyMedium, bodySmall
    //   labelLarge, labelMedium, labelSmall
    // - Thêm HorizontalDivider giữa các groups
    Box {}
}

@Composable
private fun TypographyItem(
    name: String,
    sample: String,
    textStyle: androidx.compose.ui.text.TextStyle,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement TypographyItem
    // - Row(fillMaxWidth, padding vertical=2.dp, spacedBy=8.dp, CenterVertically)
    // - Text name (labelSmall, outline, width=120.dp)
    // - Text sample (textStyle, maxLines=1, weight(1f))
    Box {}
}

// ─── Previews ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, name = "Dynamic Color - Light")
@Composable
private fun DynamicColorScreenPreview() {
    AppTheme {
        DynamicColorScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Dynamic Color - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun DynamicColorScreenDarkPreview() {
    AppTheme(darkTheme = true) {
        DynamicColorScreen()
    }
}
