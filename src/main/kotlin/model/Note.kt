package model

import Screen.UIColor
import java.util.Scanner

class Note(override var name: String, val content: String) : Entity {

    companion object {
        fun getNewEntity(): Note? {
            val scanner = Scanner(System.`in`)
            println("Введите название заметки:")
            val name = scanner.nextLine()
            println("Введите текст заметки:")
            val content = scanner.nextLine()
            if (name == "" || content == "") {
                println(UIColor.RED + "Имя и текст заметки не могут быть пустыми!" + UIColor.RESET)
                return null
            }
            return Note(name, content)
        }
    }

    fun printEntity() {
        println(UIColor.YELLOW + "Название: " + UIColor.RESET + name)
        println(UIColor.YELLOW + "Текст: " + UIColor.RESET + content)
    }
}
