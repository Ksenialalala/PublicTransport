package transport

class Bus(
    number: String,
    routeNumber: String
) : Transport(number, routeNumber) {

    override fun getTransportType(): TransportType {
        return TransportType.BUS
    }

    override fun getTypeName(): String {
        return "Автобус"
    }
}