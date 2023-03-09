package Data

import java.util.*

data class Item(val name: String, var price: Double, var quantity: Double, val taxClass: Int, var discount: Double=0.0) {
    val id: UUID
    val created: Date = Date()
    var modified: Date = Date()
    val ean13: String
    var taxRate:Double=0.0

    init {
        this.id = UUID.randomUUID()
        this.ean13 = generateEan13()
        if(taxClass==1){
            this.taxRate=0.0
        }
        else if(taxClass==2){
            this.taxRate=9.5
        }
        else if(taxClass==3){
            this.taxRate=22.0
        }
        else if(taxClass==4){
            this.taxRate=0.0
        }
        else if(taxClass==5){
            this.taxRate=5.0
        }
        else{
            println("Vnešena napačna stopnja")
        }
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

    fun getTotalPrice(): Double {
        val totalPrice = price * quantity * (1 + taxRate / 100)
        return if (discount > 0) totalPrice * (1 - discount / 100) else totalPrice
    }

    fun getUndiscountedPrice():Double{
        val totalPrice = price * quantity * (1 + taxRate / 100)
        return totalPrice
    }


    fun modifyItem(newPrice: Double? = null, newDiscount: Double? = null, newQuantity: Double?=null) {
        if (newPrice != null) {
            price = newPrice
        }
        if (newDiscount != null) {
            discount = newDiscount
        }
        if (newQuantity != null) {
            discount = newQuantity
        }
        modified = Date()
    }



    override fun toString(): String {
        return "${name.padEnd(10)}${quantity.toString().padEnd(20)}${String.format("%.2f€", price).padEnd(10)}${String.format("%.2f%%", discount).padEnd(10)}${String.format("%.2f€", getTotalPrice()).padEnd(10)}${String.format("%.2f%%", taxRate)}"
    }

}
