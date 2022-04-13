package game;

public class NPCPreset {
    public NpcAllience npcAllience;
    public String[] descriptions;
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

    public NPCPreset(NpcAllience npcAllience, String[] descriptions, int[] ACRange,
            int[] strRange, int[] dexRange, int[] conRange, int[] intRange, int[] wisRange, int[] chaRange,
            int[] noiseRange, int[] shieldRange, String[] name) {
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
    }

}
