package ru.axout.recycviewexample.diff_util

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.axout.recycviewexample.R
import ru.axout.recycviewexample.databinding.FragmentListBinding
import ru.axout.recycviewexample.models.SimpleItem
import ru.axout.recycviewexample.utils.autoCleared
import java.util.*

class DiffUtilListFragment : Fragment(R.layout.fragment_list) {

    private val binding by viewBinding(FragmentListBinding::bind)
    private var diffUtilAdapter: DiffUtilListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        setDefaultItems()
    }

    private fun initList() {
        diffUtilAdapter = DiffUtilListAdapter()
        with(binding.list) {
            val orientation = RecyclerView.VERTICAL
            adapter = diffUtilAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)
            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun setDefaultItems() {
        val defaultItems = List(20) {
            val randomUUID = UUID.randomUUID()
            SimpleItem(
                title = getString(R.string.simpleTitle),
                uuid = randomUUID,
                author = getString(R.string.author)
            )
        }
        diffUtilAdapter.setItems(defaultItems)
    }
}