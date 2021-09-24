package ru.axout.recycviewexample.complex

import android.os.Bundle
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
import ru.axout.recycviewexample.models.SimpleItem
import ru.axout.recycviewexample.utils.autoCleared
import java.util.*

class ComplexListFragment : Fragment(R.layout.fragment_list) {

    private val binding by viewBinding(FragmentListBinding::bind)
    private var complexAdapter: ComplexListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        setDefaultItems()
    }

    private fun initList() {
        complexAdapter = ComplexListAdapter()
        with(binding.list) {
            val orientation = RecyclerView.VERTICAL
            adapter = complexAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)
            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun setDefaultItems() {
        val defaultItems = List(20) {
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
        complexAdapter.setItems(defaultItems)
    }
}