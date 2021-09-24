package ru.axout.recycviewexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.axout.recycviewexample.databinding.FragmentStartBinding

class StartFragment : Fragment(R.layout.fragment_start) {

    private val binding by viewBinding(FragmentStartBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons() {
        binding.simpleListActivity.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToSimpleListFragment()
            findNavController().navigate(action)
        }
        binding.diffUtilListActivity.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToDiffUtilListFragment()
            findNavController().navigate(action)
        }
        binding.complexListActivity.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToComplexListFragment()
            findNavController().navigate(action)
        }
        binding.complexListWithDelegates.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToComplexListWithDelegatesFragment()
            findNavController().navigate(action)
        }
    }

}