package ru.axout.recycviewexample.complex_delegates

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.axout.recycviewexample.R
import ru.axout.recycviewexample.databinding.FragmentListBinding
import ru.axout.recycviewexample.models.ComplexItem
import ru.axout.recycviewexample.models.ImageItem
import ru.axout.recycviewexample.models.LoadingItem
import ru.axout.recycviewexample.models.SimpleItem
import ru.axout.recycviewexample.utils.PaginationScrollListener
import ru.axout.recycviewexample.utils.autoCleared
import java.util.*

class ComplexListWithDelegatesFragment : Fragment(R.layout.fragment_list) {

    private val binding by viewBinding(FragmentListBinding::bind)
    private var complexAdapter: ComplexDelegatesListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        loadMoreItems()
    }

    private fun initList() {
        complexAdapter = ComplexDelegatesListAdapter(
            // Sharing
            onSendEmail = { complexItem ->
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${complexItem.email}")
                    putExtra(Intent.EXTRA_SUBJECT, complexItem.title)
                }
                startActivity(intent)
            }
        )
        with(binding.list) {
            val orientation = RecyclerView.VERTICAL
            adapter = complexAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

            // Pagination
            addOnScrollListener(
                PaginationScrollListener(
                    layoutManager = layoutManager as LinearLayoutManager,
                    requestNextItems = ::loadMoreItems,
                    visibilityThreshold = 0
                )
            )

            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun getDefaultItems() = List(20) {
        val randomUUID = UUID.randomUUID()
        when ((1..3).random()) {
            1 -> SimpleItem(
                title = getString(R.string.simpleTitle),
                uuid = randomUUID,
                author = getString(R.string.author)
            )
            2 -> ComplexItem(
                title = getString(R.string.simpleTitle),
                subTitle = getString(R.string.simpleSubTitle),
                email = getString(R.string.email),
                uuid = randomUUID
            )
            3 -> ImageItem(
                id = it
            )
            else -> error("Wrong random number")
        }
    }

    private fun loadMoreItems() {
        val newItems = complexAdapter.items.toMutableList().apply {
            if (lastOrNull() is LoadingItem) {
                removeLastOrNull()
            }
        } + getDefaultItems() + LoadingItem()
        complexAdapter.items = newItems
        Log.d("Pagination", newItems.size.toString())
    }
}