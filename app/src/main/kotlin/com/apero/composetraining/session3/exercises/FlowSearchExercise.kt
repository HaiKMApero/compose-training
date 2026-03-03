package com.apero.composetraining.session3.exercises

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.apero.composetraining.common.AppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest

/**
 * ⭐⭐⭐⭐ BÀI TẬP NÂNG CAO BUỔI 3: Real-time Search với Flow + collectAsStateWithLifecycle
 *
 * Mô tả: Search contacts theo tên với debouncing, async loading, lifecycle-aware collection
 *
 * ┌─────────────────────────────────┐
 * │ 🔍 Search contacts...           │  ← TextField search bar
 * ├─────────────────────────────────┤
 * │  ● Alice Johnson                │  ← Results (filtered)
 * │    alice@email.com              │
 * │  ● Bob Smith                    │
 * │    bob@email.com                │
 * └─────────────────────────────────┘
 *
 * Key concepts:
 * - snapshotFlow: chuyển Compose State → Flow để dùng Flow operators (debounce, distinctUntilChanged)
 * - produceState: chuyển async logic → Compose State (bridge từ coroutines về Compose)
 * - collectAsStateWithLifecycle: collect Flow nhưng tự PAUSE khi app vào background
 *   → Khác collectAsState: collectAsState vẫn collect khi background (tốn tài nguyên)
 */

// ─── Data Model ──────────────────────────────────────────────────────────────

data class Contact(
    val id: Int,
    val name: String,
    val email: String,
)

// 20 contacts giả để demo search
private val fakeContacts = listOf(
    Contact(1, "Alice Johnson", "alice@example.com"),
    Contact(2, "Bob Smith", "bob@example.com"),
    Contact(3, "Charlie Brown", "charlie@example.com"),
    Contact(4, "Diana Prince", "diana@example.com"),
    Contact(5, "Edward Norton", "edward@example.com"),
    Contact(6, "Fiona Apple", "fiona@example.com"),
    Contact(7, "George Miller", "george@example.com"),
    Contact(8, "Hannah Lee", "hannah@example.com"),
    Contact(9, "Ivan Drago", "ivan@example.com"),
    Contact(10, "Julia Roberts", "julia@example.com"),
    Contact(11, "Kevin Hart", "kevin@example.com"),
    Contact(12, "Laura Palmer", "laura@example.com"),
    Contact(13, "Michael Scott", "michael@example.com"),
    Contact(14, "Nancy Drew", "nancy@example.com"),
    Contact(15, "Oscar Wilde", "oscar@example.com"),
    Contact(16, "Petra Pan", "petra@example.com"),
    Contact(17, "Quinn Hughes", "quinn@example.com"),
    Contact(18, "Rachel Green", "rachel@example.com"),
    Contact(19, "Steve Rogers", "steve@example.com"),
    Contact(20, "Tina Turner", "tina@example.com"),
)

// ─── Search Result State ─────────────────────────────────────────────────────

// Sealed class để represent các trạng thái của search result
sealed class SearchResult {
    data object Loading : SearchResult()
    data class Success(val contacts: List<Contact>) : SearchResult()
    data object Empty : SearchResult()
}

// ─── Main Composable ─────────────────────────────────────────────────────────

/**
 * FlowSearchScreen — màn hình search chính
 *
 * Pattern: SearchScreen tự quản lý query state (stateful),
 * nhưng tách logic search ra produceState/snapshotFlow
 */
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@Composable
fun FlowSearchScreen(modifier: Modifier = Modifier) {
    // TODO: Implement FlowSearchScreen
    // 1. Khai báo: var searchQuery by remember { mutableStateOf("") }
    // 2. Khai báo searchResultState bằng produceState:
    //    val searchResultState: SearchResult by produceState(
    //        initialValue = SearchResult.Success(fakeContacts),
    //        key1 = searchQuery
    //    ) {
    //        // Bên trong produceState chạy trong coroutine
    //        snapshotFlow { searchQuery }           ← chuyển State → Flow
    //            .debounce(300L)                    ← đợi user ngừng gõ 300ms
    //            .distinctUntilChanged()             ← bỏ qua query không đổi
    //            .mapLatest { query ->
    //                value = SearchResult.Loading   ← set loading
    //                delay(300L)                    ← giả lập network
    //                // filter fakeContacts theo query
    //                // return Success hoặc Empty
    //            }
    //            .collect { value = it }
    //    }
    // 3. Column(fillMaxSize, padding=16.dp):
    //    → Text tiêu đề + subtitle
    //    → Spacer + SearchBar(searchQuery, onQueryChange = { searchQuery = it })
    //    → Spacer + when(searchResultState):
    //        Loading → Box(fillMaxSize, Center) { CircularProgressIndicator() }
    //        Empty → EmptySearchState(searchQuery)
    //        Success → Text count + ContactResultList(contacts)
    // GỢI Ý: Tại sao dùng snapshotFlow thay vì observe searchQuery trực tiếp?
    // → Để dùng được .debounce(), .distinctUntilChanged() — không có trên MutableState
    Box {}
}

