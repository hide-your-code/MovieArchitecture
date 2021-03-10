package minhdtm.example.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @SerializedName("status_code")
    val statusCode: Int = 0,

    @SerializedName("status_message")
    val statusMessage: String = ""
) {

    companion object {
        const val MESS_EMPTY_RESPONSE = "Empty Response"
    }
}
