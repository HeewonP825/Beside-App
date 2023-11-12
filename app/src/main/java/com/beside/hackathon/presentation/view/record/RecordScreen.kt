package com.beside.hackathon.presentation.view.record

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.beside.hackathon.R
import com.beside.hackathon.core.utils.Colors.BG_GREY
import com.beside.hackathon.core.utils.Colors.BUTTON_YELLOW
import com.beside.hackathon.core.utils.Constant.BORDER_RADIUS
import com.beside.hackathon.core.utils.Constant.DEFAULT_PADDING_H
import com.beside.hackathon.core.utils.DataUtils
import com.beside.hackathon.core.utils.TextStyles.CONTENT_SMALL1_STYLE
import com.beside.hackathon.core.utils.TextStyles.TITLE_TEXT3_STYLE
import com.beside.hackathon.core.utils.TextStyles.TITLE_TEXT_STYLE
import com.beside.hackathon.presentation.view.common.DefaultLayout
import java.util.Date

@Composable
fun RecordScreen(navController: NavController, recordViewModel: RecordViewModel) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("카드뉴스", "필기노트")
    Column() {
        TabRow(
            selectedTabIndex = tabIndex
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
                    CardnewsTabScreen()
                }
            }

            1 -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    SummaryTabScreen()
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
            selectedTabIndex = tabIndex
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
        when (tabIndex) {
            0 -> {
                Column (){
                    CardnewsTabScreen()
                }
            }

            1 -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    SummaryTabScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun RecordItemPreview(){
    RecordItem.fromCardNews()
}


object RecordItem

@Composable
fun RecordItem.fromCardNews(){
    RecordItemBase(
        title = "테스트",
        date = Date(),
        time = 10,
        imageUrl = "https://picsum.photos/200/300"
    )
}

@Composable
fun RecordItem.fromSummary(){
    RecordItemBase(
        title = "테스트",
        date = Date(),
        time = 10,
        imageUrl = null,
        isIcon = true
    )
}


@Composable
internal fun RecordItemBase(
    title: String,
    date: Date,
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
                Text(DataUtils.conventDateToString(date),style =CONTENT_SMALL1_STYLE.copy(
                    color = Color.Gray
                ))
            }
        }


    }
}

@Composable
internal fun CardnewsTabScreen(){
    DefaultLayout {
        Column(modifier = Modifier.padding(DEFAULT_PADDING_H)){
            Spacer(modifier = Modifier.height(15.dp))
            RecordItem.fromCardNews()
            Spacer(modifier = Modifier.height(15.dp))
            RecordItem.fromCardNews()
            Spacer(modifier = Modifier.height(15.dp))
            RecordItem.fromCardNews()

        }
    }
}

@Composable
internal fun SummaryTabScreen(){
    DefaultLayout {
        Column(modifier = Modifier.padding(DEFAULT_PADDING_H)){
            RecordItem.fromSummary()
            Spacer(modifier = Modifier.height(15.dp))
            RecordItem.fromSummary()
            Spacer(modifier = Modifier.height(15.dp))
            RecordItem.fromSummary()
            Spacer(modifier = Modifier.height(15.dp))
            RecordItem.fromSummary()
        }
    }
}