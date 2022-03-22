package com.mohamedtaha.imagine.ui.navigationview.fragment.elnawawy

import butterknife.BindView
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.libraryTextView.TextViewEx
import android.widget.ProgressBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import butterknife.ButterKnife
import com.google.gson.Gson
import kotlin.Throws
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.youtube.player.internal.s
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentTranslateBinding
import com.mohamedtaha.imagine.datastore.Session.POSITION
import com.mohamedtaha.imagine.ui.navigationview.viewmodel.ElarbaoonElnawawyVieWModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class TranslateElhadethFragment : BaseFragment() {
    private lateinit var binding: FragmentTranslateBinding
    private val elarbaoonElnawawyVieWModel :ElarbaoonElnawawyVieWModel by viewModels()

@Inject
lateinit var bundle: Bundle
    private var position_elhadeth = 0
    private var disposable: CompositeDisposable? = null
    private var return_translate_elhadeth: String? = null

    private var translate_elhadeth_depended_on_position: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        fragmentTranslateBinding = FragmentTranslateBinding.inflate(inflater);
//        View view = fragmentTranslateBinding.getRoot();
        binding = FragmentTranslateBinding.inflate(inflater,container,false)
        bundle = requireArguments()
        if (bundle != null) {
            position_elhadeth = Gson().fromJson(
                bundle.getString(POSITION),
                Int::class.java
            )
        }


        elarbaoonElnawawyVieWModel.gettranslate(requireContext(),position_elhadeth)
        elarbaoonElnawawyVieWModel.translateElhadeth.observe(viewLifecycleOwner){
            translate_elhadeth_depended_on_position = it
            binding.TranslateElhadethFragmentTVTranslateElhaseth.text =
                translate_elhadeth_depended_on_position
            binding.TranslateElhadethFragmentTVTranslateElhaseth.movementMethod =
                ScrollingMovementMethod()
        }
        return binding.root
    }
}