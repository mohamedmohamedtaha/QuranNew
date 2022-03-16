package com.mohamedtaha.imagine.configfirebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.mohamedtaha.imagine.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteConfig @Inject constructor(){
    private var instanceRemoteConfig = FirebaseRemoteConfig.getInstance()
    fun setConfigComplete(){
        val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 60 * 60  }
        instanceRemoteConfig.setConfigSettingsAsync(configSettings)
        instanceRemoteConfig.apply {
            setDefaultsAsync(R.xml.config_default)
            fetchAndActivate().addOnCompleteListener { task->
                if (task.isSuccessful){
                    _youtubeChannel.value = getYouTubeChannel()
                }
            }
        }
    }
    companion object{
        private const val YOUTUBE_CHANNEL = "youTubeChannel"
    }

    private fun getYouTubeChannel() = instanceRemoteConfig.getString(YOUTUBE_CHANNEL)
    private val _youtubeChannel =  MutableLiveData<String>()
            val youtubeChannel get() = _youtubeChannel
}
















