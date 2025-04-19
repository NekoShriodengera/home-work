package neko.shriodenger.lab2

fun main(){
    //1. Визначити, чи є число парним ------------------------------------
    println("\nWork 1:")
    println("Num:")
    val num1 = readln()?.toIntOrNull()
    if (num1 != null) {
        if (num1 % 2 == 0) {
            println("Parne chislo")
        } else {
            println("Ne parne chislo")
        }
    }
    else{
        println("Num - null")
    }
    //9. Цикли та ітерації - сума чисел від 1 до n -------------------------
    println("\nWork 9:")
    println("Num:")
    val num2 = readLine()?.toIntOrNull()
    if (num2 != null && num2 > 0) {
        var sum = 0
        for (i in 1..num2) {
            sum += i
        }
        println("Result sum: 1 to $num2 = $sum")
    } else {
        println("Null or Zero or Negative")
    }
    //16. Знайти середнє значення елементів масиву --------------------------
    println("\nWork 9:")
    println("Average [21,-7.31]:")
    val numbers = arrayOf(21, -7, 31)
    var aver = numbers.average()
    println("Average: $aver")
    aver= 0.0
    for (nambes in numbers) {
        aver+=nambes
    }
    aver /= numbers.size
    println("Average 2: $aver")
    //22.	Прийняти ім’я користувача
    // (String?), вивести "Привіт, [ім’я]",
    // або "Привіт, Anonymous" якщо null/порожнє
    println("\nWork 22:")
    val name: String? = readLine()
    if (name != null) {
        println("Hi, $name")
    } else {
        println("Hi, Anonymous")
    }
    //26.Прочитати ціле число з консолі — з перевіркою NumberFormatException ---------
    println("\nWork 26:")
    println("Num:")
    try {
        val intNumb = readLine()?.toInt()
        println("Integer number: $intNumb")
    } catch (e: NumberFormatException) {
        println("Not integer number!")
    }
}