import Screen.Menu
import model.Archive
import model.EntityType
import model.Note

fun main(args: Array<String>) {
    val archiveList = ArrayList<Archive>()
    val archiveMenu = Menu(EntityType.ARCHIVE, "АРХИВЫ", archiveList)
    archiveMenu.start(
        onAdd = { Archive.getNewEntity() },
        onOpen = { archive ->
            val noteMenu = Menu(EntityType.NOTE,"АРХИВ: ${archive.name}", archive.notes)
            noteMenu.start(
                onAdd = { Note.getNewEntity() },
                onOpen = { note -> note.printEntity() }
            )
        }
    )
}