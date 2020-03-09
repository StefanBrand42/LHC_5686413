package infrastructure.lhc;

import java.util.UUID;

public class Block {
    private  final UUID uuid;
    private  final String structure;


    public Block(String structure) {
        this.structure = structure;
        uuid = UUID.randomUUID();
    }

    // für aus Datenbank erstellen
    public Block(UUID uuid, String structure) {
        this.uuid = uuid;
        this.structure = structure;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getStructure() {
        return structure;
    }
}
