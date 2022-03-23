package com.mohamedtaha.imagine.ui.activities

import android.Manifest
import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.Dialog
import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.PorterDuff
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import butterknife.OnClick
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.adapter.RecycleViewReaderAdapter
import com.mohamedtaha.imagine.adapter.RecycleViewReaderAdapter.DownloadMusic
import com.mohamedtaha.imagine.databinding.FragmentListSoundBinding
import com.mohamedtaha.imagine.helper.HelperClass
import com.mohamedtaha.imagine.helper.Utilities
import com.mohamedtaha.imagine.helper.checkConnection.NetworkConnection
import com.mohamedtaha.imagine.helper.checkConnection.NoInternetConnection
import com.mohamedtaha.imagine.helper.util.PlayerConstants
import com.mohamedtaha.imagine.helper.util.StorageUtil
import com.mohamedtaha.imagine.mvp.model.ImageModel
import com.mohamedtaha.imagine.receiver.ConnectivityReceiver
import com.mohamedtaha.imagine.receiver.DownloadReceiver
import com.mohamedtaha.imagine.receiver.NoInternetReceiver
import com.mohamedtaha.imagine.service.MediaPlayerService
import com.mohamedtaha.imagine.ui.home.adapter.ImageAdapter
import com.mohamedtaha.imagine.ui.home.viewModel.SoundViewModel
import com.mohamedtaha.imagine.util.ClickListener
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class ListSoundReader : AppCompatActivity() {
    private lateinit var binding: FragmentListSoundBinding
    private var respones: ArrayList<ImageModel>? = null
    var utilities: Utilities? = null
    @Inject
    lateinit var intentSound: Intent
    var music_uri: Uri? = null
    private var Music_DownloadId: Long = 0
    var downloadManager: DownloadManager? = null
    private var urlLink: String? = null
    private var name_sora: String? = null
    var mediaStorageDir: File? = null
    var media_path: File? = null
    private val nameSoraViewModel: SoundViewModel by viewModels()
    var totalDuration = 0
    var poisition = 0
    var position_seekBar = 0
    var imageModelTest: String? = null
    var playeIntent: Intent? = null
    private var connectivityReceiver: ConnectivityReceiver? = null
    private var noInternetReceiver: NoInternetReceiver? = null
    private var downloadReceiver: DownloadReceiver? = null
    var downloadReference: Long = 0
    var dialog: Dialog? = null

    override fun onDestroy() {
        super.onDestroy()
        if (connectivityReceiver != null) {
            unregisterReceiver(connectivityReceiver)
        }
        if (noInternetReceiver != null) {
            unregisterReceiver(noInternetReceiver)
        }
        if (downloadReceiver != null) {
            unregisterReceiver(downloadReceiver)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        binding = FragmentListSoundBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Create a network change broadcast receiver.
        connectivityReceiver = ConnectivityReceiver()
        noInternetReceiver = NoInternetReceiver()
        utilities = Utilities()
        isServiceRunning = Utilities.isServiceRunning(
            MediaPlayerService::class.java.name, applicationContext
        )
        FragmentListSoundLLControlMedia = findViewById(R.id.Fragment_List_Sound_LL_Control_Media)
        ListSoundReaderLoadingIndicator = findViewById(R.id.ListSoundReader_loading_indicator)
        imageViewAlbumArt = findViewById(R.id.imageViewAlbumArt)
        btnPlay = findViewById(R.id.btnPlay)
        btnPause = findViewById(R.id.btnPause)
        dialog = Dialog(this)

        //  broadCastReceverDownload();
        if (!isServiceRunning) {
            binding.FragmentListSoundLLControlMedia.setVisibility(View.GONE)
        }
        playeIntent = Intent(this@ListSoundReader, MediaPlayerService::class.java)
        intentSound = getIntent()
        textBufferDuration = findViewById(R.id.textBufferDuration)
        textDuration = findViewById(R.id.textDuration)
        textNowPlaying = findViewById(R.id.textNowPlaying)
        //Fragment_List_Sound_Search_View = findViewById(R.id.Fragment_List_Sound_Search_View);
        binding.progressBar.progressDrawable.setColorFilter(
            resources.getColor(R.color.colorAccent),
            PorterDuff.Mode.SRC_IN
        )
        custom_toolbar()
        respones = ArrayList()
        val bundle = getIntent()
        if (bundle != null) {
            poisition =
                Gson().fromJson(bundle.getStringExtra(ImageAdapter.SHEKH_ID), Int::class.java)
            imageModelTest =
                Gson().fromJson(bundle.getStringExtra(ImageAdapter.SHEKH_NAME), String::class.java)
        }
        FILENAME = "/$imageModelTest/"
        binding.FragmentListSoundTVNameSora.text = imageModelTest
        //For start position from right don't left
        val linearLayoutManager: GridLayoutManager = object : GridLayoutManager(
            applicationContext, 2
        ) {
            override fun isLayoutRTL(): Boolean {
                return true
            }
        }
        //For put Space for RecyclerView
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycleViewSound.getContext(),
//                linearLayoutManager.getOrientation());
//        recycleViewSound.addItemDecoration(dividerItemDecoration);
        binding.recycleViewSound.layoutManager = linearLayoutManager

        nameSoraViewModel.getAllNameSora(poisition)

        nameSoraViewModel.getAllNameSore.observe(this) {
            respones = it
            val recycleViewAdaptor = RecycleViewReaderAdapter(
                object : ClickListener<Int> {

                    override fun onClick(view: View?, position: Int) {
                        playAudio(position)
                    }
                },
                object : ClickListener<Int> {
                    override fun onClick(view: View?, position: Int) {
                            if (!respones.isNullOrEmpty()) {
                                urlLink = respones!![position].soraLink
                                name_sora = respones!![position].nameSora
                                checkPermistion()
                            }
                    }

                })
            recycleViewAdaptor.submitList(it)
            //For Animation
            val controller = AnimationUtils.loadLayoutAnimation(
                applicationContext, R.anim.layout_fall_dwon
            )
            binding.recycleViewSound.layoutAnimation = controller
            binding.recycleViewSound.adapter = recycleViewAdaptor
            binding.recycleViewSound.scheduleLayoutAnimation()
        }

        //        Fragment_List_Sound_Search_View.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//                recycleViewAdaptor = new RecycleViewReaderAdapter(getApplicationContext(), respones, new RecycleViewReaderAdapter.ClickListener() {
//                    @Override
//                    public void onClick(View view, int position) {
//                        playAudio(position);
//                    }
//                }, new RecycleViewReaderAdapter.DownloadMusic() {
//                    @Override
//                    public void download(int position) {
//                        urlLink = respones.get(position).getSora_link();
//                        name_sora = respones.get(position).getName_sora();
//                        checkPermistion();
//                    }
//                });
//                recycleViewSound.setAdapter(recycleViewAdaptor);
//                FragmentListSoundTVNoData.setVisibility(View.GONE);
//                recycleViewSound.setVisibility(View.VISIBLE);
//            }
//        });
//
//        Fragment_List_Sound_Search_View.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (newText != null && !newText.isEmpty()) {
//                    List<ImageModel> stringList = new ArrayList<>();
//                    for (ImageModel item : respones) {
//                        if (item.getName_sora().contains(newText)) {
//                            stringList.add(item);
//                        }
//                    }
//                    if (!stringList.isEmpty()) {
//                        recycleViewAdaptor = new RecycleViewReaderAdapter(getApplicationContext(), stringList, new RecycleViewReaderAdapter.ClickListener() {
//                            @Override
//                            public void onClick(View view, int position) {
//                                playAudio(stringList.get(position).getPosition());
//                            }
//                        }, new RecycleViewReaderAdapter.DownloadMusic() {
//                            @Override
//                            public void download(int position) {
//                                urlLink = stringList.get(position).getSora_link();
//                                name_sora = stringList.get(position).getName_sora();
//                                checkPermistion();
//                            }
//                        });
//                        recycleViewSound.setAdapter(recycleViewAdaptor);
//                        FragmentListSoundTVNoData.setVisibility(View.GONE);
//                        recycleViewSound.setVisibility(View.VISIBLE);
//                    } else {
//                        FragmentListSoundTVNoData.setVisibility(View.VISIBLE);
//                        recycleViewSound.setVisibility(View.GONE);
//                    }
//                } else {
//                    //If search is Null return default
//                    FragmentListSoundTVNoData.setVisibility(View.GONE);
//                    recycleViewSound.setVisibility(View.VISIBLE);
//                    recycleViewAdaptor = new RecycleViewReaderAdapter(getApplicationContext(), respones, new RecycleViewReaderAdapter.ClickListener() {
//                        @Override
//                        public void onClick(View view, int position) {
//                            playAudio(position);
//                        }
//                    }, new RecycleViewReaderAdapter.DownloadMusic() {
//                        @Override
//                        public void download(int position) {
//                            urlLink = respones.get(position).getSora_link();
//                            name_sora = respones.get(position).getName_sora();
//                            checkPermistion();
//                        }
//                    });
//                    recycleViewSound.setAdapter(recycleViewAdaptor);
//                }
//                return false;
//            }
//        });
    }

    private fun downloadSora() {
        //check Internet
        val noInternetConnection = NoInternetConnection()
        noInternetConnection.execute("http://clients3.google.com/generate_204")
        val isConnected = NetworkConnection.networkConnectivity(applicationContext)
        ListSoundReaderLoadingIndicator!!.visibility = View.VISIBLE
        Handler().postDelayed(object : Runnable {
            override fun run() {
                if (!isConnected) {
                    //send BroadcastReceiver to the Service -> Not Connection
                    val broadcastIntent = Intent(MediaPlayerService.BROADCAST_NOT_CONNECTION)
                    sendBroadcast(broadcastIntent)
                    ListSoundReaderLoadingIndicator!!.visibility = View.GONE
                } else {
                    if (!NoInternetConnection.isInternet()) {
                        ListSoundReaderLoadingIndicator!!.visibility = View.GONE
                        //send BroadcastReceiver to the Service -> Not Internet
                    } else {
                        ListSoundReaderLoadingIndicator!!.visibility = View.GONE
                        dialog!!.setCancelable(false)
                        dialog!!.setContentView(R.layout.custom_show_dialog)
                        val textView = dialog!!.findViewById<View>(R.id.show_text) as TextView
                        textView.text =
                            resources.getString(R.string.do_want_Save_sound) + " " + name_sora
                        val yesButton = dialog!!.findViewById<Button>(R.id.BT_Yes)
                        val noButton = dialog!!.findViewById<Button>(R.id.BT_No)
                        yesButton.setOnClickListener(object : View.OnClickListener {
                            override fun onClick(v: View) {
                                music_uri = Uri.parse(urlLink)
                                Music_DownloadId = DownloadData(music_uri, name_sora)
                                dialog!!.dismiss()
                            }
                        })
                        noButton.setOnClickListener(object : View.OnClickListener {
                            override fun onClick(v: View) {
                                dialog!!.dismiss()
                            }
                        })
                        dialog!!.show()
                    }
                }
            }
        }, 1000)
    }

    private fun download() {
        val progressBarDialog = ProgressDialog(this)
        progressBarDialog.setTitle("Download App Data, Please Wait")
        progressBarDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        progressBarDialog.setButton(
            DialogInterface.BUTTON_POSITIVE,
            "OK",
            object : DialogInterface.OnClickListener {
                override fun onClick(
                    dialog: DialogInterface,
                    whichButton: Int
                ) {
                    // Toast.makeText(getBaseContext(),
                    //       "OK clicked!", Toast.LENGTH_SHORT).show();
                }
            })
        progressBarDialog.progress = 0
        Thread(object : Runnable {
            override fun run() {
                val downloading = true
                val manager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                while (downloading) {
                    val q = DownloadManager.Query()
                    q.setFilterById(Music_DownloadId) //filter by id which you have receieved when reqesting download from download manager
                    val cursor = manager.query(q)
                    cursor.moveToFirst()
                    //                    int bytes_downloaded = cursor.getInt(cursor
//                            .getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
//                    int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
//
//                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
//                        downloading = false;
//                    }

                    //   final int dl_progress = (int) ((bytes_downloaded * 100l) / bytes_total);
                    runOnUiThread(object : Runnable {
                        override fun run() {

                            //  progressBarDialog.setProgress((int) dl_progress);
                        }
                    })
                    cursor.close()
                }
            }
        }).start()


        //show the dialog
        progressBarDialog.show()
    }

    private fun registerNoConnection() {
        //Register no internet receiver
        val filter = IntentFilter(MediaPlayerService.BROADCAST_NOT_CONNECTION)
        registerReceiver(connectivityReceiver, filter)
    }

    private fun registerDownloadSound() {
        //Register no internet receiver
        val intentFilter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        registerReceiver(downloadReceiver, intentFilter)
    }

    private fun registerNoInternet() {
        //Register no internet receiver
        val filter = IntentFilter(MediaPlayerService.BROADCAST_NOT_INTERNET)
        registerReceiver(noInternetReceiver, filter)
    }

    fun custom_toolbar() {
        setSupportActionBar(binding.FragmentListSoundTB)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        //for delete label for Activity
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    private fun playAudio(audioIndex: Int) {
        //  //Check is service is active
        val storageUtil = StorageUtil(applicationContext)
        storageUtil.storeAudio(respones)
        storageUtil.storeAudioIndex(audioIndex)
        if (!isServiceRunning) {
            //Store Serializable audioList to SharedPreferences
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ContextCompat.startForegroundService(applicationContext, (playeIntent)!!)
            } else {
                startService(playeIntent)
            }
            isServiceRunning = true
        } else {
            //Service is active
            //send BroadcastReceiver to the Service -> PLAY_NEW_AUDIO
            val broadcastIntent = Intent(Broadcast_PLAY_NEW_AUDIO)
            sendBroadcast(broadcastIntent)
        }
    }

    private fun DownloadData(uri: Uri?, name_sora: String?): Long {
        downloadReference = 0
        //Create Requect for android download manager
        downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(uri)
        //Setting title of request
        request.setTitle(FILENAME + name_sora)
        //Setting description of request
        request.setDescription(getString(R.string.setDescriptionRequet))
        //Setting Show Notification After downloaded
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        //Set the local destination for the download file to a path within the application's external files directory
        media_path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + FILENAME)

        //check download folder for the App private
        media_path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + FILENAME)
        //check download folder Global
        mediaStorageDir = File(media_path, name_sora + getString(R.string.mp3))
        if (mediaStorageDir != null && mediaStorageDir!!.exists()) {
            HelperClass.customToast(this, getString(R.string.send_problem_string))
        } else {
            request.setVisibleInDownloadsUi(true)
            request.setDestinationInExternalFilesDir(
                this@ListSoundReader,
                Environment.DIRECTORY_DOWNLOADS + FILENAME,
                name_sora + getString(R.string.mp3)
            )
            //Enqueue download and save the referenceId
            downloadReference = downloadManager!!.enqueue(request)
            HelperClass.customToast(this, getString(R.string.download_sound))
            //Listen for Download Sound
            downloadReceiver = DownloadReceiver(downloadReference, this)
            registerDownloadSound()
        }
        return downloadReference
    }

    //    private int getDownloadStatus(){
    //        DownloadManager.Query query = new DownloadManager.Query();
    //        query.setFilterById(downloadReference);
    //
    //        Cursor cursor = downloadManager.query(query);
    //        if (cursor.moveToFirst()){
    //            int columIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
    //            int status = cursor.getInt(columIndex);
    //            return status;
    //
    //        }
    //        return DownloadManager.ERROR_UNKNOWN;
    //    }
    //    private void broadCastReceverDownload(){
    //        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
    //        registerReceiver(new BroadcastReceiver() {
    //            @Override
    //            public void onReceive(Context context, Intent intent) {
    //                Long  broadCastDownload = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
    //                if (broadCastDownload == downloadReference){
    //                    if (getDownloadStatus() == DownloadManager.STATUS_SUCCESSFUL){
    //                        HelperClass.customToast((Activity) context, getString(R.string.download_successful));
    //                    }else {
    //                        HelperClass.customToast((Activity) context, getString(R.string.download_field));
    //
    //                    }
    //
    //                }
    //
    //            }
    //        },intentFilter);
    //    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val SearchItem = menu.findItem(R.id.action_search)
        // Fragment_List_Sound_Search_View.setMenuItem(SearchItem);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
            return true
        }
        return false
    }

    override fun onBackPressed() {
//        if (Fragment_List_Sound_Search_View.isSearchOpen()) {
//            Fragment_List_Sound_Search_View.closeSearch();
//        } else {
//            super.onBackPressed();
//        }
    }

    private fun checkPermistion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                )
            } else {
                downloadSora()
            }
        } else {
            downloadSora()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Download Sora
                    downloadSora()
                } else {
                    if (shouldShowRequestPermissionRationale(permissions[0])) {
                        SnackbarPermissionStorage(
                            getString(R.string.grand_permission),
                            getString(R.string.allow)
                        )
                    } else {
                        customForOpenSettings(
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE,
                            R.string.get_permission_storage_save
                        )
                    }
                }
                return
            }
        }
    }

    private fun customForOpenSettings(type_permission: Int, text_permision: Int) {
        if (applicationContext != null) {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.go_settings)
            alertDialog.setCancelable(false)
            alertDialog.setMessage(text_permision)
            alertDialog.setPositiveButton(
                R.string.settings,
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        try {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri =
                                Uri.fromParts(getString(R.string.package_string), packageName, null)
                            intent.data = uri
                            startActivityForResult(intent, type_permission)
                        } catch (e: Exception) {
                            Log.i("TAG", "Activity e is :" + e.message)
                        }
                    }
                })
            alertDialog.setNegativeButton(
                R.string.cancel,
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        dialog.dismiss()
                    }
                })
            val dialogCreator = alertDialog.create()
            dialogCreator.show()
            val neagtive_button = dialogCreator.getButton(DialogInterface.BUTTON_NEGATIVE)
            val positive_button = dialogCreator.getButton(DialogInterface.BUTTON_POSITIVE)
            val params_for_space_between_buttons = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params_for_space_between_buttons.setMargins(0, 0, 30, 0)
            neagtive_button.layoutParams = params_for_space_between_buttons
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                positive_button.setBackgroundColor(getColor(R.color.colorAccent))
                neagtive_button.setBackgroundColor(getColor(R.color.colorAccent))
            } else {
                positive_button.setBackgroundColor(resources.getColor(R.color.colorAccent))
                neagtive_button.setBackgroundColor(resources.getColor(R.color.colorAccent))
            }
        } else {
            Log.i("TAG", "Activity is null....")
        }
    }

    private fun SnackbarPermissionStorage(title: String, text_button: String) {
        val snackbar = Snackbar.make(findViewById(R.id.RelativeLayout), title, Snackbar.LENGTH_LONG)
            .setAction(text_button, object : View.OnClickListener {
                override fun onClick(v: View) {
                    Log.i("TAG", "isStoragePermissionGranted third")
                    checkPermistion()
                }
            })
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        snackbar.show()
    }

    override fun onResume() {
        super.onResume()
        //Listen For not Connection
        registerNoConnection()
        //Listen for not Internet
        registerNoInternet()
        PlayerConstants.PROGRESSER_HANDLER = object : Handler() {
            override fun handleMessage(msg: Message) {
                val i = msg.obj as Array<Int>
                totalDuration = i[1]
                textBufferDuration!!.text = "" + utilities!!.milliSecondsToTimer(
                    i.get(0).toLong()
                )
                textDuration!!.text = "" + utilities!!.milliSecondsToTimer(
                    i.get(1).toLong()
                )
                binding.progressBar.progress = i.get(2)
            }
        }
        //   boolean isServiceRunning = Utilities.isServiceRunning(MediaPlayerService.class.getName(), getApplicationContext());
        if (isServiceRunning) {
            updateUI(applicationContext)
        }
        binding.progressBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                //    position_seekBar  = utilities.progressToTimer(seekBar.getProgress(),totalDuration);
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                position_seekBar = utilities!!.progressToTimer(seekBar.progress, totalDuration)
                MediaPlayerService.mediaPlayer.seekTo(position_seekBar)
            }
        })
    }

    @OnClick(
        R.id.imageViewAlbumArt,
        R.id.btnPrevious,
        R.id.btnPlay,
        R.id.btnPause,
        R.id.btnStop,
        R.id.btnNext,
        R.id.Fragment_List_Sound_LL_Control_Media
    )
    fun onViewClicked(view: View) {
        val openDetails = Intent(this@ListSoundReader, DetailsSoundActivity::class.java)
        when (view.id) {
            R.id.imageViewAlbumArt -> {
                startActivity(openDetails)
                overridePendingTransition(
                    R.anim.item_anim_slide_from_top,
                    R.anim.item_anim_no_thing
                )
            }
            R.id.btnPrevious -> {
                MediaPlayerService.transportControls.skipToPrevious()
                IsPlay = true
                updateUI(applicationContext)
            }
            R.id.btnPlay -> {
                MediaPlayerService.transportControls.play()
                IsPlay = true
                updateUI(applicationContext)
            }
            R.id.btnPause -> {
                MediaPlayerService.transportControls.pause()
                IsPlay = false
                updateUI(applicationContext)
            }
            R.id.btnStop -> {
                //service is active
                stopService(playeIntent)
                FragmentListSoundLLControlMedia!!.visibility = View.GONE
                isServiceRunning = false
            }
            R.id.btnNext -> {
                MediaPlayerService.transportControls.skipToNext()
                IsPlay = true
                updateUI(applicationContext)
            }
            R.id.Fragment_List_Sound_LL_Control_Media -> {
                val bundle = Bundle()
                bundle.putString(ImageAdapter.SHEKH_ID, Gson().toJson(poisition))
                bundle.putString(
                    ImageAdapter.SHEKH_NAME,
                    Gson().toJson(MediaPlayerService.activeAudio.nameShekh)
                )
                openDetails.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                openDetails.putExtras(bundle)
                startActivity(openDetails)
                overridePendingTransition(
                    R.anim.item_anim_slide_from_top,
                    R.anim.item_anim_no_thing
                )
            }
        }
    }

    companion object {
        @JvmField
        var ListSoundReaderLoadingIndicator: ProgressBar? = null
        val BROADCAST_INVISABLE = "com.example.createmediaplayer.invisable"

        @JvmField
        var isServiceRunning = false
        var textNowPlaying: TextView? = null
        var btnPlay: ImageButton? = null
        var btnPause: ImageButton? = null
        var textBufferDuration: TextView? = null
        var textDuration: TextView? = null

        @JvmField
        var FragmentListSoundLLControlMedia: MaterialCardView? = null
        private var imageViewAlbumArt: CircleImageView? = null

        //Create Folder to save Koran
        var FILENAME: String? = null

        @JvmField
        var IsPlay = true

        //for Service
        @JvmField
        val Broadcast_PLAY_NEW_AUDIO = "com.example.createmediaplayer.PlayNewAudio"

        //Create arrayList from Audio class
        val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123

        @JvmStatic
        fun updateUI(context: Context?) {
            if (!IsPlay) {
                btnPlay!!.visibility = View.VISIBLE
                btnPause!!.visibility = View.GONE
            } else {
                btnPlay!!.visibility = View.GONE
                btnPause!!.visibility = View.VISIBLE
            }
            textNowPlaying!!.text =
                MediaPlayerService.activeAudio.nameSora + " / " + MediaPlayerService.activeAudio.nameShekh
            //        Glide.with(context)
//                .load(activeAudio.getUrl_image())
//                .apply(new RequestOptions().placeholder(R.mipmap.logo).centerCrop())
//                .into(imageViewAlbumArt);
        }
    }
}