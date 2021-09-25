package ru.axout.recycviewexample.complex_delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
//import kotlinx.android.synthetic.main.item_complex.view.*
import kotlinx.android.synthetic.main.item_image.view.*
import ru.axout.recycviewexample.R
import ru.axout.recycviewexample.models.ImageItem

class ImageItemDelegate(
    private val onItemClick: (item: ImageItem) -> Unit
) : AbsListItemAdapterDelegate<Any, Any, ImageItemDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is ImageItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ViewHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as ImageItem)
    }

    inner class ViewHolder(
        override val containerView: View,
        private val onItemClick: (item: ImageItem) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var currentItem: ImageItem? = null

        init {
            containerView.setOnClickListener { currentItem?.let(onItemClick) }
        }

        fun bind(item: ImageItem) = with(containerView) {
            currentItem = item
            tv_uuid.text = "Уникальный id: ${item.id}"
        }
    }
}