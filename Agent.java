import java.util.ArrayList;

/**
 * @author kyanderson
 * Agent class. The agent class creates new agent objects and stores some information about them. The agent object
 * is created with a string for the first and last name, the name of their lead through an enumerated variable, and 
 * an ArrayList that stores note objects. 
 *
 */
public class Agent {
	private Lead lead;
	private NoteType noteType;
	private String firstName, lastName, noteString;
	private ArrayList<Note> noteArray;
	private Note note;
	
	
	Agent(String fn, String ln, Lead l){
		this.firstName = fn;
		this.lastName = ln;
		this.lead = l;
		noteArray = new ArrayList<Note>();
	}
	Agent(String fn, String ln){
		this.firstName = fn;
		this.lastName = ln;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	public Lead getLead() {
		return this.lead;
	}
	public ArrayList<Note> getNoteArray(){
		return this.noteArray;
	}
	public void setFirstName(String fn) {
		this.firstName = fn;
	}
	public void setLastName(String ln) {
		this.lastName = ln;
	}
	public void setLead(Lead l) {
		this.lead = l;
	}
	public void addNote(Note note) {
		this.noteArray.add(note);
	}
	public String toString() {
		return firstName + " " + lastName + " " + getLead();
	}
	public String getNote(int i) {
		return this.noteArray.get(i).toString();
	}
	public Note getNoteObject(String d) {
		Note returnNote = null;
		for(Note note : this.noteArray) {
			if(note.getDate().equals(d)) {
				returnNote = note;
			}
		}
		return returnNote;
	}
	public String getNote(String d) {
		String returnNote = null;
		for(Note note : this.noteArray) {
			if(note.getDate().equals(d)) {
				returnNote = note.getNote();
			}
		}
		return returnNote;
	}


}
