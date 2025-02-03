package Screen

import model.Entity
import model.EntityType
import java.util.Scanner

class Menu<T> (
    val entityType: EntityType,
    val menuCaption: String,
    var items: ArrayList<T>
) where T: Entity {

    private val addCode: Int = 0
    private var exitCode: Int = 1

    private val scanner = Scanner(System.`in`)

    private val frameLen = 70

    init {
        exitCode += items.count()
    }

    fun start(
        onAdd: () -> T?,
        onOpen: (T) -> Unit
    ) {
        while (true) {
            printMenu()

            val choice = scanner.nextLine()
            when(val choiceInt = choice.trim().toIntOrNull()) {

                null -> {
                    printErrorMessage("Неверный ввод! Допустимо вводить только числа!", true)
                }

                addCode -> {
                    printCloseFrame()
                    printFrameHeader("-> Добавить \"${entityType.getLocalizedName()}\"")
                    val entityToAdd = onAdd.invoke()
                    if (entityToAdd != null) {
                        items.add(entityToAdd)
                        exitCode++
                        println()
                        println("Добавлено: ${entityType.getLocalizedName()} \"${entityToAdd.name}\"!")
                    } else {
                        printErrorMessage("Запись не добавлена!")
                    }
                    printCloseFrame()
                }

                exitCode -> {
                    if (entityType == EntityType.ARCHIVE) {
                        println("Ждём Вас еще)")
                    }
                    break
                }

                in 1..items.count() -> {
                    printCloseFrame()
                    val entityToOpen = items[choiceInt-1]
                    if (entityType == EntityType.NOTE) {
                        printFrameHeader("-> ${entityType.getLocalizedName()} \"${entityToOpen.name}\"")
                    }
                    onOpen.invoke(entityToOpen)
                    printCloseFrame()
                }

                else -> {
                    printErrorMessage("В меню нет такого пункта!", true)
                }

            }
        }
    }

    private fun printErrorMessage(message: String, printCloseFrame: Boolean = false) {
        println(UIColor.RED + message + UIColor.RESET)
        if (printCloseFrame)
            printCloseFrame()
    }

    private fun printFrameHeader(frameName: String) {
        println(UIColor.GREEN + "=".repeat(frameLen) + UIColor.RESET)
        println(UIColor.YELLOW + frameName + UIColor.RESET)
        println(UIColor.GREEN + "-".repeat(frameLen) + UIColor.RESET)
    }

    private fun printCloseFrame() {
        println(UIColor.GREEN + "=".repeat(frameLen) + UIColor.RESET)
        println()
    }

    private fun printMenu() {
        println(UIColor.GREEN + "=".repeat(frameLen) + UIColor.RESET)
        println(UIColor.YELLOW + menuCaption + UIColor.RESET)
        println(UIColor.GREEN + "-".repeat(frameLen) + UIColor.RESET)
        println(UIColor.YELLOW + entityType.getLocalizedHeader())
        println("$addCode. Создать новый объект \"${entityType.getLocalizedName()}\"")

        items.forEachIndexed{ index, value -> println("${index + 1}. ${value.name}")}

        println("$exitCode. Выход" + UIColor.RESET)
        println(UIColor.GREEN + "-".repeat(frameLen) + UIColor.RESET)
        println()
        print("Ваш выбор:")
    }
}