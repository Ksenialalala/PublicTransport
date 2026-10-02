package transport

class User(
    val name: String
) : NotificationListener {

    init {
        require(name.isNotBlank()) {
            "Имя пользователя не может быть пустым."
        }

        require(name.length <= 100) {
            "Имя пользователя не должно превышать 100 символов."
        }
    }

    override fun onTransportApproaching(
        route: Route,
        transport: Transport,
        stop: Stop
    ) {
        println(
            "Уведомление для $name: " +
                    "${transport.getTypeName()} " +
                    "№${transport.number} приближается " +
                    "к остановке \"${stop.name}\" " +
                    "на маршруте №${route.number}."
        )
    }
}