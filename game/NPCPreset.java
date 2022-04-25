package game;

import java.util.ArrayList;

/**
 * @author @Corbanator
 * This class is a preset for an NPC that is a template for how the actual NPC will be generated.
 */
public class NPCPreset {

    public NpcAllience npcAllience;
    public String[] descriptions;
    //All of the integer arrays will be a length of 2, where the lower index is the min and the upper one is the max.
    public int[] ACRange;
    public int[] strRange;
    public int[] dexRange;
    public int[] conRange;
    public int[] intRange;
    public int[] wisRange;
    public int[] chaRange;
    public int[] noiseRange;
    public int[] shieldRange;
    public String[] name;

    //NPCPresets are given an inventory so that they will spawn with weapons.
    public ArrayList<InteractablePreset> inventory;

    public NPCPreset(NpcAllience npcAllience, String[] descriptions, int[] ACRange,
            int[] strRange, int[] dexRange, int[] conRange, int[] intRange, int[] wisRange, int[] chaRange,
            int[] noiseRange, int[] shieldRange, String[] name, ArrayList<InteractablePreset> inventory) {
        this.npcAllience = npcAllience;
        this.descriptions = descriptions;
        this.ACRange = ACRange;
        this.strRange = strRange;
        this.dexRange = dexRange;
        this.conRange = conRange;
        this.intRange = intRange;
        this.wisRange = wisRange;
        this.chaRange = chaRange;
        this.noiseRange = noiseRange;
        this.shieldRange = shieldRange;
        this.name = name;
        this.inventory = inventory;
    }

}
