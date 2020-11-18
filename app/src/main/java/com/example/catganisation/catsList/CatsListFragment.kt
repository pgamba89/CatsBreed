package com.example.catganisation.catsList

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catganisation.R
import com.example.catganisation.databinding.FragmentCatsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatsListFragment : Fragment() {

    private val FILTER_ALL = "All"
    private val viewModel: CatsListModelView by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val countries = resources.getStringArray(R.array.countries)
        for (i in countries.indices) {
            menu.add(countries[i].toString())
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title.contains(FILTER_ALL)) {
            viewModel.getBreedNoFilter()
        } else {
            viewModel.getBreedFilter(item.title.toString())
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCatsListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cats_list, container, false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = CatsListAdapter(ListItemListener { breed ->
            view?.findNavController()
                ?.navigate(
                    CatsListFragmentDirections.actionCatsListFragmentToCatDetailFragment(breed)
                )
            viewModel.clean()
        })

        binding.recyclerviewlist.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewlist.adapter = adapter

        viewModel.catBreeds.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.catBreedsFiltered.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        })
        return binding.root
    }
}