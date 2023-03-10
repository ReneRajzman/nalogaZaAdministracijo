package Data

enum class Taxpayer {
    DA,
    NE;

    companion object {
        fun fromString(value: String): Taxpayer {
            return when (value.toUpperCase()) {
                "DA" -> DA
                "NE" -> NE
                else -> throw IllegalArgumentException("Invalid value: $value")
            }
        }
    }
}

class Company(val name: String, val taxNumber: Int, val registrationNumber: Int, val taxpayer: String):Searchable{
    override fun search(word: String): Boolean {
        return this.name.contains(word)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Ime podjetja: ")
        sb.append("${name}\n")
        sb.append("Davčna številka: ")
        sb.append("${taxNumber}\n")
        sb.append("Registracijska številka: ")
        sb.append("${registrationNumber}\n")
        return sb.toString()

    }
}