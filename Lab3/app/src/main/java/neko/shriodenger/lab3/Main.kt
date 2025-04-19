package neko.shriodenger.lab3
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun fetchGradesFromServer(): List<Int> {
    delay(2000)
    return listOf(99, 98, 97, 96, 95)
}
fun main() = runBlocking {
    val student1 = Student(name = "Sergey")
    val student2 = Student(name = "Alina")
    student2.age= 20
    student2.updateGrades(listOf(93, 74, 68, 81))
    val gradesServer = async { fetchGradesFromServer() }


    val fetchedGrades = gradesServer.await()
    student1.updateGrades(fetchedGrades)

    println("Student 1: ${student1.name}, Age: ${student1.age}, Status: ${student1.status}")
    println("His average grade:  ${student1.getAverage()}")
    println("Student 2: ${student2.name}, Age: ${student2.age}, Status: ${student2.status}")
    println("His average grade:  ${student2.getAverage()}")

    val sumGrds = student1 + student2
    println("Combined average grade: ${sumGrds.getAverage()}")

    val multGrds = student2 * 2
    println("Student 2, grades multiplied by 2: ${multGrds.name}, Average grade: ${multGrds.getAverage()}")

    if (student1 == student2) {
        println("Student 1 and Student 2 are equal.")
    } else {
        println("Student 1 and Student 2 are different.")
    }

    println("Student 1 status: ${student1.status}")

    val group = Group(student1, student2)
    println("Top student: ${group.topStud()?.name}, Average grade: ${group.topStud()?.getAverage()}")
}