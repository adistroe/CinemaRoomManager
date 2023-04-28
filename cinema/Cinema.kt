package cinema

const val SYMBOL_CURRENCY = "$"
const val SYMBOL_PERCENTAGE = "%"
const val FULL_TICKET_PRICE = 10
const val DISCOUNTED_TICKET_PRICE = 8
const val SMALL_THEATRE_ROOM = 60

const val MSG_INPUT_ROWS = "Enter the number of rows:"
const val MSG_INPUT_SEATS = "Enter the number of seats in each row:"
const val MSG_INPUT_ROW_NO = "Enter a row number:"
const val MSG_INPUT_SEAT_NO = "Enter a seat number in that row:"
const val MSG_TICKET_PRICE = "Ticket price:"
const val MSG_SEAT_IS_RESERVED = "That ticket has already been purchased!"
const val MSG_WRONG_INPUT = "Wrong input!"
const val MSG_PURCHASED_TICKETS = "Number of purchased tickets:"
const val MSG_PERCENTAGE = "Percentage:"
const val MSG_CURRENT_INCOME = "Current income:"
const val MSG_TOTAL_INCOME = "Total income:"

const val MENU_SHOW = "1. Show the seats"
const val MENU_BUY = "2. Buy a ticket"
const val MENU_STATISTICS = "3. Statistics"
const val MENU_EXIT = "0. Exit"
const val CMD_SHOW = 1
const val CMD_BUY = 2
const val CMD_STATISTICS = 3
const val CMD_EXIT = 0

const val SEAT_AVAILABLE = 'S'
const val SEAT_RESERVED = 'B'
const val TXT_CINEMA = "Cinema:"

fun inputTheatreRows(): Int {
    println(MSG_INPUT_ROWS)
    return readln().toInt()
}

fun inputTheatreSeatsPerRow(): Int {
    println(MSG_INPUT_SEATS)
    return readln().toInt()
}

// -1 because we have top row with seat numbers and left column with row numbers
fun getMaxNumberOfRows(seatChart: MutableList<MutableList<Char>>) = seatChart.size - 1
fun getMaxSeatsPerRow(seatChart: MutableList<MutableList<Char>>) = seatChart[0].size - 1

fun getTotalNumberOfSeatsInTheatre(seatChart: MutableList<MutableList<Char>>) =
    getMaxNumberOfRows(seatChart) * getMaxSeatsPerRow(seatChart)

fun isSeatInFrontRows(row: Int, seatChart: MutableList<MutableList<Char>>) = row <= getMaxNumberOfRows(seatChart) / 2

fun isSmallTheatreRoom(seatChart: MutableList<MutableList<Char>>) =
    getTotalNumberOfSeatsInTheatre(seatChart) <= SMALL_THEATRE_ROOM

fun get2DSeatChart(rows: Int, seats: Int): MutableList<MutableList<Char>> {
    //create seat list and fill it with the symbol for available seat "S"
    val seatChart2D = MutableList(rows) {
        MutableList(seats) { SEAT_AVAILABLE }
    }

    // create list containing the seat numbers (top row)
    val seatNumbers = (1..seats).map { it.digitToChar() }.toMutableList()
    seatNumbers.add(0, ' ')

    //add seat numbers to the seat list
    seatChart2D.add(0, seatNumbers)

    // add left column with row numbers
    for (row in 1..rows) {
        seatChart2D[row].add(0, row.digitToChar())
    }
    return seatChart2D
}

fun getTicketPrice(reservedRow: Int, seatChart: MutableList<MutableList<Char>>): Int {
    val fullTicketPrice = isSmallTheatreRoom(seatChart) || isSeatInFrontRows(reservedRow, seatChart)
    // to select ticket price we check if theatre room is small OR reserved seat is in front rows
    return if (fullTicketPrice) FULL_TICKET_PRICE else DISCOUNTED_TICKET_PRICE
}

