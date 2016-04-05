package com.benny.app.sample.viewmodel

import com.benny.app.sample.network.service.douban.model.Movie
import com.benny.library.kbinding.adapterview.viewmodel.SimpleItemViewModel

/**
 * Created by benny on 11/19/15.
 */

class MovieViewModel() : SimpleItemViewModel<Movie>() {

    override var data: Movie? by bindProperty("data")

    val title: String? by bindProperty("title", "data") { data!!.title }
    val smallCover: String? by bindProperty("smallCover", "data") { data!!.images.small }
    val bigCover: String? by bindProperty("bigCover", "data") { data!!.images.large }
    val score: Float by bindProperty("score", "data") { data!!.rating.average }
    val casts: String? by bindProperty("casts", "data") { data!!.casts.map { it -> it.name }.joinToString("/") }
    val genres: String? by bindProperty("genres", "data") { data!!.genres.joinToString("/") }
    val summary: String? by bindProperty("summary", "data") { data!!.summary }
    val ratingsCount: Int by bindProperty("ratingsCount", "data") { data!!.ratingsCount }
}