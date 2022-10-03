package com.yuyu.momo.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuyu.momo.Application
import com.yuyu.momo.MainActivity
import com.yuyu.momo.MainViewModel
import com.yuyu.momo.R
import com.yuyu.momo.databinding.FragmentHomeBinding
import com.yuyu.momo.detail1.Detail1ViewModel
import com.yuyu.momo.repository.DefaultRepository
import java.lang.IllegalArgumentException


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private val viewModel by viewModels<HomeViewModel> {
        ProviderFactory((context?.applicationContext as Application).repository)
    }
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setAppBar(getString(R.string.taipei_zoo), R.drawable.ic_baseline_menu_24)
        (activity as MainActivity).canBackPress = false

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeAdapter {
            findNavController().navigate(R.id.action_homeFragment_to_detail1Fragment, Bundle().apply {
                putParcelable("resultItem", it)
            })
            setAppBar(it.e_name, R.drawable.ic_baseline_arrow_back_24)
            (activity as MainActivity).canBackPress = true
        }
        binding.homeRecycler.adapter = adapter
        binding.homeRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.resultItem.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
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

class ProviderFactory(private val repo: DefaultRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(repo)

                isAssignableFrom(Detail1ViewModel::class.java) ->
                    Detail1ViewModel(repo)

                else -> throw IllegalArgumentException("Unable to construct viewModel")
            }
        } as T
}