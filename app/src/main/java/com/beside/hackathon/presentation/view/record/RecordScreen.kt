package com.beside.hackathon.presentation.view.record

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.Indicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.beside.hackathon.R
import com.beside.hackathon.core.utils.Colors.BG_GREY
import com.beside.hackathon.core.utils.Colors.BUTTON_YELLOW
import com.beside.hackathon.core.utils.Constant
import com.beside.hackathon.core.utils.Constant.BORDER_RADIUS
import com.beside.hackathon.core.utils.Constant.DEFAULT_PADDING_H
import com.beside.hackathon.core.utils.DataUtils
import com.beside.hackathon.core.utils.TextStyles.CONTENT_SMALL1_STYLE
import com.beside.hackathon.core.utils.TextStyles.TITLE_TEXT3_STYLE
import com.beside.hackathon.core.utils.TextStyles.TITLE_TEXT_STYLE
import com.beside.hackathon.data.model.record.CardNewsRecord
import com.beside.hackathon.data.model.record.SummaryRecord
import com.beside.hackathon.presentation.view.common.DefaultLayout
import com.beside.hackathon.presentation.view.quizhistory.QuizHistoryItem
import com.beside.hackathon.presentation.view.quizhistory.fromModel
import java.util.Date

@Composable
fun RecordScreen(navController: NavController, recordViewModel: RecordViewModel) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("카드뉴스", "필기노트")
    Column() {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                // 여기서 인디케이터의 모양과 색상을 정의합니다.
                // 예시로는 단순한 노란색 인디케이터를 만들어봅니다.
                Indicator(
                    color = BUTTON_YELLOW,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[tabIndex])
                        .height(2.5.dp)
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier.background(Color.White),
                    text = { Text(title,style=TITLE_TEXT_STYLE.copy(
                        color = if(tabIndex == index) BUTTON_YELLOW else Color.Black,
                        fontWeight = if(tabIndex == index) FontWeight.W700 else FontWeight.W500
                    )) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> {
                Column (modifier = Modifier.fillMaxSize()){
                    CardnewsTabScreen(navController, recordViewModel)
                }
            }

            1 -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    SummaryTabScreen(navController, recordViewModel)
                }
            }
        }
    }
}
@Preview
@Composable
fun RecordScreenPreview(){
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("카드뉴스", "필기노트")
    Column() {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                // 여기서 인디케이터의 모양과 색상을 정의합니다.
                // 예시로는 단순한 노란색 인디케이터를 만들어봅니다.
                Indicator(
                    color = BUTTON_YELLOW,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[tabIndex])
                        .height(5.dp)
                )
            }

        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier.background(Color.White),
                    text = { Text(title,style=TITLE_TEXT_STYLE.copy(
                        color = if(tabIndex == index) BUTTON_YELLOW else Color.Black
                    )) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }

    }
}

@Preview
@Composable
fun RecordItemPreview(){
    //RecordItem.fromCardNews()
}


object RecordItem

@Composable
fun RecordItem.fromCardNews(model: CardNewsRecord){
    RecordItemBase(
        title = model.title,
        date = model.publishedDate,
        time = 10,
        imageUrl = if(model.urls.isNotEmpty()) model.urls[0] else null
    )
}

@Composable
fun RecordItem.fromSummary(model: SummaryRecord){
    RecordItemBase(
        title = model.subject,
        date = model.publishedDate,
        time = model.takenTime,
        imageUrl = null,
        isIcon = true
    )
}


@Composable
internal fun RecordItemBase(
    title: String,
    date: String,
    time: Int,
    imageUrl : String? =null,
    isIcon: Boolean = false,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(
                color = BG_GREY,
                shape = RoundedCornerShape(BORDER_RADIUS)
            )
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        if(imageUrl != null){
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.FillWidth,
            )
            Spacer(modifier =Modifier.width(10.dp))
        }
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(){
                Column {
                    Text(text = title, style = TITLE_TEXT3_STYLE)
                    if(isIcon){
                        Text("한 주 동안 배웠던 카드뉴스의 내용을\n필기노트로 요약/정리 해보아요:)",
                            style = CONTENT_SMALL1_STYLE
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                if(isIcon){

                    Image(
                        painter = painterResource(id = R.drawable.record_icon),
                        modifier = Modifier.size(70.dp),
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.baseline_schedule_24),
                    contentDescription = "소요시간",
                    modifier = Modifier.size(15.dp),
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text("${time}분",style =CONTENT_SMALL1_STYLE.copy(
                    color = Color.Gray
                ))
                Spacer(modifier = Modifier.width(10.dp))
                Text(date,style =CONTENT_SMALL1_STYLE.copy(
                    color = Color.Gray
                ))
            }
        }


    }
}

@Composable
internal fun CardnewsTabScreen(navController: NavController,viewModel: RecordViewModel){
    val cardnewsList = viewModel.cardNewsRecords.collectAsLazyPagingItems()
    DefaultLayout {
        Column(modifier = Modifier.padding(DEFAULT_PADDING_H)){
            LazyColumn {
                items(cardnewsList.itemCount) { index ->
                    cardnewsList[index]?.let { record ->
                        Box(
                            modifier = Modifier.clickable {
                                val bundle = Bundle()
                                bundle.putStringArrayList("urls", ArrayList(record.urls))
                                navController.navigate(R.id.action_recordFragment_to_cardNewsRecordFragment, bundle)
                            }
                        ){
                            RecordItem.fromCardNews(record)
                        }
                        Spacer(modifier = Modifier.height(Constant.DEFAULT_PADDING_V))
                    }
                }
            }
        }
    }
}

@Composable
internal fun SummaryTabScreen(navController: NavController, viewModel: RecordViewModel){
    val summaryList = viewModel.summaryRecords.collectAsLazyPagingItems()
    DefaultLayout {
        Column(modifier = Modifier.padding(DEFAULT_PADDING_H)){
            LazyColumn {
                items(summaryList.itemCount) { index ->
                    summaryList[index]?.let { record ->
                        Box(
                            modifier = Modifier.clickable {
                                val bundle = Bundle()
                                bundle.putString("url", record.content)
                                navController.navigate(R.id.action_recordFragment_to_webViewFragment, bundle)
                            }
                        ){
                            RecordItem.fromSummary(record)
                        }
                        Spacer(modifier = Modifier.height(Constant.DEFAULT_PADDING_V))
                    }
                }
            }
        }
    }
}