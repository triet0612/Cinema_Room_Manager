fun main() {
    runCinema()
}

fun inputCinema(): MutableList<MutableList<String>> {
    while (true) {
        try {
            print("Enter the number of rows:\n>")
            val numberOfRows = readln().toInt()
            print("Enter the number of seats in each row:\n>")
            val seatsPerRow = readln().toInt()
            if (numberOfRows == 0 || seatsPerRow == 0) {
                throw Exception()
            }
            return MutableList(numberOfRows) { MutableList(seatsPerRow) { "S" } }
        } catch (e: Exception) {
            println("Wrong Input")
        }
    }
}

fun printCinemaSeats(seatList: MutableList<MutableList<String>>) {
    print("Cinema:\n  ")
    for (i in 1..seatList[0].size) {
        print("$i ")
    }
    println()
    for (i in seatList.indices) {
        print((i + 1).toString() + " ")
        println(seatList[i].joinToString(" "))
    }
}

fun buyTicket(seatList: MutableList<MutableList<String>>, stat: MutableList<Int>) {
    var pos: Pair<Int, Int>
    while (true) {
        try {
            pos = inputTicketBuy()
            if (seatList[pos.first - 1][pos.second - 1] == "B") {
                println("That ticket has already been purchased!")
            } else {
                seatList[pos.first - 1][pos.second - 1] = "B"
                stat[1]++
                break
            }
        } catch (e: Exception) {
            println("Wrong input!")
        }
    }
    when {
        seatList.size * seatList[0].size <= 60 -> {
            println("Ticket price: $10")
            stat[0] += 10
        }
        pos.first <= (seatList.size / 2) -> {
            println("Ticket price: $10")
            stat[0] += 10
        }
        else -> {
            println("Ticket price: $8")
            stat[0] += 8
        }
    }
}

fun inputTicketBuy(): Pair<Int, Int> {
    print("Enter a row number:\n>")
    val rowPos = readln().toInt()
    print("Enter a seat number in that row:\n>")
    val seatPos = readln().toInt()
    return Pair(rowPos, seatPos)
}

fun statistics(stat: MutableList<Int>, totalSeat: Int, totalIncome: Int) {
    println("Number of purchased tickets: ${stat[1]}")
    println("Percentage: ${"%.2f".format((stat[1]).toFloat() * 100 / totalSeat.toFloat())}%")
    println("Current income: \$${stat[0]}")
    println("Total income: \$${totalIncome}")
}

fun runCinema() {
    //init stat: (cur income, cur purchased)
    val stat: MutableList<Int> = mutableListOf(0, 0)
    //add Cinema
    val seatList = inputCinema()
    val totalSeat = seatList.size * seatList[0].size
    //total income
    val totalIncome = when {
        totalSeat <= 60 -> 10 * seatList.size * seatList[0].size
        else -> 10 * (totalSeat / 2) + 8 * (totalSeat / 2)
    }
    var command: Int
    while (true) {
        try {
            println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
            command = readln().toInt()
            when (command) {
                1 -> printCinemaSeats(seatList)
                2 -> buyTicket(seatList, stat)
                3 -> statistics(stat, totalSeat, totalIncome)
                0 -> break
                else -> throw Exception()
            }
        } catch (e: Exception) {
            println("Wrong input!")
        }
    }
}
