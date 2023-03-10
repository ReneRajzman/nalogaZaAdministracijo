package Data

import androidx.compose.ui.window.application



fun main() = application {

        var jabolko=Item("delises", 2.10, 1.10, 2, 5.0)
        var cips=Item("chio", 3.00, 2.33, 3, 0.0)
        var hruska=Item("hruske", 2.33, 1.25, 3, 3.0)
        var lesniki=Item("lesniki", 4.50, 4.0, 3, 0.0)
        var slive=Item("slive", 3.00, 2.0, 3, 0.0)
        var mleko=Item("mleko", 0.90, 7.0, 2, 5.0)

        var nakupovalniList=Items("danes")
        nakupovalniList.addItem(jabolko)
        nakupovalniList.addItem(cips)
        nakupovalniList.addItem(hruska)
        nakupovalniList.addItem(lesniki)
        nakupovalniList.addItem(slive)
        nakupovalniList.addItem(mleko)


        nakupovalniList.addItem(jabolko)


        nakupovalniList.modifyItem(jabolko,null,12.00,4.0)
        nakupovalniList.deleteItem(hruska)

        val valuna= Company("Valuna", 123, 567, "NE")

        val invoice=Invoice(items =nakupovalniList, "Hofer", "Dora", valuna)
        println(invoice.toString())

        println("\n\nProba search:\n")
        println(invoice.search("Dora"))



}
