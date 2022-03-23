package com.mohamedtaha.imagine.ui.youtube

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.mohamedtaha.imagine.BuildConfig.YOUTUBE_API_KEY
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.databinding.ActivityYoutubeBinding
import com.mohamedtaha.imagine.datastore.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YoutubeActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener {
    private lateinit var binding: ActivityYoutubeBinding
    private val datStoreViewModel: DataStoreViewModel by viewModels()

    private var channelLink = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityYoutubeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val fragment: YouTubePlayerSupportFragment =
//            (YouTubePlayerSupportFragment) supportFragmentManager!!.findFragmentById (R.id.youtubeFragment)
//        fragment.initialize(Config.YOUTUBE_API_KEY, this)
        //   binding.youtubeFragment.initialize(Config.YOUTUBE_API_KEY, this)
        datStoreViewModel.youTubeChannel.asLiveData().observe(this@YoutubeActivity) {
            channelLink = it
        }

        val youTubePlayerFragment = supportFragmentManager
            .findFragmentById(R.id.youtubeFragment) as YouTubePlayerSupportFragment? ?: return
        youTubePlayerFragment.initialize(YOUTUBE_API_KEY, this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider,
        youTubePlayer: YouTubePlayer,
        b: Boolean
    ) {
        if (!b) {
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)
            youTubePlayer.cueVideo(channelLink)
            Log.d("Play", "Play")
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider,
        youTubeInitializationResult: YouTubeInitializationResult
    ) {
        if (youTubeInitializationResult.isUserRecoverableError) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show()
        } else {
            val error = String.format(getString(R.string.player_error), youTubeInitializationResult)
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//        if (requestCode == RECOVERY_REQUEST) {
//            // Retry initialization if user performed a recovery action
//            youtubePlayerProvider.initialize(Config.YOUTUBE_API_KEY, this)
//        }
//        super.onActivityResult(requestCode, resultCode, data)
//    }
//
//    private val youtubePlayerProvider: YouTubePlayer.Provider
//        get() = binding.YoutubeView

    companion object {
        private const val RECOVERY_REQUEST = 1
    }
}