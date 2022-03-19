package com.mohamedtaha.imagine.ui.activities

import android.app.NotificationManager
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.viewpager.widget.ViewPager
import butterknife.OnClick
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.adapter.AdapterForAzkarSwipe
import com.mohamedtaha.imagine.adapter.AdapterForSwipe
import com.mohamedtaha.imagine.databinding.ActivitySwipePagesBinding
import com.mohamedtaha.imagine.datastore.DataStoreViewModel
import com.mohamedtaha.imagine.helper.HelperClass
import com.mohamedtaha.imagine.helper.SharedPerefrenceHelper
import com.mohamedtaha.imagine.helper.ShowDialog
import com.mohamedtaha.imagine.helper.images
import com.mohamedtaha.imagine.helper.images.SAVE_POSITION
import com.mohamedtaha.imagine.mvp.model.ModelAzkar
import com.mohamedtaha.imagine.notification.morningAzkar.MorningAzkarAlarmReceiver
import com.mohamedtaha.imagine.notification.quran.AlarmReceiver
import com.mohamedtaha.imagine.ui.home.activity.NavigationDrawaberActivity
import com.mohamedtaha.imagine.ui.home.fragment.AzkarFragment
import com.mohamedtaha.imagine.ui.home.fragment.SwarFragment
import com.mohamedtaha.imagine.ui.home.viewModel.SwarAndPartsViewModel
import com.mohamedtaha.imagine.util.ClickListener
import com.mohamedtaha.imagine.util.DialogUtils
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SwipePagesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySwipePagesBinding
    private val viewModel: SwarAndPartsViewModel by viewModels()
    private val dataStoreViewModel by viewModels<DataStoreViewModel>()
    var images: List<Int>? = null
    var imagesNotification: ArrayList<Int>? = null
    var imagesFirst: ArrayList<Int>? = null
    private lateinit var modelAzkarList: List<ModelAzkar>

    private var save_position = 0
    private var save_position_azkar = 0
    private var position = 1
    private var position_azkar = 0

    @Inject
    lateinit var bundle: Bundle
    var notificationId = -1
    var notificationIdMorningAzkar = -1
    private var language_name: String? = null
    private val screenOn = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwipePagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.addImagesList()



        bundle = intent?.extras!!
        //to Check before change Language
        language_name = Locale.getDefault().language
        if (language_name != "ar") {
            HelperClass.change_language("ar", this)
        }
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        getScreenOn(screenOn)
        //for close Notification
        notificationId = intent.getIntExtra(AlarmReceiver.NOTIFICATION_ID, -1)
        notificationIdMorningAzkar =
            intent.getIntExtra(MorningAzkarAlarmReceiver.NOTIFICATION_ID_NUMBER_AZKAR, -1)
        val timeSend = intent.getIntExtra(AlarmReceiver.TIME_SEND, -1)
        val timeSendMorningAzkar =
            intent.getIntExtra(MorningAzkarAlarmReceiver.TIME_SEND_MORNING_AZKAR, -1)
        if (notificationId >= 0) {
            argemnetsNotification
            createImageNotification()
         //   val adapterForSwipe = AdapterForSwipe(imagesNotification)
          //  binding.SwipePagesActivityVP.setAdapter(adapterForSwipe);
          //  binding.SwipePagesActivityVP.setCurrentItem(notificationId);
            Log.d("TAG", " $notificationId")
            notificationManager.cancel(timeSend)
        } else if (notificationIdMorningAzkar >= 0) {
            argemnetsForNotificationAzkar
            val adapterForAzkarSwipe = AdapterForAzkarSwipe(this, modelAzkarList);
            if (notificationIdMorningAzkar == 27) {
                binding.SwipePagesActivityVP.setAdapter(adapterForAzkarSwipe);
                binding.SwipePagesActivityVP.setCurrentItem(notificationIdMorningAzkar);
            } else {
                binding.SwipePagesActivityVP.setAdapter(adapterForAzkarSwipe);
                binding.SwipePagesActivityVP.setCurrentItem(notificationIdMorningAzkar);
            }
            notificationManager.cancel(timeSendMorningAzkar)
        } else if (bundle.getBoolean(SwarFragment.SAVE_STATE)) {
            position = bundle.getInt(SAVE_POSITION)
            viewModel.allImages.observe(this) {
                images = it
                createImage()
                declareAdapter()
                observeViewPager()
                observeSaveData()

            }
        } else if (bundle.getBoolean(NavigationDrawaberActivity.SAVE_PAGE)) {
            getImagesFirst()
            createImagesFirst()
            val adapterForSwipe = AdapterForSwipe(
                imagesFirst!!,object: ClickListener<Int>{
                    override fun onClick(view: View?, position: Int) {
                        Toast.makeText(this@SwipePagesActivity,"Click",Toast.LENGTH_LONG).show()
                    }

                }
            )
//            { positon: Int ->
//                ShowDialog.showDialog(
//                    this@SwipePagesActivity,
//                    save_position,
//                    getString(R.string.save_position)
//                )
//            }
            //            SwipePagesActivityVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    save_position = position;
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//                }
//            });
//            SwipePagesActivityVP.setAdapter(adapterForSwipe);
//            int position = SharedPerefrenceHelper.getInt(getApplicationContext(), SAVE_IMAGES, 0);
//            SwipePagesActivityVP.setCurrentItem(position);
        } else {
            getAzkarList()
            val adapterForAzkarSwipe = AdapterForAzkarSwipe(
                this,
                modelAzkarList,
                object : AdapterForAzkarSwipe.showDetail {
                    override fun showDetails(modelAzkar: ModelAzkar?) {
                        // ShowDialog.showDialogAzkar(reques, save_position_azkar, getString(R.string.save_position_alzekr), modelAzkar)
                    }

                })

            binding.SwipePagesActivityVP.addOnPageChangeListener(object :
                ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {
                    save_position_azkar = position;

                }

                override fun onPageScrollStateChanged(state: Int) {
                }

            });
            binding.SwipePagesActivityVP.setAdapter(adapterForAzkarSwipe);
            if (SharedPerefrenceHelper.getBooleanForAzkar(this, IS_TRUE_AZKAR, false)) {
                ShowDialog.showDialogForRetrieveAzkar(
                    this,
                    binding.SwipePagesActivityVP,
                    position_azkar
                );
            } else {
                binding.SwipePagesActivityVP.setCurrentItem(position_azkar);
            }
        }
    }

    private fun declareAdapter() {
        val adapterForSwipe = AdapterForSwipe(
            images!!,object :ClickListener<Int>{
                override fun onClick(view: View?, position: Int) {
                    DialogUtils.showDialog(this@SwipePagesActivity,getString(R.string.save_position),object:View.OnClickListener{
                        override fun onClick(p0: View?) {
                            dataStoreViewModel.deleteReadingQuran()
                            dataStoreViewModel.saveReadingQuran(save_position)
                            finish()
                        }
                    })
                }
            }
        )
        binding.SwipePagesActivityVP.adapter = adapterForSwipe
    }

    private fun observeViewPager() {
        binding.SwipePagesActivityVP.addOnPageChangeListener(object :
            ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                save_position = position
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    private fun observeSaveData() {
        dataStoreViewModel.getReadingQuran.asLiveData().observe(this){
            if (it >=0 ){
                DialogUtils.showDialogForRetrieveReading(this,getString(R.string.app_name),getString(R.string.do_want_Save),
                    { _, _ ->
                        binding.SwipePagesActivityVP.currentItem = it
                    }
                ) { _, _ ->
                    dataStoreViewModel.deleteReadingQuran()
                    binding.SwipePagesActivityVP.currentItem = position
                }
            }else
                binding.SwipePagesActivityVP.currentItem = position
        }

    }

    private fun getScreenOn(isScreenOn: Boolean) {
        if (isScreenOn) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }


    private fun createImage() {
        for (i in images!!.indices) {
            bundle!!.putInt(SwarFragment.SAVE_IMAGES, images!![i])

            bundle!!.putInt(SAVE_POSITION, position)
        }
        binding.SwipePagesActivityPB.visibility = View.GONE
    }

    private val argemnetsNotification: Unit
        private get() {
            if (bundle != null) {
                imagesNotification =
                    bundle!!.getIntegerArrayList(AlarmReceiver.SAVE_Position_Notification)
                Log.d("TAG", " $imagesNotification")
            }
        }

    private fun createImageNotification() {
        if (imagesNotification != null) {
            for (i in imagesNotification!!.indices) {
                bundle!!.putInt(AlarmReceiver.SAVE_Position_Notification, imagesNotification!![i])
            }
            Log.d("TAG", " $imagesNotification")
            binding.SwipePagesActivityPB.visibility = View.GONE
        }
    }

    private val azkarNotification: Unit
        private get() {
            if (bundle != null) {
                imagesNotification =
                    bundle!!.getIntegerArrayList(AlarmReceiver.SAVE_Position_Notification)
                Log.d("TAG", " $imagesNotification")
            }
        }

    private fun createAzkarNotification() {
        if (imagesNotification != null) {
            for (i in imagesNotification!!.indices) {
                bundle!!.putInt(AlarmReceiver.SAVE_Position_Notification, imagesNotification!![i])
            }
            Log.d("TAG", " $imagesNotification")
            binding.SwipePagesActivityPB.visibility = View.GONE
        }
    }

    private fun getImagesFirst() {
        if (bundle != null) {
            imagesFirst = bundle!!.getIntegerArrayList(NavigationDrawaberActivity.SAVE_ALL_IMAGES)
        }
    }

    private fun createImagesFirst() {
        if (imagesFirst != null) {
            for (i in imagesFirst!!.indices) {
                bundle!!.putInt(NavigationDrawaberActivity.SAVE_ALL_IMAGES, imagesFirst!![i])
            }
            binding.SwipePagesActivityPB.visibility = View.GONE
        }
    }

    // modelAzkarList =  Gson().fromJson(st, listType);
    fun getAzkarList() {
        if (bundle != null) {
            val listType = object : TypeToken<List<ModelAzkar?>?>() {}.type
            val st = bundle!!.getString(AzkarFragment.SAVE_AZKAR)
            modelAzkarList = Gson().fromJson(st, listType);
            position_azkar = bundle!!.getInt(AzkarFragment.SAVE_POTION_AZKAR)
        }
        binding.SwipePagesActivityPB.visibility = View.GONE
    }

    //    modelAzkarList = Gson().fromJson(st, listType);
    private val argemnetsForNotificationAzkar: Unit
        private get() {
            if (bundle != null) {
                val listType = object : TypeToken<List<ModelAzkar?>?>() {}.type
                val st = bundle!!.getString(MorningAzkarAlarmReceiver.SAVE_POSITION_MORNING_AZKAR)
                modelAzkarList = Gson().fromJson(st, listType);
                position_azkar =
                    bundle!!.getInt(MorningAzkarAlarmReceiver.NOTIFICATION_ID_NUMBER_AZKAR)
            }
            binding.SwipePagesActivityPB.visibility = View.GONE
        }

    override fun onSaveInstanceState(outState: Bundle) {
        SharedPerefrenceHelper.putInt(this, SwarFragment.SAVE_IMAGES, save_position)
        SharedPerefrenceHelper.putInt(this, AzkarFragment.SAVE_AZKAR, save_position_azkar)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        save_position = savedInstanceState.getInt(SwarFragment.SAVE_IMAGES)
        save_position_azkar = savedInstanceState.getInt(AzkarFragment.SAVE_AZKAR)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.item_anim_no_thing, R.anim.item_anim_slide_from_bottom)
    }

    companion object {
        const val IS_TRUE = "is_true"
        const val IS_TRUE_AZKAR = "is_true_azkar"
    }
}