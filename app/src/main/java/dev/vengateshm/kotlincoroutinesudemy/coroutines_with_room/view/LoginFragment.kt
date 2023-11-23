package dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import dev.vengateshm.kotlincoroutinesudemy.R
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.viewmodel.LoginViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        view.findViewById<Button>(R.id.loginBtn).setOnClickListener {
            val username = view.findViewById<EditText>(R.id.loginUsername).text.toString()
            val password = view.findViewById<EditText>(R.id.loginPassword).text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireActivity(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.login(username, password)
            }
        }
        view.findViewById<Button>(R.id.gotoSignupBtn).setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            it.findNavController().navigate(action)
        }

        observeViewModelData()
    }

    private fun observeViewModelData() {
        viewModel.loggedIn.observe(viewLifecycleOwner) {
            Toast.makeText(requireActivity(), "Logged in", Toast.LENGTH_SHORT).show()
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            requireView().findNavController().navigate(action)
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), "Error $error", Toast.LENGTH_SHORT).show()
        }
    }
}