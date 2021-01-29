package com.kudzaichasinda.starwarscharacters.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kudzaichasinda.starwarscharacters.databinding.FragmentCharacterBinding
import com.kudzaichasinda.starwarscharacters.ui.character.adapter.FilmAdapter
import com.kudzaichasinda.starwarscharacters.ui.character.adapter.SpecieAdapter
import com.kudzaichasinda.starwarscharacters.util.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterViewModel by viewModels()

    private val args: CharacterFragmentArgs by navArgs()

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

        observeCharacterLiveData()
        observePlanetLiveData()
        observeFilmLiveData()
        observeSpecieLiveData()

        viewModel.getCharacter(args.url)
    }

    private fun observeCharacterLiveData() {
        viewModel.characterLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    binding.isCharacterLoading = false
                    binding.toolbar.title = result.data.name

                    val character = result.data
                    binding.character = character

                    if (character.species.isEmpty())
                        binding.groupSpecie.visibility = GONE

                    viewModel.getFilms(character.films)
                    viewModel.getHomeWorld(character.homeWorld)
                    viewModel.getSpecies(character.species)
                }
                is Result.Idle -> {

                }
                is Result.Loading -> {
                    binding.isCharacterLoading = true
                }
                is Result.Error -> {
                    binding.isCharacterLoading = false
                    result.message?.let { showToast(it) }
                }
            }
        })
    }

    private fun observePlanetLiveData() {
        viewModel.planetLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    binding.isPlanetLoading = false
                    val planet = result.data

                    binding.planet = planet
                }
                is Result.Idle -> {

                }
                is Result.Loading -> {
                    binding.isPlanetLoading = true
                }
                is Result.Error -> {
                    binding.isPlanetLoading = false
                    result.message?.let { showToast(it) }
                }
            }
        })
    }

    private fun observeSpecieLiveData() {
        viewModel.specieLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    binding.specieShouldShow = true

                    val species = result.data

                    if(species.isEmpty())
                        binding.specieShouldShow = false
                    else{
                        binding.speciesList.apply {
                            adapter = SpecieAdapter().apply {
                                submitList(species)
                            }
                        }
                    }
                }
                is Result.Idle -> {

                }
                is Result.Loading -> {
                    binding.specieShouldShow = false
                }

                is Result.Error -> {
                    binding.specieShouldShow = false
                    result.message?.let { showToast(it) }
                }
            }
        })
    }

    private fun observeFilmLiveData() {
        viewModel.filmLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    binding.isFilmsLoading = false
                    val films = result.data

                    binding.filmList.apply {
                        adapter = FilmAdapter().apply {
                            submitList(films)
                        }
                    }
                }
                is Result.Idle -> {

                }
                is Result.Loading -> {
                    binding.isFilmsLoading = true
                }

                is Result.Error -> {
                    binding.isFilmsLoading = false
                    result.message?.let { showToast(it) }
                }
            }
        })
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