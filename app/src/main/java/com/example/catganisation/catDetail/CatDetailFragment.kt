package com.example.catganisation.catDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.catganisation.R
import com.example.catganisation.databinding.FragmentCatDetailBinding


class CatDetailFragment : Fragment() {
    private lateinit var modelView: CatDetailModelView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCatDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cat_detail, container, false
        )

        val application = requireNotNull(activity).application
        val breedSelected = CatDetailFragmentArgs.fromBundle(requireArguments()).breedSelected
        val viewModelFactory = CatDetailModelViewFactory(breedSelected, application)

        modelView = ViewModelProvider(this, viewModelFactory).get(CatDetailModelView::class.java)
        binding.modelView = modelView
        binding.lifecycleOwner = this

        return binding.root
    }
}