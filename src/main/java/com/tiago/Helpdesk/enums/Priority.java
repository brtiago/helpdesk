package com.tiago.Helpdesk.enums;

public enum Priority {

    LOW(0, "LOW"),
    MEDIUM(1, "MEDIUM"),
    HIGH(2, "HIGH");
    private Integer id;
    private String description;


    Priority(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @SuppressWarnings("unused")
    public static Priority toEnum(Integer id) {
        if(id == null) {
            if(id == null) {
                return null;
            }

            for (Priority p : Priority.values()) {
                if (id.equals(p.getId())){
                    return p;
                }
            }
        }
        throw new IllegalArgumentException("Invalid priority");
    }
}
