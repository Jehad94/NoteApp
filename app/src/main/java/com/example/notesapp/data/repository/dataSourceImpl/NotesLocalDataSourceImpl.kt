import com.example.notesapp.data.db.NoteDAO
import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.data.repository.dataSource.NotesLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesLocalDataSourceImpl @Inject constructor(
    private val noteDAO: NoteDAO
) : NotesLocalDataSource {

    override suspend fun saveNoteToDB(note: NoteEntity) {
        return noteDAO.insertNote(note)
    }

    override fun getSavedNotes(): Flow<List<NoteEntity>> {
        return noteDAO.getNotes()
    }

    override fun geteNotsByTitle(title: String): Flow<List<NoteEntity>> {
        return noteDAO.getListAllByName(title)
    }

    override suspend fun getNote(id: Int): NoteEntity {
        return noteDAO.getNoteById(id)
    }

    override suspend fun deleteNoteFromDB(note: NoteEntity) {
        return noteDAO.deleteNote(note)
    }

    override suspend fun updateNoteFromDB(note: NoteEntity) {
        return noteDAO.updateNotes(note)
    }
}