fun buyTicket(seatChart: MutableList<MutableList<Char>>) {
    var row: Int
    var seat: Int
    val rowsInTheatre = getMaxNumberOfRows(seatChart)
    val seatsPerRow = getMaxSeatsPerRow(seatChart)

    // check validity and availability of the reserved seat
    do {
        println("\n$MSG_INPUT_ROW_NO")
        row = readln().toInt()
        println(MSG_INPUT_SEAT_NO)
        seat = readln().toInt()

        var isValidReservation = true
        when {
            row !in 1..rowsInTheatre || seat !in 1..seatsPerRow -> {
                isValidReservation = false
                println("\n$MSG_WRONG_INPUT")
                continue
            }

            seatChart[row][seat] != SEAT_AVAILABLE -> {
                isValidReservation = false
                println("\n$MSG_SEAT_IS_RESERVED")
                continue
            }
        }
    } while (!isValidReservation)

// update the seat chart and print the ticket price
    seatChart[row][seat] = SEAT_RESERVED
    val ticketPrice = getTicketPrice(row, seatChart)
    println("\n$MSG_TICKET_PRICE $SYMBOL_CURRENCY${ticketPrice}")
}

fun getNumberOfPurchasedTickets(seatChart: MutableList<MutableList<Char>>): Int {
    var purchasedTickets = 0
    for (eachRow in seatChart) {
        purchasedTickets += eachRow.count { it == SEAT_RESERVED }
    }
    return purchasedTickets
}

fun getPercentageOfReservedSeats(seatChart: MutableList<MutableList<Char>>) =
    (getNumberOfPurchasedTickets(seatChart) / getTotalNumberOfSeatsInTheatre(seatChart).toDouble()) * 100

fun getTotalIncome(seatChart: MutableList<MutableList<Char>>): Int {
    return if (isSmallTheatreRoom(seatChart)) {
        getTotalNumberOfSeatsInTheatre(seatChart) * FULL_TICKET_PRICE
    } else {
        var totalIncome: Int
        val frontRows = getMaxNumberOfRows(seatChart) / 2
        val backRows = getMaxNumberOfRows(seatChart) / 2 + getMaxNumberOfRows(seatChart) % 2
        totalIncome = FULL_TICKET_PRICE * frontRows * getMaxSeatsPerRow(seatChart)
        totalIncome += DISCOUNTED_TICKET_PRICE * backRows * getMaxSeatsPerRow(seatChart)
        totalIncome // returned value
    }
}

fun getCurrentIncome(seatChart: MutableList<MutableList<Char>>): Int {
    var currentIncome = 0
    for (row in 1..seatChart.lastIndex) {
        for (seat in seatChart[row]) {
            if (seat == SEAT_RESERVED) {
                currentIncome += getTicketPrice(row, seatChart)
            }
        }
    }
    return currentIncome
}

fun showAllSeatsInTheatre(seatChart: MutableList<MutableList<Char>>) {
    println("\n$TXT_CINEMA")
    for (eachRow in seatChart) {
        println(eachRow.joinToString(" "))
    }
}

fun showStatistics(seatChart: MutableList<MutableList<Char>>) {
    println(
        "\n$MSG_PURCHASED_TICKETS ${getNumberOfPurchasedTickets(seatChart)}" +
                "\n$MSG_PERCENTAGE ${"%.2f".format(getPercentageOfReservedSeats(seatChart))}$SYMBOL_PERCENTAGE" +
                "\n$MSG_CURRENT_INCOME $SYMBOL_CURRENCY${getCurrentIncome(seatChart)}" +
                "\n$MSG_TOTAL_INCOME $SYMBOL_CURRENCY${getTotalIncome(seatChart)}"
    )
}

fun showMainMenu() {
    println("\n$MENU_SHOW\n$MENU_BUY\n$MENU_STATISTICS\n$MENU_EXIT")
}


fun main() {

    val rows = inputTheatreRows()
    val seatsPerRow = inputTheatreSeatsPerRow()
    val seatChart = get2DSeatChart(rows, seatsPerRow)

    do {
        showMainMenu()
        val input = readln().toInt()
        when (input) {
            CMD_SHOW -> showAllSeatsInTheatre(seatChart)
            CMD_BUY -> buyTicket(seatChart)
            CMD_STATISTICS -> showStatistics(seatChart)
        }
    } while (input != CMD_EXIT)
}