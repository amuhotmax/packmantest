import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Wall;

public class PacManTown extends City {
	
	private int size;
	
	PacManTown(int resolution){
		super(resolution,resolution);
		this.size = resolution;
		generateWalls();
	}
	
	public void generateWalls(){
		for(int i = 0; i < 11; i++){
			new Wall(this, 0, i, Direction.NORTH);			
			new Wall(this, size-1, i, Direction.SOUTH);			
			new Wall(this, i, 0, Direction.WEST);			
			new Wall(this, i, size-1, Direction.EAST);			
		}
	}

}
