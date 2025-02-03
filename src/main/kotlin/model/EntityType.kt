package model

interface Localizable {
    fun getLocalizedName(): String
    fun getLocalizedHeader(): String
}

enum class EntityType : Localizable {
    NOTE {
        override fun getLocalizedName(): String = "Заметка"
        override fun getLocalizedHeader(): String = "Список заметок:"
    },
    ARCHIVE {
        override fun getLocalizedName(): String = "Архив"
        override fun getLocalizedHeader(): String = "Список архивов:"
    }
}