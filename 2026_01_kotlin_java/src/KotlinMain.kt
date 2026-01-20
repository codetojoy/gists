fun main(args: Array<String>) {
    val type = if (args.isNotEmpty()) args[0].toInt() else 1

    val vehicle = Vehicle()
    val fuel = vehicle.getFuel(type)

    println("Kotlin calling Java: Vehicle type $type uses '$fuel'")
}
