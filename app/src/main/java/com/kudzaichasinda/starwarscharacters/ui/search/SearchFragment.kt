package com.kudzaichasinda.starwarscharacters.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.kudzaichasinda.starwarscharacters.databinding.FragmentSearchBinding
import com.kudzaichasinda.starwarscharacters.model.CharacterView
import com.kudzaichasinda.starwarscharacters.util.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            viewModel.performSearch(text.toString())
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            searchInput.addTextChangedListener(textWatcher)

            clearText.setOnClickListener {
                clear()
            }

            viewModel.searchResults.asLiveData()
                .observe(viewLifecycleOwner, { result ->
                    when (result) {
                        is Result.Success -> {
                            isLoading = false
                            hideLoadingStateLayout()
                            showRecyclerView(result.data)
                        }
                        is Result.Idle -> {
                            isLoading = false
                            showLoadingStateLayout()
                            hideRecyclerView()
                            hideEmptyState()
                        }
                        is Result.Loading -> {
                            isLoading = true
                            showLoadingStateLayout()
                            hideEmptyState()
                            hideRecyclerView()
                        }
                        is Result.Error -> {
                            isLoading = false
                            showLoadingStateLayout()
                            hideRecyclerView()
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                })
        }
    }

    private fun clear() {
        binding.searchInput.editableText.clear()
        viewModel.performSearch("")
    }

    private fun showLoadingStateLayout() {
        binding.loadingView.loadingView.visibility = VISIBLE
    }

    private fun hideLoadingStateLayout() {
        binding.loadingView.loadingView.visibility = GONE
    }

    private fun showRecyclerView(results: List<CharacterView>) {
        binding.apply {

            resultsList.visibility = VISIBLE

            if (results.isEmpty()) {
                showEmptyState()
            } else {
                hideEmptyState()
            }

            resultsList.adapter = SearchResultAdapter(::onItemClick)
                .apply {
                    submitList(results)
                }
        }
    }

    private fun hideRecyclerView() {
        binding.resultsList.visibility = GONE
    }

    private fun showEmptyState() {
        binding.emptyStateView.emptyStateLayout.visibility = VISIBLE
    }

    private fun hideEmptyState() {
        binding.emptyStateView.emptyStateLayout.visibility = GONE
    }

    private fun onItemClick(character: CharacterView) {
        clear()
        val directions = SearchFragmentDirections.actionSearchFragmentToCharacterFragment(
            character.url
        )
        findNavController().navigate(directions)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}