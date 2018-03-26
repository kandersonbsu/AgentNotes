public enum Lead{
		Select ("Select a Lead"),Adela("Adela"), John("John"), Stephanie("Stephanie"), Krystal("Krystal"), Zach("Zach"), Josh("Josh");
	
	private String displayName;
	Lead(String displayName){
		this.displayName = displayName;
	}
	public String toString() {
		return this.displayName;
	}
}
