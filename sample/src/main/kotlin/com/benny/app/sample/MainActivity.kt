package com.benny.app.sample

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import com.benny.app.sample.extension.bindingContext
import com.trello.rxlifecycle.components.support.RxFragmentActivity
import org.jetbrains.anko.*

import com.benny.app.sample.viewmodel.LoginViewModel
import com.benny.library.neobinding.bind.*
import com.benny.library.neobinding.drawable.*
import com.benny.library.neobinding.view.*
import com.benny.library.neobinding.converter.*

import com.benny.app.sample.viewcomponent.LoadingItemViewComponent
import com.benny.library.neobinding.extension.*

class MainActivity : RxFragmentActivity(), LoginViewModel.LoginDelegate {
    val viewModel = LoginViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainActivityUI().setContentView(this).bindTo(bindingContext(this), viewModel)
    }

    override fun onLoginSuccess(user: String) {
        toast("Login success with user " + user)
    }

    override fun onLoginFailed(e: Throwable) {
        toast(e.message ?: "")
    }

    class MainActivityUI : ViewBinderComponent<MainActivity> {
        val AnkoContext<*>.editTextStyle: (View) -> Unit get() = {
            v: View ->
            with(this) {
                if(v is EditText) with(v) {
                    textSizeDimen = R.dimen.font_38
                    verticalPadding = dip(8)
                    horizontalPadding = 0
                    background = null
                }
            }
        }

        val AnkoContext<*>.bgButton: Drawable get() = with(this) {
            stateList {
                borderRoundRect(dip(2).toFloat(), resources.getColor(R.color.color_20blue)) {
                    drawableState = intArrayOf(android.R.attr.state_enabled, android.R.attr.state_pressed)
                }
                borderRoundRect(dip(2).toFloat(), resources.getColor(R.color.color_8b)) {
                    drawableState = intArrayOf(-android.R.attr.state_enabled)
                }
                borderRoundRect(dip(2).toFloat(), resources.getColor(R.color.color_blue)) {
                    drawableState = intArrayOf(android.R.attr.state_enabled)
                }
            }
        }

        override fun builder(): AnkoContext<*>.() -> Unit = {
            verticalLayout {
                inflate(LoadingItemViewComponent(), this@verticalLayout)
                verticalLayout {
                    backgroundColor = Color.WHITE
                    leftPadding = dip(14)
                    editText {
                        hint = "请输入手机号或者电子邮箱地址"
                        bind { text(path="name", mode = BindingMode.TwoWay) }
                    }.lparams(width = matchParent)
                    view { backgroundResource = R.color.color_f2 }.lparams(width = matchParent, height = 1)
                    editText {
                        hint = "请输入密码"
                        bind { text(path="password", mode = BindingMode.TwoWay) }
                    }.lparams(width = matchParent)
                }.lparams(width = matchParent)
                textView {
                    text = "登录"
                    textSizeDimen = R.dimen.font_38
                    textColor = Color.WHITE
                    background = bgButton
                    verticalPadding = dip(10.4f)
                    isClickable = true
                    bind { click("login") }
                    bind { enabled(paths=listOf("name", "password"), converter = ArrayToBooleanConverter()) }
                }.lparams(width = matchParent) { margin = dip(14) }.let { it.gravity = Gravity.CENTER }
            }.style(editTextStyle)
        }
    }
}
