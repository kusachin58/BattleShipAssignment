package assignment.battleShip.models.warArtillery;

import java.util.HashMap;

public class ArtilleryFactory {
   private final HashMap<String, Integer> powerHaspMap;

    public ArtilleryFactory() {
        powerHaspMap = new HashMap<>();
        powerHaspMap.put("P", 1);
        powerHaspMap.put("Q", 2);
    }
    public Artillery createWarInventory(String InventoryPower, ArtilleryType artilleryType) {
         if (artilleryType == ArtilleryType.SHIP) {
             return new Ship(this.powerHaspMap.get(InventoryPower));
         }

         //Exception Handling or null pointer Pattern
         return null;
    }

}
