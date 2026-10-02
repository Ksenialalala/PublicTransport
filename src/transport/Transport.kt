package transport

abstract class Transport(
    val number: String,
    val routeNumber: String
) {

    private var currentStop: Stop? = null

    init {
        require(number.isNotBlank()) {
            "Номер транспортного средства не может быть пустым."
        }

        require(number.length <= 10) {
            "Номер транспортного средства не должен превышать 10 символов."
        }

        require(routeNumber.isNotBlank()) {
            "Номер маршрута не может быть пустым."
        }

        require(routeNumber.length <= 10) {
            "Номер маршрута не должен превышать 10 символов."
        }
    }

    fun moveTo(stop: Stop) {
        currentStop = stop
    }

    fun getCurrentStop(): Stop? {
        return currentStop
    }

    abstract fun getTransportType(): TransportType

    abstract fun getTypeName(): String
}