package com.mygallery.ui.base

import android.support.v7.widget.RecyclerView
import android.view.View
import org.jetbrains.anko.AnkoLogger

/**
 * @author lusinabrian on 16/08/17.
 * @Notes Base view holder
 */
abstract class BaseViewHolder<E> constructor(itemView: View) : RecyclerView.ViewHolder(itemView), AnkoLogger {
    lateinit var itemList: ArrayList<E>
    private var mCurrentPosition: Int = 0

    constructor(itemView: View, itemList: ArrayList<E>) : this(itemView) {
        this.itemList = itemList
    }

    /**
     * Binds the item by the position in the adapter list
     * to the particular view
     * @param position of item view in recycler adapter
     * */
    open fun onBind(position: Int) {
        mCurrentPosition = position
    }

    open fun getCurrentPosition(): Int {
        return mCurrentPosition
    }

    override val loggerTag: String get() = this.javaClass.simpleName
}