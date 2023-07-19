
public class Player {

	private int _index;
	private String _firstName; 
	private String _secondName;
	private int _apt;
	private int _set;
	private String _association;
	private String _position;
	private Double _avg;
	
	public Player(
			int index,
			String firstName, 
			String secondName,
			int apt,
			int set,
			String association,
			String position
			) {
	
		_index = index;
		_firstName = firstName;
		_secondName = secondName;
		_apt = apt;
		_set = set;
		_association = association;
		_position = position;
		_avg = ((Double.valueOf(apt)+Double.valueOf(set))/2);	
	}
	

	public int getIndex() {
		return _index;
	}
	
	public String getFirstName() {
		return _firstName;
	}

	public String getSecondName() {
		return _secondName;
	}
	
	public int getApt() {
		return _apt;
	}
	
	public int getSet() {
		return _set;
	}
	
	public String getAssociation() {
		return _association;
	}
		
	public String getPosition() {
		return _position;
	}
	
	public Double getAvg() {
		return _avg;
	}
	
	public double getValueForSorting(int element) {
		
		if (element == 0) {
			return _set;
		}
		else if (element == 1){
			return _apt;
		}
		else {
			return _avg;
		}
	}
	
	
}
