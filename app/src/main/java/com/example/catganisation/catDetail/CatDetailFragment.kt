package com.example.catganisation.catDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.catganisation.R
import com.example.catganisation.databinding.FragmentCatDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatDetailFragment : Fragment() {

    private val modelView: CatDetailModelView by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCatDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cat_detail, container, false
        )

        binding.modelView = modelView
        binding.lifecycleOwner = this

        return binding.root
    }
}