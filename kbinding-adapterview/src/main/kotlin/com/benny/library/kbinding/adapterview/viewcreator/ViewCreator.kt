package com.benny.library.kbinding.adapterview.viewcreator

import android.view.View
import android.view.ViewGroup
import com.benny.library.autoadapter.utils.Func3
import com.benny.library.autoadapter.viewcreator.IViewCreator
import com.benny.library.autoadapter.viewcreator.ViewCreatorCollection
import com.benny.library.kbinding.adapterview.viewmodel.ItemViewModel
import com.benny.library.kbinding.adapterview.viewmodel.MockItemViewModel
import com.benny.library.kbinding.bind.BindingDisposer
import com.benny.library.kbinding.view.BindingDisposerGenerator

/**
 * Created by benny on 3/12/16.
 */

open class ViewCreator<T>(val bindingDisposer: BindingDisposer, val itemViewBinderComponent: ItemViewBinderComponent, val viewModelFactory: () -> ItemViewModel<T>) : IViewCreator<T> {

    override fun view(container: ViewGroup): View {
        val viewBinder = itemViewBinderComponent.createViewBinder(container.context, container)
        val viewModel = viewModelFactory()
        viewBinder.bindTo(bindingDisposer, viewModel)
        viewBinder.view.tag = viewModel
        return viewBinder.view
    }

    override fun viewTypeFor(data: T?, position: Int, itemCount: Int): Int {
        return 0
    }

    override fun viewTypeCount(): Int {
        return 1
    }
}

fun <T> BindingDisposerGenerator.viewCreator(viewBinderComponent: ItemViewBinderComponent, viewModelFactory: () -> ItemViewModel<T>) = ViewCreator(bindingDisposer, viewBinderComponent, viewModelFactory)
fun <T> BindingDisposerGenerator.pagingViewCreator(loadingViewBinderComponent: ItemViewBinderComponent, itemViewBinderComponent: ItemViewBinderComponent, viewModelFactory: () -> ItemViewModel<T>): IViewCreator<T> {
    return ViewCreatorCollection.Builder<T>()
            .addFilter(Func3 { data, position, itemCount -> data != null }, ViewCreator(bindingDisposer, itemViewBinderComponent, viewModelFactory))
            .addFilter(Func3 { data, position, itemCount -> data == null && position == itemCount - 1 }, ViewCreator(bindingDisposer, loadingViewBinderComponent, { MockItemViewModel<T>() }))
            .build()
}