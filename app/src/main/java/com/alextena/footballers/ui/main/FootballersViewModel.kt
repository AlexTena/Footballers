package com.alextena.footballers.ui.main


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextena.footballers.model.Player
import com.alextena.footballers.model.PlayersResponse
import com.alextena.footballers.repository.FootballersRepository
import com.alextena.footballers.utils.Constants.Companion.QUERY_PAGE_SIZE
import com.alextena.footballers.utils.Resource
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Response

class FootballersViewModel: ViewModel() {
    private lateinit var footballersRepository : FootballersRepository
    private var currentImage: Bitmap? = null

    var players: MutableLiveData<Resource<PlayersResponse>> = MutableLiveData()
    var footballersPage: Int = 1
    var playersListResponse: PlayersResponse? = null

    fun loadData(repository: FootballersRepository) {
        footballersRepository = repository
        getAllPlayers(pageNumber = footballersPage, limit =  QUERY_PAGE_SIZE)
    }

    fun getAllPlayers(pageNumber: Int, limit: Int) = viewModelScope.launch {
        players.postValue(Resource.Loading())
        val response = footballersRepository.getAllPlayers(pageNumber, limit)
        players.postValue(handlePlayersResponse(response))
    }

    private suspend fun handlePlayersResponse(response: Response<PlayersResponse>): Resource<PlayersResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                footballersPage++
                resultResponse.items.forEach { player ->
                    val image: Deferred<Bitmap?> = viewModelScope.async(context = Dispatchers.IO) {
                        getPlayerImage(player.id)
                    }
                    player.playerImage = image.await()
                }
                if(playersListResponse == null) {
                    playersListResponse = resultResponse
                }
                else {
                    val oldPlayersList = playersListResponse?.items
                    val newPlayersList = resultResponse.items
                    oldPlayersList?.addAll(newPlayersList)
                }
                return Resource.Success(playersListResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun getPlayerImage(playerId: Int) : Bitmap? {
        val response = footballersRepository.getPlayerImage(playerId)
        currentImage  = handleImageResponse(response).data ?: currentImage
        return currentImage
    }

    private fun handleImageResponse(response: Response<ResponseBody>): Resource<Bitmap> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val data = BitmapFactory.decodeStream(resultResponse.byteStream())
                val bmp = Bitmap.createScaledBitmap(data, 340, 340, false)
                return Resource.Success(bmp)
            }
        }
        return Resource.Error(response.message())
    }

    fun savePlayer(player: Player) = viewModelScope.launch {
        footballersRepository.upsert(player)
    }

    fun getSavedPlayers() = footballersRepository.getSavedPlayers()

    fun deletePlayer(player: Player) = viewModelScope.launch {
        footballersRepository.deletePlayer(player)
    }
}