class Animal {
    fun makeSound(type: Int): String {
        return when (type) {
            1 -> "Meow"
            2 -> "Woof"
            3 -> "Moo"
            4 -> "Oink"
            else -> "Unknown animal"
        }
    }
}
