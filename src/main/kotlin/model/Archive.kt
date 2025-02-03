package model

import Screen.UIColor
import java.util.Scanner

class Archive(override var name: String) : Entity {

    val notes = ArrayList<Note>()

    companion object {
        fun getNewEntity(): Archive? {
            val scanner = Scanner(System.`in`)
            println("Введите название архива:")
            val name = scanner.nextLine()
            if (name == "") {
                println(UIColor.RED + "Имя архива не может быть пустым!" + UIColor.RESET)
                return null
            }
            return Archive(name)
        }
    }
}
