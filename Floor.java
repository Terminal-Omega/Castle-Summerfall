
import java.util.ArrayList;
public class Floor {
    private ArrayList<ArrayList<Room>> rooms; // Stores Coordinates literally based on the location within the vector.
    private ArrayList<Enemy> enemies;

    public Floor(ArrayList<ArrayList<Room>> rooms) {
        this.rooms = rooms;
        this.enemies = new ArrayList<>();
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    // public Floor(){
    //     ArrayList<ArrayList<Room>> rooms;
    //     ArrayList<Room> roomList;
    //     for (int i = 0; i < 5; i++){
    //         for (int j = 0; j < 5; j++){
    //             Room room = new Room();
    //             roomList.add(room);
    //         }
    //         rooms.add(roomList);
    //     }
    //     Floor(rooms);
    // }

    public Room getRoom(int xCoord, int yCoord){
        return rooms.get(xCoord).get(yCoord);
    }

    //use this to get a door going in a specific direction
    public Door getDoor(int xCoord, int yCoord, Direction direction){
        switch (direction) {
            case NORTH:
                if(!(yCoord >= rooms.get(xCoord).size())){
                    return rooms.get(xCoord).get(yCoord+1).getDoor(Direction.SOUTH);
                }
            case SOUTH:
                return rooms.get(xCoord).get(yCoord).getDoor(Direction.SOUTH);
            case EAST:
                return rooms.get(xCoord).get(yCoord).getDoor(Direction.EAST);
            case WEST:
            if(xCoord!=0){
                return rooms.get(xCoord-1).get(yCoord).getDoor(Direction.EAST);
            }else{
                return null;
            }
            default:
                return null;
        }
    }

    public String getDescription(int xCoord, int yCoord){
        StringBuilder builder = new StringBuilder(rooms.get(xCoord).get(yCoord).getDescription());
        ArrayList<Enemy> localEnemies = new ArrayList<>();
        for(Enemy enemy : enemies){
            if(enemy.getXCoord() == xCoord && enemy.getYCoord() == yCoord){
                localEnemies.add(enemy);
            }
        }
        switch (localEnemies.size()) {
            case 0:
                break;
            case 1:
            builder.append("\n");
            builder.append("There is a " + localEnemies.get(0).getName() + " in the room.");
            break;
            case 2:
            builder.append("\n");
            builder.append(String.format("In the room is a %s and a %s", localEnemies.get(0).getName(),localEnemies.get(0).getName()));
            break;
            default:
            builder.append("\n");
            for(int i = 0;i<localEnemies.size();i++){
                if(i != localEnemies.size() - 1){
                    builder.append(" a "+localEnemies.get(i).getName() + ",");
                } else{
                    builder.append(" and a" + localEnemies.get(i).getName() + ".");
                }
            }
           
        }
        int i = 0;
        for(Direction direction : Direction.values()){
            if(getDoor(xCoord, yCoord, direction) != null){
                i++;
            }
        }

        int j = 0;
        for(Direction direction : Direction.values()){
            if(getDoor(xCoord, yCoord, direction)!=null){
                j++;
                String openStatus = "";
                String directionString = direction.toString().substring(0,1).toUpperCase() + direction.toString().substring(1);
                if(getDoor(xCoord, yCoord, direction).passable()){
                    openStatus = "an open";
                } else{
                    openStatus = "a closed";
                }
                if(j == 1){
                    builder.append("\nThere is " + openStatus + " door to the " + directionString);
                } else if(j<Direction.values().length - 1){
                    builder.append(", " + openStatus + " door to the " + directionString);
                } else{
                    builder.append(", and " + openStatus + " door to the " + directionString);
                }
            }
        }
        builder.append(".");
        return builder.toString();
    }
}
