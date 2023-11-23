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
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.viewmodel.SignUpViewModel

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var viewModel: SignUpViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        view.findViewById<Button>(R.id.signupBtn).setOnClickListener {
            val username = view.findViewById<EditText>(R.id.signupUsername).text.toString()
            val password = view.findViewById<EditText>(R.id.signupPassword).text.toString()
            val otherInfo = view.findViewById<EditText>(R.id.otherInfo).text.toString()
            if (username.isEmpty() || password.isEmpty() || otherInfo.isEmpty()) {
                Toast.makeText(requireActivity(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.signUp(username, password, otherInfo)
            }
        }
        view.findViewById<Button>(R.id.gotoLoginBtn).setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            it.findNavController().navigate(action)
        }
        observeViewModelData()
    }

    private fun observeViewModelData() {
        viewModel.signUpComplete.observe(viewLifecycleOwner) { isComplete ->
            Toast.makeText(requireActivity(), "Signup success", Toast.LENGTH_SHORT).show()
            val action = SignUpFragmentDirections.actionSignUpFragmentToHomeFragment()
            requireView().findNavController().navigate(action)
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), "Error $error", Toast.LENGTH_SHORT).show()
        }
    }
}