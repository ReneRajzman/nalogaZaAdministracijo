package Data

import java.util.*


class Items(val name: String):Searchable {
    val created: Date = Date()
    var modified: Date = Date()

    private val itemsList = mutableListOf<Item>()

    fun addItem(item: Item) {
        var itemExists = false
        for (existingItem in itemsList) {
            if (existingItem.id == item.id) {
                existingItem.quantity += item.quantity
                existingItem.modified = Date()
                itemExists = true
                break
            }
        }
        if (!itemExists) {
            itemsList.add(item)
            item.modified = Date()
        }
        modified = Date()
    }

    fun deleteItem(item: Item) {
        var found = false
        for (i in itemsList.indices) {
            if (itemsList[i] == item) {
                itemsList.removeAt(i)
                found = true
                break
            }
        }
        if (found) {
            modified = Date()
        } else {
            println("Item not found.")
        }
    }


    fun modifyItem(item: Item, newPrice: Double? = null, newDiscount: Double? = null, newQuantity: Double? = null) {
        var itemToUpdate: Item? = null
        for (i in itemsList.indices) {
            if (itemsList[i].id == item.id) {
                itemToUpdate = itemsList[i]
                break
            }
        }
        if (itemToUpdate != null) {
            if (newPrice != null) {
                itemToUpdate.price = newPrice
            }
            if (newDiscount != null) {
                itemToUpdate.discount = newDiscount
            }
            if (newQuantity != null) {
                itemToUpdate.quantity = newQuantity
            }
            itemToUpdate.modified = Date()
            modified = Date()
        } else {
            println("Item not found.")
        }
    }


    fun returnTotal():Double{
        var total:Double=0.00
        for(item in itemsList){
            total=total+item.getTotalPrice()
        }
        return total
    }

    fun returnTaxTotal():Double{
        var taxBuffer:Double=0.00
        var totalBuffer:Double=0.00
        for(item in itemsList){
            taxBuffer=taxBuffer+item.getTotalPrice()/(1 + item.taxRate / 100)
            totalBuffer=totalBuffer+item.getTotalPrice()
        }
        return totalBuffer-taxBuffer
    }

    fun NetoDDVBrutoString():String{
        val sb = StringBuilder()

        sb.append("${String.format("%.2f€", returnTotal()-returnTaxTotal()).padEnd(10)}${String.format("%.2f€", returnTaxTotal()).padEnd(50)}${String.format("%.2f€", returnTotal()).padEnd(10)}\n")

        return sb.toString()
    }

    fun priceForTaxRelieved():String{
        val sb=StringBuilder()
        sb.append("${String.format("%.2f€", returnTotal()-returnTaxTotal()).padEnd(10)}${String.format("0.00€").padEnd(50)}${String.format("%.2f€", returnTotal()-returnTaxTotal()).padEnd(10)}\n")
        return sb.toString()
    }

    override fun search(word: String): Boolean {
        var query=false
        if(this.name.contains(word)){
            query=true
        }
        for(item in itemsList){
            if(item.search(word)){
                query=true
            }
        }
        return query
    }

    override fun toString(): String {
        val sb = StringBuilder()

        for (item in itemsList) {
            sb.append("${item.toString()}\n")
        }
        return sb.toString()
    }
}

