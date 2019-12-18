package com.giedrius.baseproject.utils.mvp

interface BasePresenter<T> {

    fun takeView(view: T)
    fun dropView()
}
