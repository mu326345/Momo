package com.yuyu.momo.detail1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuyu.momo.Application
import com.yuyu.momo.data.ResultItem
import com.yuyu.momo.databinding.FragmentDetail1Binding
import com.yuyu.momo.home.ProviderFactory

class Detail1Fragment : Fragment() {

    private lateinit var binding: FragmentDetail1Binding
    private lateinit var adapter: Detail1Adapter
    private val viewModel by viewModels<Detail1ViewModel> {
        ProviderFactory((context?.applicationContext as Application).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetail1Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.detail1Layout.lifecycleOwner = viewLifecycleOwner
        binding.detail1Layout.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = Detail1Adapter()
        binding.detailRecycler.adapter = adapter
        binding.detailRecycler.layoutManager = LinearLayoutManager(requireContext())

        arguments?.getParcelable<ResultItem>("resultItem")?.also {
            viewModel.takeBundleData(it)
        }

        viewModel.animalItem.observe(viewLifecycleOwner) {
            if(it.isEmpty()) {
                binding.nodataLayout.root.visibility = View.VISIBLE
                binding.detailRecycler.visibility = View.GONE
            } else {
                adapter.submitList(it)
                binding.nodataLayout.root.visibility = View.GONE
                binding.detailRecycler.visibility = View.VISIBLE
            }
        }
    }
}