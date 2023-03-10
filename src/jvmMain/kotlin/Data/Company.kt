package Data

enum class Taxpayer {
    DA,
    NE
}

class Company(val name: String, val taxNumber: String, val registrationNumber: String, val taxpayer: Taxpayer){
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