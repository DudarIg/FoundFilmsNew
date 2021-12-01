package ru.dudar.findfilms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.core.view.isVisible
import ru.dudar.findfilms.R
import ru.dudar.findfilms.databinding.FragmentSettingsBinding
import ru.dudar.findfilms.domain.Disable
import android.content.Intent
import android.provider.Settings


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)
        val disable = context as Disable
        disable.onDisableButton(false, R.id.as_settings)

        binding.zeroTextView.isVisible = false
        binding.explainTextView.isVisible = false
        binding.allowedTextView.isVisible = false
        binding.locateButton.isVisible = false
        val explain = shouldShowRequestPermissionRationale("android.permission.ACCESS_COARSE_LOCATION")
        val permisResultCoarse = checkSelfPermission(requireContext(),
            "android.permission.ACCESS_COARSE_LOCATION")
        val permisResultFine = checkSelfPermission(requireContext(),
            "android.permission.ACCESS_FINE_LOCATION")

        if (permisResultCoarse != PermissionChecker.PERMISSION_GRANTED ||
                permisResultFine != PermissionChecker.PERMISSION_GRANTED) {
            binding.zeroTextView.isVisible = true
            if (explain)
                binding.explainTextView.isVisible = true

            binding.locateButton.isVisible = true
            binding.locateButton.setOnClickListener{
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            explain()
        }

    }

    fun explain() {
        binding.allowedTextView.isVisible = true
    }

    override fun onStop() {
        super.onStop()
        val disable = context as Disable
        disable.onDisableButton(true, R.id.as_settings)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}