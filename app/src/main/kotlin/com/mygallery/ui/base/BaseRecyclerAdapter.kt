package com.mygallery.ui.base

import android.os.Handler
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import com.mygallery.utils.DiffUtilCallback
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import java.util.*

/**
 * @author lusinabrian on 27/07/17.
 * @Notes Base recyclerAdapter
 */
abstract class BaseRecyclerAdapter<T>(private var objectList: ArrayList<T>)
    : RecyclerView.Adapter<BaseViewHolder<T>>(), AnkoLogger {

    private val pendingUpdates = ArrayDeque<ArrayList<T>>()

    override val loggerTag: String get() = this::class.java.simpleName

    /**
     * Replaces the content in the views that make up the menu item view and the
     * Ad view. This method is invoked by the layout manager.
     * Gets the item view type and determine what position to display
     * */
    override fun onBindViewHolder(holder: BaseViewHolder<T>?, position: Int) {
        holder?.onBind(position)
    }

    fun clear() {
        objectList.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount() = objectList.size

    override fun getItemId(position: Int): Long {
        return objectList[position]?.hashCode()!!.toLong()
    }


    /**
     * updating items for adapter using notify data set changed, notifies adapter of the said adapter
     * change
     * @param newObjectList new object items to add to adapter
     * */
    fun addItemsUsingDataSetChange(newObjectList: ArrayList<T>) {
        objectList.addAll(newObjectList)
        notifyDataSetChanged()
    }

    /**
     * Add items to the list
     * Will check if the trending list items has a given item and only add items that are not
     * in the adapter
     * Uses [DiffUtil] to calculate whether this adapter should be updated
     * This allows us to perform quicker operations and not keep updating the same items over and
     * over again
     * Calculation of diffs is done on a background thread to avoid blocking operations,
     * hence the reason for a [doAsync] callback
     * @param newObjectList Trending list with trending items
     * */
    fun addItemsUsingDiff(newObjectList: ArrayList<T>) {
        pendingUpdates.add(newObjectList)

        if (pendingUpdates.size > 1) {
            return
        }

        updateItemsInternal(newObjectList)
    }

    private fun updateItemsInternal(newObjectList: ArrayList<T>) {
        val handler = Handler()
        Thread(Runnable {
            val diff = DiffUtilCallback(objectList, newObjectList)
            val diffResult = DiffUtil.calculateDiff(diff)

            handler.post { applyDiffResult(newObjectList, diffResult) }
        }).start()
    }

    private fun applyDiffResult(newItemList: ArrayList<T>, diffResult: DiffUtil.DiffResult) {
        pendingUpdates.remove()

        // dispatch updates
        dispatchUpdates(newItemList, diffResult)

        if (pendingUpdates.size > 0) {
            updateItemsInternal(pendingUpdates.peek())
        }
    }


    private fun dispatchUpdates(newItems: ArrayList<T>, diffResult: DiffUtil.DiffResult) {
        objectList.clear()
        objectList.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}
