package com.yuyu.momo.animal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yuyu.momo.data.AResultItem
import com.yuyu.momo.databinding.FragmentAnimalBinding

class AnimalFragment : Fragment() {

    private lateinit var binding: FragmentAnimalBinding
    private val viewModel by viewModels<AnimalViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAnimalBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<AResultItem>("animalItem")?.also {
            viewModel.takeBundleData(it)
        }
    }
}