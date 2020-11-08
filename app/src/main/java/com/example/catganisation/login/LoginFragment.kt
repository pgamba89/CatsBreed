package com.example.catganisation.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.findNavController
import com.example.catganisation.R
import com.example.catganisation.databinding.FragmentLoginBinding
import com.example.catganisation.model.SignInBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var modelView: LoginModelView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        modelView = ViewModelProvider(this).get(LoginModelView::class.java)
        binding.viewModel = modelView
        binding.lifecycleOwner

        binding.Login.setOnClickListener {
            if (TextUtils.isEmpty(binding.editTextUser.text) || TextUtils.isEmpty(binding.editTextPassword.text)) {
                Toast.makeText(activity, R.string.empty_not_login, Toast.LENGTH_LONG).show()
            } else {
                val email = binding.editTextUser.text.toString()
                val password = binding.editTextPassword.text.toString()
                val signInBody = SignInBody(email, password)

                lifecycleScope.launch(Dispatchers.Default) {
                    modelView.signIn(signInBody)
                }

            }
        }

        modelView.loginResponse.observe(viewLifecycleOwner, {
            Toast.makeText(activity, "LoginOk", Toast.LENGTH_LONG).show()
            view?.findNavController()
                ?.navigate(LoginFragmentDirections.actionLoginFragmentToCatsListFragment())
        })

        return binding.root
    }
}