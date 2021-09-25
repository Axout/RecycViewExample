package ru.axout.recycviewexample.complex_delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_complex.view.*
import ru.axout.recycviewexample.R
import ru.axout.recycviewexample.models.ComplexItem

class ComplexItemDelegate(
    private val onItemClick: (item: ComplexItem) -> Unit
) : AbsListItemAdapterDelegate<Any, Any, ComplexItemDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is ComplexItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_complex, parent, false)
        return ViewHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as ComplexItem)
    }

    inner class ViewHolder(
        override val containerView: View,
        private val onItemClick: (item: ComplexItem) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var currentItem: ComplexItem? = null

        init {
            containerView.setOnClickListener { currentItem?.let(onItemClick) }
        }

        fun bind(item: ComplexItem) = with(containerView) {
            currentItem = item
            tv_titleComplex.text = item.title
            tv_subtitleComplex.text = item.subTitle
            tv_emailComplex.text = "Email: ${item.email}"
            tv_uuidComplex.text = "Уникальный id: ${item.uuid}"
        }
    }
}