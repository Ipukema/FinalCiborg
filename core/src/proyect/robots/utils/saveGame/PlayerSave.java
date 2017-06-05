package proyect.robots.utils.saveGame;

public class PlayerSave implements Comparable<PlayerSave>{
	
	public String name;
    public int points;
    public int stage;
    
	public PlayerSave(String name, int points, int stage){
        this.name = name;
        this.points = points;
        this.stage = stage;
    }

	public PlayerSave() {
		
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	@Override
	public int compareTo(PlayerSave o) {
		return o.getPoints()-this.getPoints();
	}
}