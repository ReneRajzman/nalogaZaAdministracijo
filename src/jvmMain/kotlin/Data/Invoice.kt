package Data

import java.util.*

class Invoice(val items: Items, val issuer: String, val cashier:String, val customer: Company?):Searchable {
    val id: UUID
    val created: Date = Date()
    var modified: Date = Date()
    val ean13: String

    val adress="HIPERMARKET LENART, Industrijska ulica 49, 2230 Lenart"
    val heading="Naziv"
    val quantityPrint="Količina:KG/kom"
    val uPicePrint="Cena"
    val discount="Popust"
    val totalPrint="Znesek"
    val taxPrint="Davek"
    val netoPrint="ZNESEK"
    val DDVPrint="DDV"
    val brutoPrint="Bruto"


    init {
        this.id = UUID.randomUUID()
        this.ean13 = generateEan13()
    }

    override fun search(word: String): Boolean {
        var query=false
        if(items.search(word)){
            query=true
        }
        if(this.issuer.contains(word)){
            query=true
        }
        if(this.cashier.contains(word)){
            query=true
        }
        if(this.customer?.search(word) == true){
            query=true
        }
        return query
    }

    override fun toString(): String {
        val sb = StringBuilder()
        var paysTaxes=true
        var isCustomer=false

        if(customer!=null) {
            isCustomer=true
            if (customer.taxpayer.toString() == "NE") {
                paysTaxes = false
            }
        }

        sb.append("${issuer}\n")
        sb.append("${adress}\n")
        sb.append("${created}\n")
        sb.append("EAN13: ${ean13}\n")
        sb.append("Blagajnik: ${cashier}\n")
        sb.append("___________________________________________________________________\n")
        sb.append("${heading.padEnd(10)}${quantityPrint.padEnd(20)}${uPicePrint.padEnd(10)}${discount.padEnd(10)}${totalPrint.padEnd(10)}${taxPrint.padEnd(10)}\n")
        sb.append("___________________________________________________________________\n")
        sb.append(items.toString())
        sb.append("___________________________________________________________________\n\n")
        if(paysTaxes) {
            sb.append("${brutoPrint.padEnd(10)}${DDVPrint.padEnd(50)}${netoPrint.padEnd(10)}\n")
            sb.append("${items.NetoDDVBrutoString()}\n")
        }
        else {
            sb.append("ZNESEK ZA DAVČNEGA UPRAVIČENCA: \n")
            sb.append("${brutoPrint.padEnd(10)}${DDVPrint.padEnd(50)}${netoPrint.padEnd(10)}\n")
            sb.append("${items.priceForTaxRelieved()}\n")

        }
        if(isCustomer){
            sb.append("${customer?.toString()}\n")
        }

        sb.append("___________________________________________________________________\n")
        sb.append("ZOI: $id")

        return sb.toString()
    }
    private fun generateEan13(): String {
        val random = Random()
        val sb = StringBuilder()

        // Generate 12 random digits
        for (i in 1..12) {
            sb.append(random.nextInt(10))
        }

        // Calculate the check digit
        var sum = 0
        for (i in 0..11 step 2) {
            sum += sb[i].toString().toInt()
            sum += sb[i+1].toString().toInt() * 3
        }
        val checkDigit = (10 - sum % 10) % 10

        // Return the full EAN-13 barcode
        sb.append(checkDigit)
        return sb.toString()
    }
}
