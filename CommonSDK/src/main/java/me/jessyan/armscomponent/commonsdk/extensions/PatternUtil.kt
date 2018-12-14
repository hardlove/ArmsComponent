package me.jessyan.armscomponent.commonsdk.extensions

/**
 * Created by Chenlu on 2018/11/29
 * Email:chenlu@globalroam.com
 * 扩展类，用于验证输入是否合法
 */

/**
 * 验证是否是合法的用户名
 */
fun String.isValidUserName(): Boolean = this.matches(Regex("^[a-zA-Z]\\w{2,19}$"))

/**
 * 验证是否是合法的密码
 * 正确格式为：以字母开头，长度在6~18之间，只能包含字符、数字和下划线
 */
fun String.isValidPassword(): Boolean = this.matches(Regex("^[a-zA-Z]\\w{5,17}\$"))

/**
 * 验证是否是合法的手机号
 */
fun String.isValidPhone(): Boolean = this.matches(Regex("^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(14[5-9])|(166)|(19[8,9])|)\\d{8}$"))

/**
 * 验证是否是合法的身份证
 */
fun String.isValidIdCard(): Boolean = this.matches(Regex("\\d{17}[\\d|x]|\\d{15}"))

/**
 * 验证是否是合法的Email地址
 *
 */
fun String.isValidEmail(): Boolean = this.matches(Regex("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$"))

/**
 * 验证是否是合法的IP地址
 */
fun String.isValidIP(): Boolean = this.matches(Regex("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)"))

/**
 * 验证是否是数字
 */
fun String.isNumber(): Boolean = this.matches(Regex("^[0-9]*$"))

