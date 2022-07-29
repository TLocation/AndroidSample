package com.location.composesample

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

/**
 *
 * @author tianxiaolong
 * time：2022/7/20 15:56
 * description：
 */
@Composable
fun WeightGraph(nav: NavHostController,  modifier: Modifier = Modifier){
    ScreenWeight(modifier)
}

@Composable
fun ScreenWeight(modifier: Modifier = Modifier) {
    Log.d("txlTest", "ScreenWeight")
                                                                                 Column(
                                                                                     modifier
                                                                                         .padding(16.dp)
                                                                                         .fillMaxHeight()) {
        LazyColumn {
            itemTitle("文本控件") {
                Text(text = "粗体文本", fontWeight = FontWeight.Bold)
                Text(text = "细点的", fontWeight = FontWeight.Medium)
                Text(text = "斜体文本", fontStyle = FontStyle.Italic)
                Text(text = "粗斜体文本", fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
                Text(text = stringResource(id = R.string.weight_text))
                //超长文字...
                Text(
                    text = "《狂人日记》是鲁迅创作的第一个短篇白话文日记体小说，也是中国第一部现代白话小说，写于1918年4月。该文首发于1918年5月15日4卷5号的《新青年》月刊，后收入《呐喊》集，编入《鲁迅全集》第一卷",
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text("居中对齐", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Text("右对齐", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Right)

                Text(text = "第一行 修改行间距 \n第二行", lineHeight = 30.sp)
            }
            itemTitle("文本输入框") {
                var text1 by remember {
                    mutableStateOf("")
                }
                TextField(value = text1,
                    modifier = Modifier.padding(bottom = 10.dp),
                    placeholder = {
                        //设置提示文字
                        Text(text = "最基本的输入框")
                    },
                    onValueChange = {
                        text1 = it
                    })
                var text1_1 by remember {
                    mutableStateOf("")
                }
                TextField(value = text1_1,
                    modifier = Modifier.padding(bottom = 10.dp),
                    label = {
                        //设置提示文字
                        Text(text = "提示文字2.0")
                    },
                    onValueChange = {
                        text1_1 = it
                    })

                var text2 by remember {
                    mutableStateOf("")
                }
                TextField(value = text2,
                    modifier = Modifier.padding(bottom = 10.dp),
                    placeholder = {
                        //设置提示文字
                        Text(text = "只能输入数字")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        text2 = it
                    })
                Text(text = "leadingIcon 可以设置左边的内容")
                var text3 by remember {
                    mutableStateOf("")
                }
                TextField(value = text3,
                    modifier = Modifier.padding(bottom = 10.dp),
                    leadingIcon = {
                        Icon(Icons.Default.Search, null)
                    },
                    onValueChange = {
                        text3 = it
                    })

                Text(text = "trailingIcon 可以设置右边的内容")
                var text4 by remember {
                    mutableStateOf("")
                }
                TextField(value = text4,
                    modifier = Modifier.padding(bottom = 10.dp),
                    leadingIcon = {
                        Icon(Icons.Default.Search, null)
                    },
                    trailingIcon = {
                        Icon(Icons.Default.Send, null, Modifier.clickable {
                            Toast.makeText(App.context, "点击发送按钮", Toast.LENGTH_SHORT).show()
                        })
                    },
                    onValueChange = {
                        text4 = it
                    })

                var text5 by remember {
                    mutableStateOf("")
                }
                var text5Vis by remember {
                    mutableStateOf(false)
                }
                //设置密码框
                TextField(value = text5,
                    modifier = Modifier.padding(bottom = 10.dp),
                    leadingIcon = {
                        Icon(Icons.Default.Search, null)
                    },
                    label = {
                        //设置提示文字
                        Text(text = "请输入密码")
                    },
                    trailingIcon = {
                        Button(onClick = { text5Vis = !text5Vis }) {
                           Text(text = if (text5Vis) "隐藏" else "显示")
                        }
                    },
                    //显示模式
                    visualTransformation = if (text5Vis) {
                        VisualTransformation.None
                    } else {
                        //这里设置密码框的显示模式
                        PasswordVisualTransformation(mask = '*')
                    },
                    //输入英文和字符
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                    singleLine = true,
                    onValueChange = {
                        text5 = it.trim()
                    })

                Text(text = "BaseTextField 更多自定义")
                SearchEdittext()
            }



        }
    }
}

@Composable
fun SearchEdittext() {
    var text by remember {
        mutableStateOf("")
    }
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF101324))
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF3F51B5), RoundedCornerShape(20.dp))
                .padding(top = 10.dp, bottom = 10.dp)
                .weight(1.0f)
        ) {
            BasicTextField(value = text,
                onValueChange = {
                    text = it
                },
                decorationBox = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Icon(painterResource(R.drawable.search_icon), null)
                        Box(Modifier.weight(1f)) {
                            it()
                        }
                        if (text.isNotEmpty()) {
                            Image(
                                painterResource(R.drawable.delete_icon),
                                null,
                                modifier = Modifier
                                    .clickable {
                                        text = ""
                                    })
                        }
                    }
                }
            )
        }
        Text(
            text = "取消",
            modifier = Modifier
                .padding(start = 10.dp)
                .clickable {
                    text = ""
                },
            color = Color.White
        )
    }
}



inline fun LazyListScope.itemTitle(
    title: String,
    crossinline block: @Composable LazyItemScope.() -> Unit
) {
    item {
        Text(
            text = title,
            style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
        block()
    }
}


@Composable
fun LayoutGraph(nav: NavHostController,  modifier: Modifier = Modifier){
    ScreenLayout(modifier)
}

@Composable
fun ScreenLayout( modifier: Modifier = Modifier) {

//    Text(text = "ScreenLayout")
    //页面切换后保存状态
    var value by rememberSaveable {
        mutableStateOf("默认")
    }
    Log.d("txlTest", "ScreenWeight")
    var count: Int by rememberSaveable {
        mutableStateOf(0)
    }
    Column {
        Text(text = "layout")
        Text(text = value)
        Button(onClick = {
            count += 1
            Log.d("txlTest", "ScreenWeight click count:$count")
            value = "count:${count}"
        }) {
            Text(text = "点击")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenWeightPreview() {
    ScreenWeight()
}

@Preview(showBackground = true)
@Composable
fun searchEdittextPreview() {
    SearchEdittext()
}


@Preview(showBackground = true)
@Composable
fun testEdittext(){
    LazyColumn{
        items(35){
            Text(
                text = "占位符$it",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            var text by remember {
                mutableStateOf("")
            }
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "TextField")
                }
            )
        }

        item {
            var text by remember {
                mutableStateOf("")
            }
            BasicTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .background(Color.Red),
            )
        }
    }
}


