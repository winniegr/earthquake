package com.example.earthquake.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.earthquake.viewmodel.EarthquakeViewModel
import com.example.earthquake.databinding.FragmentEarthquakeBinding


class EarthquakeFragment : Fragment() {

    var binding: FragmentEarthquakeBinding? = null
    private lateinit var adapter: EarthquakeRecyclerViewAdapter
    private val viewModel by viewModels<EarthquakeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEarthquakeBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = EarthquakeRecyclerViewAdapter(requireActivity())
        binding!!.recyclerView.adapter = adapter
        addObservers()
        viewModel.getEarthquakes()
    }

    private fun addObservers() {
        viewModel.earthquake.observe(viewLifecycleOwner) {
            it.features?.let { features ->
                adapter.features = features
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding!!.loadingView.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.failed.observe(viewLifecycleOwner) {
            binding!!.listError.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}