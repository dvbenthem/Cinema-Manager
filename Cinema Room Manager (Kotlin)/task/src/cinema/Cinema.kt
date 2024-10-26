package cinema

fun main() {
    println("Enter the number of rows: ")
    val rows = readln().toInt()
    println("Enter the number of seats in each row: ")
    val cols = readln().toInt()
    val room: MutableList<MutableList<String>> = MutableList(rows) {MutableList(cols){"S"}}

    do {
        println("")
        printCinemaMenu()

        val input = readln().toInt()

        when (input){
            1 -> {
                println("")
                printCinemaRoom(room)
            }
            2 -> {
                while(true){
                    println("")
                    println("Enter a row number: ")
                    val rowNumber = readln().toInt()
                    println("Enter a seat number in that row: ")
                    val seatNumber = readln().toInt()

                    if (rowNumber > rows || seatNumber > cols){
                        println("Wrong input")
                    } else if(room[rowNumber - 1][seatNumber - 1] == "S"){
                        room[rowNumber - 1][seatNumber - 1] = "B"
                        costPerSeat(rowNumber, rows, cols)
                        break
                    } else{
                         println("That ticket has already been purchased.")
                        }
                }
            }
            3 ->{
                totalSeatsOccupied(room)
                totalIncome(rows, cols, room)
            }
        }
    } while (input != 0)
}

fun printCinemaMenu(){
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
}

fun printCinemaRoom(room: MutableList<MutableList<String>>){
    println("Cinema:")
    print("  ")
    for (col in 1..room[0].size) {
        print("$col ")

    }
    println()

    for((indexRow, row) in room.withIndex()){
        print("${indexRow + 1} ")
        println(row.joinToString(" "))
    }
}

fun totalSeatsOccupied(room: MutableList<MutableList<String>>){
    println("")
    var soldTickets = 0
    val totalSeats = room.size * room[0].size

    for (i in room){
        soldTickets += i.count{ ticket -> ticket == "B"}
    }


    val percentage = (soldTickets.toDouble() / totalSeats) * 100
    val formatPercentage = "%.2f".format(percentage)

    println("Number of purchased tickets $soldTickets")
    println("Percentage: ${formatPercentage}%")
}

fun totalIncome(rows: Int, cols: Int, room: MutableList<MutableList<String>>){
    val totalSeats: Int = rows * cols
    var totalCost: Int = 0
    var totalIncome: Int = 0
    val halfRows: Int = rows / 2

    for (i in 0 until rows) {
        val row = room[i]
        val pricePerSeat = if (totalSeats <= 60) 10 else if(i < halfRows) 10 else 8

        for (seat in 0 until cols) {
            // Controleer of de stoel bezet is (bijvoorbeeld met 'B')
            if (seat < row.size && row[seat] == "B") {
                totalCost += pricePerSeat
            }
            if (seat < row.size){
                totalIncome += pricePerSeat
            }
        }
    }
    println("Current income: \$$totalCost")
    println("Total income: \$$totalIncome")
}

fun costPerSeat(rowNumber: Int, rows: Int, cols: Int){
    val totalSeats = rows * cols
    val firstRows = rows / 2

    println("")
    if (totalSeats <= 60) {
        println("Ticket price: $10")
    } else if (firstRows >= rowNumber){
        println("Ticket price: $10")
    } else {
        println("Ticket price: $8")
    }
}

