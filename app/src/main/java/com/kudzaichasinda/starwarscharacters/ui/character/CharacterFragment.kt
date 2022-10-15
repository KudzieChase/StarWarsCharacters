package com.kudzaichasinda.starwarscharacters.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kudzaichasinda.starwarscharacters.databinding.FragmentCharacterBinding
import com.kudzaichasinda.starwarscharacters.state.CharacterScreenUiState
import com.kudzaichasinda.starwarscharacters.state.CharacterViewUiState
import com.kudzaichasinda.starwarscharacters.state.FilmsViewUiState
import com.kudzaichasinda.starwarscharacters.state.PlanetViewUiState
import com.kudzaichasinda.starwarscharacters.state.SpeciesViewUiState
import com.kudzaichasinda.starwarscharacters.ui.character.adapter.FilmAdapter
import com.kudzaichasinda.starwarscharacters.ui.character.adapter.SpecieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterViewModel by viewModels()

    private val args: CharacterFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { viewState ->
                    renderViewState(viewState = viewState)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getCharacter(args.url)
    }

    private fun renderViewState(viewState: CharacterScreenUiState) {
        renderCharacterState(viewState = viewState.characterUiState)
        renderPlanetState(viewState = viewState.planetUiState)
        renderSpecieState(viewState = viewState.speciesViewUiState)
        renderFilmState(viewState = viewState.filmsUiState)
    }

    private fun renderCharacterState(viewState: CharacterViewUiState) {
        binding.isCharacterLoading = viewState.isLoading

        viewState.character?.let { character ->
            binding.toolbar.title = character.name

            binding.character = character

            if (character.species.isEmpty()) {
                binding.groupSpecie.visibility = GONE
            }

            viewModel.getFilms(character.films)
            viewModel.getHomeWorld(character.homeWorld)
            viewModel.getSpecies(character.species)
        }

        viewState.error?.let {
            showToast(it)
        }
    }

    private fun renderPlanetState(viewState: PlanetViewUiState) {
        binding.isPlanetLoading = viewState.isLoading
        viewState.planet?.let { planet ->
            binding.planet = planet
        }

        viewState.error?.let {
            showToast(it)
        }
    }

    private fun renderSpecieState(viewState: SpeciesViewUiState) {
        binding.isSpeciesLoading = viewState.isLoading

        val species = viewState.species

        if (species.isEmpty()) {
            binding.groupSpecie.visibility = GONE
        } else {
            binding.speciesList.apply {
                adapter = SpecieAdapter().apply {
                    submitList(species)
                }
            }
        }

        viewState.error?.let {
            showToast(it)
        }
    }

    private fun renderFilmState(viewState: FilmsViewUiState) {
        binding.isFilmsLoading = viewState.isLoading
        val films = viewState.films

        binding.filmList.apply {
            adapter = FilmAdapter().apply {
                submitList(films)
            }
        }

        viewState.error?.let {
            showToast(it)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
