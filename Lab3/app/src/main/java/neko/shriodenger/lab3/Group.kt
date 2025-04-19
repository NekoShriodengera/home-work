package neko.shriodenger.lab3

class Group(vararg students: Student)  {
    private val studentsList: List<Student> = students.toList()
    operator fun get(index: Int): Student {
        if (index in studentsList.indices) {
            return studentsList[index]
        } else {
            throw IndexOutOfBoundsException("NotIndex")
        }
    }
    fun topStud(): Student? {
        return studentsList.maxByOrNull { it.getAverage() }
    }

}