package com.yuyu.momo.detail1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuyu.momo.Application
import com.yuyu.momo.MainActivity
import com.yuyu.momo.MainViewModel
import com.yuyu.momo.R
import com.yuyu.momo.data.ResultItem
import com.yuyu.momo.databinding.FragmentDetail1Binding
import com.yuyu.momo.home.ProviderFactory

class Detail1Fragment : Fragment() {

    private lateinit var binding: FragmentDetail1Binding
    private lateinit var adapter: Detail1Adapter
    private val viewModel by viewModels<Detail1ViewModel> {
        ProviderFactory((context?.applicationContext as Application).repository)
    }
    private val mainViewModel by activityViewModels<MainViewModel>()
    private lateinit var detailUri: String

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

        adapter = Detail1Adapter {
            findNavController().navigate(R.id.action_detail1Fragment_to_animalFragment, Bundle().apply {
                putParcelable("animalItem", it)
            })
            setAppBar(it.a_name_ch, R.drawable.ic_baseline_arrow_back_24)
            (activity as MainActivity).canBackPress = true
        }
        binding.detailRecycler.adapter = adapter
        binding.detailRecycler.layoutManager = LinearLayoutManager(requireContext())

        arguments?.getParcelable<ResultItem>("resultItem")?.also {
            viewModel.takeBundleData(it)
            detailUri = it.e_url
        }

        binding.detail1Layout.openTv.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.setData(Uri.parse(detailUri))
                startActivity(intent)
            } catch (e: Exception) {

            }
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

    private fun setAppBar(title: String, image: Int) {
        mainViewModel.apply {
            changeBarTitle(title)
            changeBarImage(image)
        }
    }
}