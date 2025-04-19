package neko.shriodenger.lab2

class TemperatureOutOfRangeException: Exception("Error: temperature < -60 or > 60")
fun main() {
    print("Days count: ")
    val days = readLine()?.toIntOrNull() ?: 1

    val temperatures = DoubleArray(days)

    for (i in 0 until days) {
        print("Day ${i + 1}, Temperature: ")
        val input = readLine()?.toDoubleOrNull()
        if (input == null) {
            println("Null error")
            return
        }

        if (input < -60 || input > 60) {
            throw TemperatureOutOfRangeException()
        }
        else{
            temperatures[i] = input
        }
    }
    val averTemp = temperatures.average()
    val iceDays = temperatures.count { it < 0 }
    val maxTemp = temperatures.maxOrNull() ?: 0.0
    val minTemp = temperatures.minOrNull() ?: 0.0
    var warmDay =0
    for (i in temperatures.indices) {
        if (temperatures[i] == maxTemp) {
            warmDay = i+1
        }
    }
    var coldDay = 0
    for (i in temperatures.indices) {
        if (temperatures[i] == minTemp) {
            coldDay = i+1
        }
    }
    val comment = when{
        averTemp < 0 -> "Cold"
        averTemp <= 20 -> "Nornal"
        else -> "Warm"
    }
    println("\n      Table Temperature:      ")
    println(" ---------------------------- ")
    println("   Day       Temperature      ")
    println(" ---------------------------- ")
    for (i in temperatures.indices) {
        println(" ${String.format("%4d", i + 1)}   ${String.format("%11.1f", temperatures[i])} ")
        println(" ---------------------------- ")
    }

    println("\nResult")
    println("Average temperature: %.2f".format(averTemp))
    println("Cold days count (t < 0): $iceDays")
    println("Most warm day $warmDay : Temp $maxTemp")
    println("Most cold day $coldDay : Temp $minTemp")
    println("Commentar: $comment")

}