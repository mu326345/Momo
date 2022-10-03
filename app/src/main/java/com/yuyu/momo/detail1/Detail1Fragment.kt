package com.yuyu.momo.detail1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yuyu.momo.data.ResultItem
import com.yuyu.momo.databinding.FragmentDetail1Binding

class Detail1Fragment : Fragment() {

    private lateinit var binding: FragmentDetail1Binding
    private val viewModel by viewModels<Detail1ViewModel>()

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

        arguments?.getParcelable<ResultItem>("resultItem")?.also {
            viewModel.takeBundleData(it)
        }
    }
}