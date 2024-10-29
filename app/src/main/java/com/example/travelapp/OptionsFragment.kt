package com.example.travelapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.travelapp.databinding.FragmentOptionsBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OptionsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentOptionsBinding? = null
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
        _binding = FragmentOptionsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val jenisTicket = resources.getStringArray(R.array.jenis_ticket)
            val adapterJenisTicket = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, jenisTicket)
            adapterJenisTicket.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            spinnerTickets.adapter = adapterJenisTicket

            btnBuy.setOnClickListener {
                val tipeTicket = spinnerTickets.selectedItem?.toString()

                if (tipeTicket.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Pilih tipe tiket", Toast.LENGTH_SHORT).show()
                } else {
                    val currentDateTime = java.text.SimpleDateFormat("dd-MM-yyyy hh:mm:ss", java.util.Locale.getDefault()).format(java.util.Date())
                    Toast.makeText(requireContext(), "Tiket dengan tipe $tipeTicket berhasil dipesan pada $currentDateTime", Toast.LENGTH_SHORT).show()
                    findNavController().apply {
                        previousBackStackEntry?.savedStateHandle?.set("jenis ticket", spinnerTickets.selectedItem.toString())
                    }.navigateUp()
                }
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OptionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}