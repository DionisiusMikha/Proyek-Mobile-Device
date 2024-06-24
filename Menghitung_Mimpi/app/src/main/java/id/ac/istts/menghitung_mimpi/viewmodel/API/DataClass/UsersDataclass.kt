package id.ac.istts.menghitung_mimpi.viewmodel.API.DataClass

data class UsersDataclass(
    val id_user: String,
    val full_name: String,
    val dob: String,
    val phone_number: String,
    val email: String,
    val password: String,
    val saldo: Int,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String?
)