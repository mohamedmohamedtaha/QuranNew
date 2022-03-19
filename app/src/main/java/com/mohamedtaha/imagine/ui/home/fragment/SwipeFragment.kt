package com.mohamedtaha.imagine.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.ActivitySwipePagesBinding
import com.mohamedtaha.imagine.databinding.FragmentSwipeBinding
import com.mohamedtaha.imagine.mvp.model.ModelAzkar
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class SwipeFragment : BaseFragment() {
    private lateinit var binding:FragmentSwipeBinding
    var images: ArrayList<Int>? = null
    var imagesNotification: ArrayList<Int>? = null
    var imagesFirst: ArrayList<Int>? = null

    private lateinit var modelAzkarList: List<ModelAzkar>

    private var save_position = 0
    private var save_position_azkar = 0
    private var position = 1
    private var position_azkar = 0
    private var bundle: Bundle? = null
    var notificationId = -1
    var notification_id_morning_azkar = -1
    private var language_name: String? = null
    private val screenOn = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSwipeBinding.inflate(inflater,container,false)
        return binding.root
    }

}