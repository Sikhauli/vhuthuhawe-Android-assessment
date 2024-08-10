package com.glucode.about_you.engineers

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.glucode.about_you.R
import com.glucode.about_you.databinding.FragmentEngineersBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.viewModel.EngineersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EngineersFragment : Fragment() {
  private lateinit var binding: FragmentEngineersBinding
  private val viewModel: EngineersViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentEngineersBinding.inflate(inflater, container, false)
    setHasOptionsMenu(true)

    viewModel.engineers.observe(viewLifecycleOwner) { engineers ->
      setUpEngineersList(engineers)
    }
    viewModel.loadEngineers()
    return binding.root
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.menu_engineers, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_years -> {
        viewModel.sortEngineersBy { it.quickStats.years }
        true
      }

      R.id.action_coffees -> {
        viewModel.sortEngineersBy { it.quickStats.coffees }
        true
      }

      R.id.action_bugs -> {
        viewModel.sortEngineersBy { it.quickStats.bugs }
        true
      }

      else -> super.onOptionsItemSelected(item)
    }
  }

  private fun setUpEngineersList(engineers: List<Engineer>) {
    binding.list.adapter = EngineersRecyclerViewAdapter(engineers) {
      goToAbout(it)
    }
    if (binding.list.itemDecorationCount == 0) {
      val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
      binding.list.addItemDecoration(dividerItemDecoration)
    }
  }

  private fun goToAbout(engineer: Engineer) {
    val bundle = Bundle().apply {
      putString("name", engineer.name)
    }
    findNavController().navigate(R.id.action_engineersFragment_to_aboutFragment, bundle)
  }
}
