package transport

data class Stop(
    val name: String
) {

    init {
        require(name.isNotBlank()) {
            "Название остановки не может быть пустым."
        }

        require(name.length <= 100) {
            "Название остановки не должно превышать 100 символов."
        }
    }
}