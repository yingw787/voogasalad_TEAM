***API between engine and front end, front end is an observable and engine is the observer



readAllUnits(); //read the map which holds all the things

update(List<object> e) // update the movement of enemy and the shoot on the map, return true (win) or false (lose)

  - note: each object e is a special event in the game. for example: buy/sell a tower, collision between a bullet and a zombie, .....
  
  - we will write the constructer of these event object, e.g, 
  
  ```Java
    Collide c = new Collide(int bulletID, int towerID);
    SellTower st = new SellTower(int towerID);
  ```
  
  - and for using update() API:
  
  ```Java
    List<object> list = new ArrayList<object>;
    list.add(c);
    list.add(st);
    update(list);
  ```
  
