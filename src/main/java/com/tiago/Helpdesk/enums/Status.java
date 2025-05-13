package com.tiago.Helpdesk.enums;

public enum Status {

    OPEN(0, "OPEN"),
    IN_PROGRESS(1, "IN PROGRESS"),
    CLOSED(2, "CLOSED");
    private Integer id;
    private String description;


    Status(Integer id, String description) {
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
    public static Status toEnum(Integer id) {
        if(id == null) {
            return null;
        }

        for (Status p : Status.values()) {
            if (id.equals(p.getId())){
                return p;
            }
        }

        throw new IllegalArgumentException("Invalid status");
    }
}
