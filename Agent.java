import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author kyanderson
 * Agent class. The agent class creates new agent objects and stores some information about them. The agent object
 * is created with a string for the first and last name, the name of their lead through an enumerated variable, and 
 * an ArrayList that stores note objects. 
 *
 */
public class Agent implements Serializable, Comparator<Agent>, Comparable<Agent>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Lead lead;
	private NoteType noteType;
	private String firstName, lastName, noteString;
	private ArrayList<Note> noteArray;
	private Note note;
	
	
	Agent(String fn, String ln, Lead l){
		this.firstName = fn;
		this.lastName = ln;
		this.lead = l;
		this.noteArray = new ArrayList<Note>();
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
	@Override
	public int compare(Agent arg0, Agent arg1) {
		return 0;
	}
	@Override
	public int compareTo(Agent arg0) {
		return (this.firstName).compareTo(arg0.firstName);
	}


}
