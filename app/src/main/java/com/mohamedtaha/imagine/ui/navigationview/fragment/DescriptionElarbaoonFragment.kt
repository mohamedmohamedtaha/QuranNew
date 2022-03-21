package com.mohamedtaha.imagine.ui.navigationview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.mohamedtaha.imagine.adapter.PagerAdapterElarbaoonElnawawy
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentDescriptionElarbaoon2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescriptionElarbaoonFragment : BaseFragment() {
    private lateinit var binding: FragmentDescriptionElarbaoon2Binding

    //    @Inject
//    lateinit var bundle: Bundle
//    private var position_elhadeth: ElarbaoonElnawawyModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDescriptionElarbaoon2Binding.inflate(inflater, container, false)
        deleteToolbar()
        val safeArgs: DescriptionElarbaoonFragmentArgs by navArgs()
        val elarbaoonElnawawy = safeArgs.elarbaoonElnawawyModel
        binding.DescriptionElarbaoonFragmentTVNumberElhadeth.text =
            elarbaoonElnawawy.numberElhadeth + "/"
        binding.DescriptionElarbaoonFragmentTVNameElhadeth.text =
            elarbaoonElnawawy.nameElhadeth
        val pagerAdapterElarbaoonElnawawy = PagerAdapterElarbaoonElnawawy(
            activity, childFragmentManager, elarbaoonElnawawy.position
        )


//    position_elhadeth = Gson().fromJson(
//                bundle.getString(ElarbaoonElnawawyActivity.POSITION),
//                ElarbaoonElnawawyModel::class.java
//            )
//            binding.DescriptionElarbaoonFragmentTVNumberElhadeth.text =
//                position_elhadeth!!.numberElhadeth + "/"
//            binding.DescriptionElarbaoonFragmentTVNameElhadeth.text =
//                position_elhadeth!!.nameElhadeth
//            val pagerAdapterElarbaoonElnawawy = PagerAdapterElarbaoonElnawawy(
//                activity, childFragmentManager,
//                position_elhadeth!!.position
//            )
        binding.DescriptionElarbaoonFragmentViewPager.setAdapter(pagerAdapterElarbaoonElnawawy)
        binding.DescriptionElarbaoonFragmentViewPager.setCurrentItem(2)
        binding.DescriptionElarbaoonFragmentTabLayout.setupWithViewPager(
            binding.DescriptionElarbaoonFragmentViewPager
        )
        return binding.root
    }

}