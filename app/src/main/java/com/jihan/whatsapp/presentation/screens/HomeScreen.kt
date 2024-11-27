package com.jihan.whatsapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.jihan.whatsapp.presentation.destinations.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {


    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            TopAppBar(title = {
                Text("WhatsApp Clone")
            }, actions = {
                IconButton(onClick = {
                    Firebase.auth.signOut()
                    navController.popBackStack()
                    navController.navigate(Destination.Login)
                }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                }
            }, scrollBehavior = scrollBehaviour
            )

        }) { innerPadding ->

        TabHorizontalPager(Modifier.padding(innerPadding),navController)

    }



}
    @Composable
    private fun TabHorizontalPager(modifier: Modifier,navController: NavController) {
        Column(
            modifier = modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface),
        ) {

            //?  Tab Items
            val tabItems = listOf(
                "Chat", "Status", "Call"
            )

            val state = rememberPagerState(pageCount = { tabItems.size })

            var selectedIndex by remember { mutableIntStateOf(0) }

            //? Horizontal Pager

            TabRow(selectedTabIndex = selectedIndex) {
                tabItems.forEachIndexed { index, tabItem ->

                    //? TAB
                    Tab(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },

                        content = {
                            val textColor = if (selectedIndex == index) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            }
                            Text(tabItem, color = textColor, fontSize = 20.sp, modifier = Modifier.padding(12.dp))
                        },
                    )

                }
            }


            //?Changing pager contents on tab Item click

            LaunchedEffect(selectedIndex) {
                state.animateScrollToPage(selectedIndex)
            }

            LaunchedEffect(state.currentPage, state.isScrollInProgress) {
                if (!state.isScrollInProgress) selectedIndex = state.currentPage
            }


            //?Horizontal Pager
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), state = state
            ) { index ->

                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                    when (index) {
                        0 -> ChatsScreen{
                            navController.navigate(Destination.ChatDetail(
                                senderId = it.senderId,
                                receiverId = it.receiverId,
                                receiverName = it.receiverName,
                                receiverImage = it.receiverImage
                            ))
                        }
                        1 -> StatusScreen()
                        2 -> CallsScreen()
                    }

                }

            }

        }
    }





