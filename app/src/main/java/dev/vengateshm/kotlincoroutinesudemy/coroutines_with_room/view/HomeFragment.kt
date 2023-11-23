package dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import dev.vengateshm.kotlincoroutinesudemy.R
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.model.LoginState
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.viewmodel.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        view.findViewById<TextView>(R.id.usernameTV).apply {
            if (LoginState.isLoggedIn) {
                text = LoginState.user?.username ?: "username"
            }
        }

        view.findViewById<Button>(R.id.signoutBtn).setOnClickListener {
            viewModel.signOut()
        }
        view.findViewById<Button>(R.id.deleteUserBtn).setOnClickListener {
            AlertDialog.Builder(requireActivity())
                .setTitle("Delete User")
                .setMessage("Are you sure you want to delete this user?")
                .setPositiveButton("Yes") { _, _ -> viewModel.deleteUser() }
                .setNegativeButton("No", null)
                .create()
                .show()
        }

        observeViewModelData()
    }

    private fun observeViewModelData() {
        viewModel.signedOut.observe(viewLifecycleOwner) {
            Toast.makeText(requireActivity(), "Signed out", Toast.LENGTH_SHORT).show()
            val action = HomeFragmentDirections.actionHomeFragmentToSignUpFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
        viewModel.userDeleted.observe(viewLifecycleOwner) {
            Toast.makeText(requireActivity(), "User deleted", Toast.LENGTH_SHORT).show()
            val action = HomeFragmentDirections.actionHomeFragmentToSignUpFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }
}