package com.example.xinzhang.tdstest.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xinzhang.tdstest.R
import com.example.xinzhang.tdstest.databinding.FragmentEmergencyControlBinding
import com.example.xinzhang.tdstest.viewModel.EmergencyControlViewModel

class EmergencyControlFragment : Fragment() {

    private var binding: FragmentEmergencyControlBinding? = null
    private val viewModel: EmergencyControlViewModel = EmergencyControlViewModel()



    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emergency_control, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.controlViewModel = viewModel
        viewModel.initEmergencyCondition()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDispose()
    }
}
