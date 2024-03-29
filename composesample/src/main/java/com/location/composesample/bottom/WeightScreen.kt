package com.location.composesample.bottom

/**
 *
 * @author tianxiaolong
 * time：2022/8/12 16:29
 * description：
 */
sealed class WeightScreen(rotate:String, val rotateName:String = "weight/$rotate") {

    object Home : WeightScreen("home")
    object Button : WeightScreen("button")
    object Text : WeightScreen("text")
    object TextFiled : WeightScreen("text_filed")
    object Image : WeightScreen("image")
    object CheckBox : WeightScreen("image_filed")
    object RadioButton : WeightScreen("radio_button")
}
