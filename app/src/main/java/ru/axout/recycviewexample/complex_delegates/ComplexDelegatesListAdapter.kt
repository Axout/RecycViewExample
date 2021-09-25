package ru.axout.recycviewexample.complex_delegates

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.axout.recycviewexample.models.ComplexItem
import ru.axout.recycviewexample.models.ImageItem
import ru.axout.recycviewexample.models.SimpleItem

class ComplexDelegatesListAdapter(
    onSendEmail: (item: ComplexItem) -> Unit
) : AsyncListDifferDelegationAdapter<Any>(ComplexDiffCallback()) {

    init {
        delegatesManager
            .addDelegate(SimpleItemDelegate(::removeItem))
            .addDelegate(ComplexItemDelegate(onSendEmail))
            .addDelegate(ImageItemDelegate(::removeItem))
            .addDelegate(PageLoadingAdapterDelegate())
    }

    class ComplexDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(first: Any, second: Any): Boolean {
            return first.javaClass == second.javaClass && when (first) {
                is SimpleItem -> first.uuid == (second as SimpleItem).uuid
                is ComplexItem -> first.uuid == (second as ComplexItem).uuid
                is ImageItem -> first.id == (second as ImageItem).id
                else -> true
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second
    }

    private fun removeItem(item: Any) {
        val newItems = items.toMutableList().apply {
            remove(item)
        }
        items = newItems
    }
}
