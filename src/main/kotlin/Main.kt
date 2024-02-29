fun main() {
    val singleNum = singleNumber(arrayOf(1, 2, 1, 2, 1, 2, 5, 7, 7))
    println(singleNum)

    val minSplit = minSplit(59)
    println(minSplit)

    val longestPrefix = longestPrefix(arrayOf("extract", "exhale", "excavate"))
    println(longestPrefix)

    val binarySum = binarySumString("1010", "1011")
    println(binarySum)

    val countVariants = countVariants(4)
    println(countVariants)

    val dataStructure = ConstantTimeDeletion<Int>()
    dataStructure.apply {
        add(5)
        add(6)
        add(10)
    }
    dataStructure.delete(10)

}

fun singleNumber(array: Array<Int>): Int {
    val n: Int = array.size

    for (i in 0..<n) {
        var isRepeated = false
        val currentNum = array[i]

        for (j in 0..<n) {
            if (i != j && currentNum == array[j]) {
                isRepeated = true
                break
            }
        }
        if (!isRepeated) {
            return currentNum
        }
    }

    return -1
}

fun minSplit(amount: Int): Int {
    var minSplitNum = 0
    var remainingChange = amount
    val coins = listOf(50, 20, 10, 5, 1)

    for (coin in coins) {
        if (remainingChange >= coin) {
            minSplitNum += remainingChange / coin
            remainingChange %= coin
        }
    }

    return minSplitNum
}

fun longestPrefix(array: Array<String>): String {
    var prefix = array[0]

    for (i in 1..<array.size) {
        val word = array[i]
        var j = 0
        while (j < prefix.length && j < word.length && prefix[j] == word[j]) {
            j++
        }
        prefix = prefix.substring(0, j)
        if (prefix.isEmpty()) break
    }

    return prefix
}

fun binarySumString(first: String, second: String): String {
    val firstNumber = Integer.parseInt(first, 2)
    val secondNumber = Integer.parseInt(second, 2)

    val thirdNumber = firstNumber + secondNumber

    return Integer.toBinaryString(thirdNumber)
}

fun countVariants(stairsCount: Int): Int {
    if (stairsCount <= 0) {
        return 0
    } else if (stairsCount == 1) {
        return 1
    }

    var prev = 1
    var current = 2

    for (i in 3..stairsCount) {
        val next = prev + current
        prev = current
        current = next
    }

    return current
}

class ConstantTimeDeletion<T> {
    private val itemMap = HashMap<T, Int>()
    private val itemList = ArrayList<T>()

    fun add(item: T) {
        if (!itemMap.containsKey(item)) {
            itemMap[item] = itemList.size
            itemList.add(item)
        }
    }

    fun delete(item: T) {
        if (itemMap.containsKey(item)) {
            val indexToRemove = itemMap[item]!!
            val lastItem = itemList[itemList.size - 1]

            itemList[indexToRemove] = lastItem
            itemMap[lastItem] = indexToRemove

            itemList.removeAt(itemList.size - 1)
            itemMap.remove(item)
        }
    }

    fun contains(item: T): Boolean {
        return itemMap.containsKey(item)
    }

    fun size(): Int {
        return itemList.size
    }

    fun getItemAtIndex(index: Int): T {
        return itemList[index]
    }
}