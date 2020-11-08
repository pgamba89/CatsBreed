package com.example.catganisation.catsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.catganisation.R
import com.example.catganisation.databinding.FragmentCatsListBinding
import com.example.catganisation.databinding.FragmentLoginBinding
import com.example.catganisation.login.LoginFragmentDirections

class CatsListFragment : Fragment() {

    private lateinit var modelView: CatsListModelView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCatsListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cats_list, container, false
        )

        modelView = ViewModelProvider(this).get(CatsListModelView::class.java)
        binding.viewModel = modelView
        binding.lifecycleOwner

        modelView.catModel.observe(viewLifecycleOwner, {
            Toast.makeText(activity, "OKKK" + it[0].url, Toast.LENGTH_LONG).show()
        })

        return binding.root
    }
}