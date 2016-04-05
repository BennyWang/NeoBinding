/*
 * Copyright 2016. Alex Zhang aka. ztc1997
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.benny.library.kbinding.support.v4.bindings

import android.support.v4.widget.DrawerLayout
import com.benny.library.kbinding.bind.OneWay
import com.benny.library.kbinding.bind.TwoWay
import com.benny.library.kbinding.bind.oneWayPropertyBinding
import com.benny.library.kbinding.bind.twoWayPropertyBinding
import com.benny.library.kbinding.converter.EmptyOneWayConverter1
import com.benny.library.kbinding.converter.EmptyTwoWayConverter
import com.benny.library.kbinding.converter.OneWayConverter
import com.benny.library.kbinding.converter.TwoWayConverter
import com.jakewharton.rxbinding.support.v4.widget.RxDrawerLayout

fun DrawerLayout.drawerOpen(gravity: Int, vararg paths: String, mode: OneWay = OneWay(), converter: OneWayConverter<*, Boolean> = EmptyOneWayConverter1())
        = oneWayPropertyBinding(paths, RxDrawerLayout.open(this, gravity), false, converter)

fun DrawerLayout.drawerOpen(gravity: Int, path: String, mode: TwoWay, converter: TwoWayConverter<Boolean, Any?> = EmptyTwoWayConverter())
        = twoWayPropertyBinding(path, RxDrawerLayout.drawerOpen(this, gravity), RxDrawerLayout.open(this, gravity), converter)