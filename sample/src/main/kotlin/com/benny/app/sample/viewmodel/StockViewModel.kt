package com.benny.app.sample.viewmodel

import com.benny.app.sample.network.service.caishuo.model.Stock
import com.benny.library.kbinding.adapterview.viewmodel.SimpleItemViewModel
import com.benny.library.kbinding.annotation.ExtractProperty
import kotlin.properties.Delegates

/**
 * Created by benny on 11/19/15.
 */
class StockViewModel() : SimpleItemViewModel<Stock>() {

    @delegate:ExtractProperty(
            "name", "symbol", "realtimePrice", "changePercent", "changePrice", "listedState", "market",
            hasPrefix = false
    )
    override var data: Stock? by Delegates.property()
}