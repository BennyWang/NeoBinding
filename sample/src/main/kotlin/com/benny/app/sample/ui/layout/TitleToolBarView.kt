package com.benny.app.sample.ui.layout

import android.graphics.Color
import android.view.Gravity
import com.benny.app.sample.R
import com.benny.app.sample.ui.extension.toolbar
import com.benny.library.kbinding.view.ViewBinderComponent
import org.jetbrains.anko.*

/**
 * Created by benny on 1/15/16.
 */

class TitleToolBarView<T>(val title: String) : ViewBinderComponent<T> {
    override fun builder(): AnkoContext<T>.() -> Unit = {
        toolbar(R.style.ToolbarTheme) {
            backgroundColor = Color.parseColor("#393a4c")
            textView {
                text = this@TitleToolBarView.title
                textSize = 16f
                textColor = Color.WHITE
            }.lparams(wrapContent, wrapContent) { gravity = Gravity.CENTER }
        }
    }
}