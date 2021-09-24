package ru.axout.recycviewexample.diff_util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_simple.view.*
import ru.axout.recycviewexample.R
import ru.axout.recycviewexample.models.SimpleItem

class DiffUtilListAdapter : DiffAdapter<SimpleItem, DiffUtilListAdapter.ViewHolder>() {

    override fun getDiffCalculator(
        oldItems: List<SimpleItem>,
        newItems: List<SimpleItem>
    ): DiffCalculator<SimpleItem> = object : DiffCalculator<SimpleItem>(oldItems, newItems) {
        override fun areSame(first: SimpleItem, second: SimpleItem): Boolean {
            return first.uuid == second.uuid
        }

        override fun contentsAreSame(first: SimpleItem, second: SimpleItem): Boolean {
            return first == second
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_simple, parent, false)
        return ViewHolder(itemView) { item ->
            removeItem(item)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun removeItem(item: SimpleItem) {
        val newItems = getItems().toMutableList().apply {
            remove(item)
        }
        setItems(newItems)
    }

    class ViewHolder(
        override val containerView: View,
        onItemClick: (item: SimpleItem) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var currentItem: SimpleItem? = null

        init {
            containerView.setOnClickListener {
                currentItem?.let { item ->
                    onItemClick.invoke(item)
                }
            }
        }

        fun bind(item: SimpleItem) = with(containerView) {
            currentItem = item
            tv_title.text = item.title
            tv_randomNumSubtitle.text = "Уникальный id: ${item.uuid}"
            tv_author.text = item.author
        }
    }
}