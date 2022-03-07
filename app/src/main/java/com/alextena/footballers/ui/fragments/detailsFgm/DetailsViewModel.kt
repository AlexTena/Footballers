package com.alextena.footballers.ui.fragments.detailsFgm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextena.footballers.model.ClubResponse
import com.alextena.footballers.repository.DetailsRepository
import com.alextena.footballers.utils.Resource
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class DetailsViewModel : ViewModel() {
    val clubName: MutableLiveData<Resource<ClubResponse>> = MutableLiveData()
    val clubImage: MutableLiveData<Resource<Bitmap>> = MutableLiveData()

    private var detailsRepository : DetailsRepository = DetailsRepository()

    fun getClubImage(clubId: Int) = viewModelScope.launch {
        clubImage.postValue(Resource.Loading())
        val response = detailsRepository.getClubImage(clubId)
        clubImage.postValue(handleClubImageResponse(response))
    }

    private fun handleClubImageResponse(response: Response<ResponseBody>): Resource<Bitmap> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val data = BitmapFactory.decodeStream(resultResponse.byteStream())
                val bmp = Bitmap.createScaledBitmap(data, 200, 200, false)
                return Resource.Success(bmp)
            }
        }
        return Resource.Error(response.message())
    }

    fun getClubName(clubId: Int) = viewModelScope.launch {
        clubName.postValue(Resource.Loading())
        val response = detailsRepository.getClubById(clubId)
        clubName.postValue(handleClubResponse(response))
    }

    private fun handleClubResponse(response: Response<ClubResponse>): Resource<ClubResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}