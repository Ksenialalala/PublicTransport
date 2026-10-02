package transport

class Route(
    val number: String,
    val transportType: TransportType
) {

    private val subscribers =
        mutableListOf<NotificationListener>()

    init {
        require(number.isNotBlank()) {
            "Номер маршрута не может быть пустым."
        }

        require(number.length <= 10) {
            "Номер маршрута не должен превышать 10 символов."
        }
    }

    fun subscribe(
        listener: NotificationListener
    ) {
        if (!subscribers.contains(listener)) {
            subscribers.add(listener)
            println(
                "Пользователь успешно подписан " +
                        "на маршрут №$number."
            )
        } else {
            println(
                "Пользователь уже подписан " +
                        "на маршрут №$number."
            )
        }
    }

    fun notifyUsers(
        transport: Transport,
        stop: Stop
    ) {
        if (transport.routeNumber != number) {
            println(
                "Ошибка: транспорт №${transport.number} " +
                        "не относится к маршруту №$number."
            )
            return
        }

        if (transport.getTransportType() != transportType) {
            println(
                "Ошибка: ${transport.getTypeName()} " +
                        "не соответствует типу транспорта " +
                        "маршрута №$number."
            )
            return
        }
        println()
        println(
            "${transport.getTypeName()} " +
                    "№${transport.number} приближается " +
                    "к остановке \"${stop.name}\"."
        )
        if (subscribers.isEmpty()) {
            println(
                "На маршруте №$number " +
                        "нет подписанных пользователей."
            )
            return
        }

        for (subscriber in subscribers) {
            subscriber.onTransportApproaching(
                this,
                transport,
                stop
            )
        }
    }
}