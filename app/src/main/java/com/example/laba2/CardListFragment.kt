package com.example.laba2
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laba2.Network.YourViewModel
import com.google.android.material.snackbar.Snackbar

class CardListFragment : Fragment() {

    private lateinit var viewModel: YourViewModel
    private lateinit var adapter: CardAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        setupRecyclerView()
        setupViewModel()
        viewModel.loadData()
    }

    private fun setupRecyclerView() {
        adapter = CardAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(YourViewModel::class.java)
        viewModel.cards.observe(viewLifecycleOwner, Observer { cardItems ->
            adapter.submitList(cardItems)
        })
        viewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_SHORT).show()
        })
    }
}


