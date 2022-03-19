package com.mohamedtaha.imagine.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.databinding.ActivityContianerDescriptionElnawawyBinding
import com.mohamedtaha.imagine.helper.HelperClass
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel
import com.mohamedtaha.imagine.ui.fragments.DescriptionElarbaoonFragment
import com.mohamedtaha.imagine.ui.navigationview.ElarbaoonElnawawyActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ContianerDescriptionElnawawyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContianerDescriptionElnawawyBinding
    private var position_elhadeth: ElarbaoonElnawawyModel? = null

    @Inject
    lateinit var bundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContianerDescriptionElnawawyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        if (intent != null) {
            position_elhadeth = Gson().fromJson(
                intent.getStringExtra(ElarbaoonElnawawyActivity.POSITION),
                ElarbaoonElnawawyModel::class.java
            )
        }
        val descriptionElarbaoonFragment = DescriptionElarbaoonFragment()
        bundle.putString(ElarbaoonElnawawyActivity.POSITION, Gson().toJson(position_elhadeth))
        descriptionElarbaoonFragment.arguments = bundle
        HelperClass.replece(
            descriptionElarbaoonFragment,
            supportFragmentManager,
            R.id.Cycle_Elarbaoon_Elnawawy_contener
        )
    }

}