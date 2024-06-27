package com.example.week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week1.ui.theme.Week1Theme
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.graphics.Color

data class Contact(
    val name: String,
    val phoneNumber: String
)

object ContactRepository {
    val contacts = listOf(
        Contact("짱구", "010-1234-5678"),
        Contact("철수", "010-2345-6789"),
        Contact("유리", "010-3456-7890"),
        Contact("맹구", "010-4567-8901"),
        Contact("훈이", "010-5678-9012"),
        Contact("흰둥이", "010-6789-0123"),
        Contact("짱구", "010-1234-5678"),
        Contact("철수", "010-2345-6789"),
        Contact("유리", "010-3456-7890"),
        Contact("맹구", "010-4567-8901"),
        Contact("훈이", "010-5678-9012"),
        Contact("흰둥이", "010-6789-0123")
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week1Theme {
                val tabs = listOf("Tab 1", "Tab 2", "Tab 3")
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    TabbedContactList(tabs = tabs)
                }
            }
        }
    }
}

@Composable
fun TabbedContactList(tabs: List<String>) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .statusBarsPadding() // 상태바 패딩 추가
                .height(72.dp) // 높이를 원하는 대로 설정
                .padding(bottom = 8.dp) // 탭과 콘텐츠 사이에 간격 추가
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                ) {
                    Text(text = title, modifier = Modifier.padding(vertical = 16.dp))
                }
            }
        }

        when (selectedTabIndex) {
            0 -> ContactList(ContactRepository.contacts)
            1 -> ContactList(ContactRepository.contacts)
            2 -> ContactList(ContactRepository.contacts)
        }
    }
}

@Composable
fun ContactList(contacts: List<Contact>) {
    LazyColumn(
        modifier = Modifier.padding(top = 8.dp) // 리스트 상단에 패딩 추가
    ) {
        items(contacts) { contact ->
            ContactItem(contact = contact)
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(226,226,226)), // 연한 배경색 설정
        elevation = CardDefaults.cardElevation(4.dp) // 그림자 설정
    ) {
        Text(
            text = "${contact.name}: ${contact.phoneNumber}",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactListPreview() {
    Week1Theme {
        ContactList(ContactRepository.contacts)
    }
}
