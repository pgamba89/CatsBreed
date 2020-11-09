package com.example.catganisation.catsList

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catganisation.R
import com.example.catganisation.databinding.FragmentCatsListBinding

class CatsListFragment : Fragment() {

    private lateinit var modelView: CatsListModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val countries = resources.getStringArray(R.array.countries)
        for (i in countries.indices){
            menu.add(countries[i].toString())
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        Toast.makeText(activity, item.title, Toast.LENGTH_SHORT).show()

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCatsListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cats_list, container, false
        )

        modelView = ViewModelProvider(this).get(CatsListModelView::class.java)
        binding.viewModel = modelView
        binding.lifecycleOwner = this

        val adapter = CatsListAdapter(ListItemListener { breed ->
            view?.findNavController()
                ?.navigate(CatsListFragmentDirections.actionCatsListFragmentToCatDetailFragment(breed))
        })

        binding.recyclerviewlist.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewlist.adapter = adapter

        modelView.catBreeds.observe(viewLifecycleOwner, Observer {
            it?.let { adapter.submitList(it) }
        })

        return binding.root
    }
}