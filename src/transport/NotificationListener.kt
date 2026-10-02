package transport

interface NotificationListener {

    fun onTransportApproaching(
        route: Route,
        transport: Transport,
        stop: Stop
    )
}