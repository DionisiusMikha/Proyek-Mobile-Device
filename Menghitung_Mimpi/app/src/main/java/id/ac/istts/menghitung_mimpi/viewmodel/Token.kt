package id.ac.istts.menghitung_mimpi.viewmodel

object Token {
    private var jwtToken: String? = null

    fun setToken(token: String) {
        jwtToken = token
    }

    fun getToken(): String? {
        return jwtToken
    }
}