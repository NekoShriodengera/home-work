package neko.shriodenger.lab3

class Student private constructor(name: String, age: Int, grades: List<Int>) {
    //----------------------------------------------------------------------------------------
    constructor(name: String) : this(name, 0, emptyList())

    //----------------------------------------------------------------------------------------
    init {
        println("Student object created: $name, age $age")
    }
    //----------------------------------------------------------------------------------------
    private var _name: String= name.trim().replaceFirstChar { it.uppercase() }
    private var _age: Int = if (age >= 0) age else 0
    private var _grades: MutableList<Int> = grades.toMutableList()
    var name: String
        get() = _name
        set(value) {
            _name = value.trim().replaceFirstChar { it.uppercase() }
        }

    var age: Int
        get() = _age
        set(value) {
            if (value >= 0) {
                _age = value
            }
        }
    //----------------------------------------------------------------------------------------
    val isAdult: Boolean
        get() = _age >= 18
    //----------------------------------------------------------------------------------------
    val status: String by lazy {
        if (isAdult) "Adult" else "Minor"
    }
    //----------------------------------------------------------------------------------------
    fun getAverage(): Double {
        return if (_grades.isNotEmpty()) _grades.average() else 0.0
    }
    //----------------------------------------------------------------------------------------
    fun processGrades(operation: (Int) -> Int) {
        _grades = _grades.map { operation(it) }.toMutableList()
    }
    //----------------------------------------------------------------------------------------
    fun updateGrades(grades: List<Int>) {
        _grades = grades.toMutableList()
    }
    //----------------------------------------------------------------------------------------
    operator fun plus(other: Student): Student {
        val combGrds = this._grades + other._grades
        return Student(this.name, this.age, combGrds)
    }
    operator fun times(multiplier: Int): Student {
        val multGrds = _grades.map { it * multiplier }
        return Student(this.name, this.age, multGrds)
    }
    override operator fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Student) return false

        return this.name == other.name && this.getAverage() == other.getAverage()
    }
    override fun hashCode(): Int {
        return name.hashCode() + getAverage().hashCode()
    }
}