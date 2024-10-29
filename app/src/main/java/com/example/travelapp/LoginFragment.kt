package com.example.travelapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.travelapp.databinding.FragmentLoginBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener("registerData", this) { _, bundle ->
            val name = bundle.getString("name")
            val nim = bundle.getString("nim")
            val email = bundle.getString("email")
            val password = bundle.getString("password")

            with(binding) {
                btnLogin.setOnClickListener {
                    val emailEntered = fieldEmail.text.toString()
                    val passwordEntered = fieldPassword.text.toString()
                    if (emailEntered.isEmpty() || passwordEntered.isEmpty()) {
                        Toast.makeText(context, "All fields must be filled", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    if (emailEntered == email && passwordEntered == password) {
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()

                        val intent = Intent(activity, MainActivity::class.java).apply {
                            putExtra("name", name)
                            putExtra("nim", nim)
                        }
                        startActivity(intent)
                        activity?.finish()
                    } else {
                        Toast.makeText(context, "Incorrect email or password", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
