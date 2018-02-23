import java.util.ArrayList;

/**
 * @author kyanderson
 * Note Class. The note class creates objects of notes. It's most important feature is the actual 
 * string that stores the note. It also is constructed with an enumerated NoteType that stores
 * what the actual note is about for future reference. 
 *
 */
public class Note {
	
	private Date date;
	private String note;
	private NoteType noteType;
	private ArrayList<Note> noteArray;
	
	Note(String n, NoteType nt, Date d){
		this.note = n;
		this.noteType = nt;
		this.date = d;
	}
	
	public String getNote(int i) {
		return noteArray.get(i).toString();
	}
	public String getNote() {
		return this.note;
	}
	
	public void setNote(String n) {
		this.note = n;
	}
	public String toString() {
		return note;
	}
	public NoteType getNoteType() {
		return noteType;
	}
	public void setNoteType (NoteType nt) {
		this.noteType = nt;
	}
	public String getDate() {
		return date.getDate();
	}

}