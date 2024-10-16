package com.tiago.Helpdesk.enums;

public enum Profile {

    ADMIN(0, "ROLE_ADMIN"),
    CLIENT(1, "ROLE_CLIENT"),
    TECHNICIAN(2, "ROLE_TECHNICIAN");
    private Integer id;
    private String description;


    Profile(Integer id, String description) {
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
    public static Profile toEnum(Integer id) {
        if(id == null) {
            if(id == null) {
                return null;
            }

            for (Profile p : Profile.values()) {
                if (id.equals(p.getId())){
                    return p;
                }
            }
        }
        throw new IllegalArgumentException("Invalid profile");
    }
}
