package com.kudzaichasinda.starwarscharacters.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kudzaichasinda.starwarscharacters.databinding.FragmentCharacterBinding
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

        observeCharacterLiveData()
        observeFilmLiveData()
        observeFilmLiveData()
        observeSpecieLiveData()

        viewModel.getCharacter(args.url)
    }

    private fun observeCharacterLiveData() {
        viewModel.characterLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    val character = result.data

                    viewModel.getFilms(character.films)
                    viewModel.getHomeWorld(character.homeWorld)
                    viewModel.getSpecies(character.species)
                }
                is Result.Idle -> {

                }
                is Result.Loading -> {

                }
                is Result.Error -> {

                }
            }
        })
    }

    private fun observePlanetLiveData() {
        viewModel.planetLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {

                }
                is Result.Idle -> {

                }
                is Result.Loading -> {

                }
                is Result.Error -> {

                }
            }
        })
    }

    private fun observeSpecieLiveData() {
        viewModel.specieLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {

                }
                is Result.Idle -> {

                }
                is Result.Loading -> {

                }

                is Result.Error -> {

                }
            }
        })
    }

    private fun observeFilmLiveData() {
        viewModel.filmLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {

                }
                is Result.Idle -> {

                }
                is Result.Loading -> {

                }

                is Result.Error -> {

                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}