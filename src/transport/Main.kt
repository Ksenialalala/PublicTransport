package transport

fun main() {

    val routes = mutableListOf<Route>()
    val users = mutableListOf<User>()

    while (true) {
        println("Городской общественный транспорт")
        println("1. Создать маршрут")
        println("2. Создать пользователя")
        println("3. Подписать пользователя на маршрут")
        println("4. Смоделировать приближение транспорта")
        println("0. Выход")

        print("Выберите действие: ")
        when (readLine()?.trim()) {
            "1" -> {
                println()
                println("Создание маршрута")
                print("Введите номер маршрута: ")
                val number =
                    readLine()?.trim() ?: ""
                if (number.isEmpty()) {
                    println(
                        "Ошибка: номер маршрута " +
                                "не может быть пустым."
                    )
                    continue
                }
                if (number.length > 10) {
                    println(
                        "Ошибка: номер маршрута " +
                                "не должен превышать 10 символов."
                    )
                    continue
                }
                if (
                    routes.any {
                        it.number == number
                    }
                ) {
                    println(
                        "Ошибка: маршрут №$number " +
                                "уже существует."
                    )
                    continue
                }
                println()
                println("Выберите вид транспорта:")
                println("1. Автобус")
                println("2. Трамвай")
                print("Ваш выбор: ")
                val type = when (
                    readLine()?.trim()
                ) {
                    "1" -> TransportType.BUS
                    "2" -> TransportType.TRAM
                    else -> {
                        println(
                            "Ошибка: необходимо выбрать 1 или 2."
                        )
                        continue
                    }
                }
                val route = Route(
                    number = number,
                    transportType = type
                )
                routes.add(route)
                println()
                println(
                    "Маршрут №$number успешно создан."
                )
            }

            "2" -> {
                println()
                println("Создание пользователя")
                print("Введите имя пользователя: ")
                val name =
                    readLine()?.trim() ?: ""
                if (name.isEmpty()) {
                    println(
                        "Ошибка: имя не может быть пустым."
                    )
                    continue
                }
                if (name.length > 100) {
                    println(
                        "Ошибка: имя не должно " +
                                "превышать 100 символов."
                    )
                    continue
                }
                if (
                    users.any {
                        it.name == name
                    }
                ) {
                    println(
                        "Ошибка: пользователь \"$name\" " +
                                "уже существует."
                    )
                    continue
                }
                val user = User(name)
                users.add(user)
                println()
                println(
                    "Пользователь \"$name\" успешно создан."
                )
            }

            "3" -> {
                println()
                println("--- Подписка на маршрут ---")
                if (routes.isEmpty()) {
                    println(
                        "Сначала необходимо создать хотя бы один маршрут."
                    )
                    continue
                }
                if (users.isEmpty()) {
                    println(
                        "Сначала необходимо создать хотя бы одного пользователя."
                    )
                    continue
                }
                println()
                println("Доступные маршруты:")
                for (route in routes) {
                    val typeName = when (
                        route.transportType
                    ) {
                        TransportType.BUS ->
                            "автобус"

                        TransportType.TRAM ->
                            "трамвай"
                    }
                    println(
                        "№${route.number} — $typeName"
                    )
                }

                print("Введите номер маршрута: ")
                val routeNumber =
                    readLine()?.trim() ?: ""
                val route = routes.find {
                    it.number == routeNumber
                }

                if (route == null) {
                    println(
                        "Ошибка: маршрут №$routeNumber " +
                                "не найден."
                    )
                    continue
                }
                println()
                println("Доступные пользователи:")
                for (user in users) {
                    println(
                        "- ${user.name}"
                    )
                }
                print("Введите имя пользователя: ")
                val userName =
                    readLine()?.trim() ?: ""
                val user = users.find {
                    it.name == userName
                }
                if (user == null) {
                    println(
                        "Ошибка: пользователь \"$userName\" " +
                                "не найден."
                    )
                    continue
                }

                route.subscribe(user)
            }

            "4" -> {
                println()
                println("Приближение транспорта")
                if (routes.isEmpty()) {
                    println(
                        "Сначала необходимо создать маршрут."
                    )
                    continue
                }

                println()
                println("Выберите маршрут:")
                for (route in routes) {
                    val typeName = when (
                        route.transportType
                    ) {
                        TransportType.BUS ->
                            "автобус"
                        TransportType.TRAM ->
                            "трамвай"
                    }
                    println(
                        "№${route.number} — $typeName"
                    )
                }

                print("Введите номер маршрута: ")
                val routeNumber =
                    readLine()?.trim() ?: ""
                val route = routes.find {
                    it.number == routeNumber
                }
                if (route == null) {
                    println(
                        "Ошибка: маршрут №$routeNumber " +
                                "не найден."
                    )
                    continue
                }
                print(
                    "Введите номер транспортного средства: "
                )
                val transportNumber =
                    readLine()?.trim() ?: ""
                if (transportNumber.isEmpty()) {
                    println(
                        "Ошибка: номер транспорта " +
                                "не может быть пустым."
                    )
                    continue
                }

                if (transportNumber.length > 10) {
                    println(
                        "Ошибка: номер транспорта " +
                                "не должен превышать 10 символов."
                    )
                    continue
                }
                print(
                    "Введите название остановки: "
                )
                val stopName =
                    readLine()?.trim() ?: ""

                if (stopName.isEmpty()) {
                    println(
                        "Ошибка: название остановки " +
                                "не может быть пустым."
                    )
                    continue
                }

                if (stopName.length > 100) {
                    println(
                        "Ошибка: название остановки " +
                                "не должна превышать 100 символов."
                    )
                    continue
                }

                val stop = Stop(stopName)
                val transport: Transport = when (
                    route.transportType
                ) {
                    TransportType.BUS -> {
                        Bus(
                            number = transportNumber,
                            routeNumber = route.number
                        )
                    }
                    TransportType.TRAM -> {
                        Tram(
                            number = transportNumber,
                            routeNumber = route.number
                        )
                    }
                }
                transport.moveTo(stop)

                route.notifyUsers(
                    transport = transport,
                    stop = stop
                )
            }

            "0" -> {
                println()
                println("Программа завершена.")
                break
            }

            else -> {
                println(
                    "Ошибка: выберите пункт от 0 до 4."
                )
            }
        }
    }
}