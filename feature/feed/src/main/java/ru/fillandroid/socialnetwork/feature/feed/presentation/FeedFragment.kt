package ru.fillandroid.socialnetwork.feature.feed.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fillandroid.socialnetwork.feature.feed.databinding.FragmentFeedBinding
import ru.fillandroid.socialnetwork.feature.feed.presentation.FeedViewModel.UiEvent.ShowToast

class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = checkNotNull(_binding)

    private val viewModel: FeedViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupViews()
        observeState()
    }

    private fun setupViews() {

    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.eventFlow.collectLatest { event ->
                when(event) {
                    is ShowToast -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}
