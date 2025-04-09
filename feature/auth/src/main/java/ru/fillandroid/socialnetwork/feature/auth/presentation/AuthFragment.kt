package ru.fillandroid.socialnetwork.feature.auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fillandroid.socialnetwork.common.extensions.gone
import ru.fillandroid.socialnetwork.common.extensions.show
import ru.fillandroid.socialnetwork.feature.auth.databinding.FragmentAuthBinding
import ru.fillandroid.socialnetwork.feature.auth.presentation.AuthViewModel.UiEvent.ShowCodeInput
import ru.fillandroid.socialnetwork.feature.auth.presentation.AuthViewModel.UiEvent.ShowToast
import ru.fillandroid.socialnetwork.feature.auth.presentation.AuthViewModel.UiEvent.NavigateToFeed
import ru.fillandroid.socialnetwork.feature.auth.R.string as StringResource

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = checkNotNull(_binding)

    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupViews()
        observeState()
    }

    private fun setupViews() {
        binding.btnRequestCode.setOnClickListener {
            val inputView = binding.etLogin
            if (inputView.text.isNullOrEmpty()) {
                inputView.error = resources.getString(StringResource.login_input)
            } else {
                inputView.error = null
                viewModel.requestCode()
            }
        }
        binding.etCode.doOnTextChanged { text, _, _, _ ->
            if (text?.count() == 4) {
                viewModel.validateCode(text.toString())
            }
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.eventFlow.collectLatest { event ->
                when(event) {
                    is ShowCodeInput -> {
                        binding.run {
                            etLogin.gone()
                            etCode.show()
                            btnRequestCode.gone()
                        }
                    }
                    is ShowToast -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                    }
                    is NavigateToFeed -> {

                    }
                }
            }
        }
    }
}