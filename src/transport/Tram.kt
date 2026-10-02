package transport

class Tram(
    number: String,
    routeNumber: String
) : Transport(number, routeNumber) {

    override fun getTransportType(): TransportType {
        return TransportType.TRAM
    }

    override fun getTypeName(): String {
        return "Трамвай"
    }
}