// ─── Search Bar Component ─────────────────────────────────────────────────────

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement SearchBar
    // - OutlinedTextField với fillMaxWidth + singleLine + shapes.large
    // - placeholder: "Search contacts..."
    // - leadingIcon: Icon(Search)
    // - trailingIcon: nếu query.isNotEmpty() → IconButton với Icon(Clear), onClick = { onQueryChange("") }
    Box {}
}

// ─── Contact List ─────────────────────────────────────────────────────────────

@Composable
private fun ContactResultList(
    contacts: List<Contact>,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement ContactResultList
    // - LazyColumn với fillMaxSize + spacedBy(4.dp)
    // - items(contacts, key = { it.id }) { contact → ContactItem(contact) }
    // GỢI Ý: key = { it.id } quan trọng cho animation và performance tracking
    Box {}
}

@Composable
private fun ContactItem(
    contact: Contact,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement ContactItem
    // - ListItem với:
    //   headlineContent: Text contact.name
    //   supportingContent: Text contact.email (onSurfaceVariant)
    //   leadingContent: Surface(size=40dp, extraLarge shape, primaryContainer) {
    //       Box(Center) { Icon(Person, size=24dp) }
    //   }
    Box {}
}

// ─── Empty State ──────────────────────────────────────────────────────────────

@Composable
private fun EmptySearchState(
    query: String,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement EmptySearchState
    // - Column(fillMaxSize, Center, CenterHorizontally)
    // - Icon(Search, size=64dp, onSurfaceVariant)
    // - Spacer(16.dp)
    // - Text "No contacts found" (titleMedium, onSurfaceVariant)
    // - Text "No results for \"$query\"" (bodyMedium, outline)
    Box {}
}

// ─── collectAsStateWithLifecycle Demo ────────────────────────────────────────

/**
 * Demo: Sự khác biệt giữa collectAsState và collectAsStateWithLifecycle
 *
 * collectAsState:
 * - Collect liên tục, kể cả khi app ở background
 * - Tốn battery, tốn resources
 *
 * collectAsStateWithLifecycle:
 * - Tự PAUSE khi Activity/Fragment stop (vào background)
 * - Tự RESUME khi Activity/Fragment start lại
 * - KHUYẾN NGHỊ dùng thay collectAsState trong production
 *
 * Cú pháp:
 * val uiState by viewModel.uiState.collectAsStateWithLifecycle()
 * //                                  ↑ Lifecycle-aware, tự pause/resume
 */
@Composable
private fun CollectAsStateDemo(modifier: Modifier = Modifier) {
    // TODO: Implement demo card giải thích collectAsStateWithLifecycle
    // - Card với secondaryContainer color
    // - Column padding(16.dp): Text title + Text description
    Box {}
}

// ─── Previews ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, name = "Flow Search - Light")
@Composable
private fun FlowSearchScreenPreview() {
    AppTheme {
        FlowSearchScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Flow Search - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun FlowSearchScreenDarkPreview() {
    AppTheme(darkTheme = true) {
        FlowSearchScreen()
    }
}

@Preview(showBackground = true, name = "Contact Item Preview")
@Composable
private fun ContactItemPreview() {
    AppTheme {
        ContactItem(
            contact = Contact(1, "Alice Johnson", "alice@example.com"),
        )
    }
}

@Preview(showBackground = true, name = "Empty State Preview")
@Composable
private fun EmptyStatePreview() {
    AppTheme {
        EmptySearchState(query = "xyz")
    }
}
