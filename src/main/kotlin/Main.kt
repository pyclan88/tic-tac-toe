package tictactoe

fun main() {
    var table = mutableListOf<MutableList<Char>>()
    for (i in 0..2) {
        val list = mutableListOf<Char>()
        for (j in 0..2) {
            list.add(' ')
        }
        table.add(list)
    }
    printTable(table)
    var status = "Game not finished"
    while (status == "Game not finished") {
        table = getMove(table)
        status = getResult(table)
    }
    println(status)
}

fun getMove(table: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
    var y = -1
    var x = -1
    while (true) {
        val moveList = readln().split(' ')
        try {
            y = moveList.first().toInt()
            x = moveList.last().toInt()
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
        }
        if ((y == 0 || y in 4..9) || (x == 0 || x in 4..9)) {
            println("Coordinates should be from 1 to 3!")
        } else if (y in 1..3 && x in 1..3) {
            if (table[y - 1][x - 1] == 'X' || table[y - 1][x - 1] == 'O') {
                println("This cell is occupied! Choose another one!")
            } else break
        }
    }
    var count = 0
    for (line in table) {
        for (j in line) {
            if (j == ' ') count++
        }
    }
    if (count % 2 == 0) {
        table[y - 1][x - 1] = 'O'
    } else table[y - 1][x - 1] = 'X'
    printTable(table)
    return table
}

fun printTable(table: MutableList<MutableList<Char>>) {
    println("---------")
    for (i in table) {
        println(i.joinToString(" ", "| ", " |"))
    }
    println("---------")
}

fun getResult(table: MutableList<MutableList<Char>>): String {
    var tableString = ""
    for (line in table) {
        for (i in line) {
            tableString += i
        }
    }
    val winTable = mutableListOf<MutableList<Int>>()
    winTable.add(mutableListOf(0, 1, 2))
    winTable.add(mutableListOf(3, 4, 5))
    winTable.add(mutableListOf(6, 7, 8))
    winTable.add(mutableListOf(0, 3, 6))
    winTable.add(mutableListOf(1, 4, 7))
    winTable.add(mutableListOf(2, 5, 8))
    winTable.add(mutableListOf(0, 4, 8))
    winTable.add(mutableListOf(2, 4, 6))
    var xWon = false
    var oWon = false
    for (winLine in winTable) {
        var xCount = 0
        var oCount = 0
        for (i in winLine) {
            if (tableString[i] == 'X') xCount++
            if (tableString[i] == 'O') oCount++
        }
        if (xCount == 3) xWon = true
        if (oCount == 3) oWon = true
    }
    var x = 0
    var o = 0
    var nothing = 0
    for (i in tableString) {
        if (i == 'X') {
            x++
        } else if (i == 'O') {
            o++
        } else nothing++
    }
    if (o == x || o == x + 1 || x == o + 1) {
        if (xWon && !oWon) {
            return "X wins"
        } else if (!xWon && oWon) {
            return "O wins"
        } else if (!xWon && !oWon && nothing != 0) {
            return "Game not finished"
        } else if ((xWon && oWon || !xWon && !oWon) && nothing == 0) {
            return "Draw"
        } else return "Impossible"
    } else return "Impossible"
}