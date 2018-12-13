package me.jessyan.armscomponent.commonsdk.extensions

import java.util.regex.Pattern

/**
 * Created by Chenlu on 2018/11/29
 * Email:chenlu@globalroam.com
 * 扩展类，用于验证输入是否合法
 */

/**
 * 验证是否是合法的用户名
 */
fun String.isValidUserName(): Boolean {
    val regex = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+"
    return Pattern.compile(regex).matcher(this).matches()
}
/**
 * 验证是否是合法的密码
 * 正确格式为：以字母开头，长度在6~18之间，只能包含字符、数字和下划线
 */
fun String.isValidPassword(): Boolean {
    val regex = "^[a-zA-Z]\\w{5,17}\$"
    return Pattern.compile(regex).matcher(this).matches()
}

/**
 * 验证是否是合法的手机号
 */
fun String.isValidPhone(): Boolean {
//    val regex = "0?(13|14|15|17|18|19)[0-9]{9}"
    val regex = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$"
    return Pattern.compile(regex).matcher(this).matches()
}

/**
 * 验证是否是合法的身份证
 */
fun String.isValidIdCard(): Boolean {
    val regex = "\\d{17}[\\d|x]|\\d{15}"
    return Pattern.compile(regex).matcher(this).matches()
}

/**
 * 验证是否是合法的Email地址
 *
 */
fun String.isValidEmail(): Boolean {
    val regex = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}"
    return Pattern.compile(regex).matcher(this).matches()
}

/**
 * 验证是否是合法的IP地址
 */
fun String.isValidIP(): Boolean {
    val regex = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)"
    return Pattern.compile(regex).matcher(this).matches()
}

/**
 * 验证是否是数字
 */
fun String.isNumber(): Boolean {
    val regex = "^[0-9]*$"
    return Pattern.compile(regex).matcher(this).matches()
}

