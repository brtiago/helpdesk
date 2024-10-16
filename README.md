# Helpdesk API
A help desk api

<details>
  <summary>UML modeling</summary>
```mermaid
classDiagram
    class Priority {
        +Integer id
        +String description
        +Integer getId()
        +String getDescription()
        +static Priority toEnum(Integer id)
    }

    class Profile {
        +Integer id
        +String description
        +Integer getId()
        +String getDescription()
        +static Profile toEnum(Integer id)
    }

    class Status {
        +Integer id
        +String description
        +Integer getId()
        +String getDescription()
        +static Status toEnum(Integer id)
    }

    class Person {
        +Integer id
        +String name
        +String cpf
        +String email
        +String password
        +Set<Integer> profiles
        +LocalDate createdAt
        +Integer getId()
        +String getName()
        +String getCpf()
        +String getEmail()
        +String getPassword()
        +Set<Profile> getProfiles()
        +LocalDate getCreatedAt()
        +void addProfiles(Profile profiles)
    }

    class User {
        +User()
        +User(Integer id, String name, String cpf, String email, String password, Set<Integer> profiles)
    }

    class Technician {
        +Technician()
        +Technician(Integer id, String name, String cpf, String email, String password, Set<Integer> profiles)
    }

    class Ticket {
        +Integer id
        +LocalDate openedAt
        +LocalDate closedAt
        +Priority priority
        +Status status
        +String title
        +String notes
        +Technician technician
        +User user
        +Integer getId()
        +LocalDate getOpenedAt()
        +LocalDate getClosedAt()
        +Priority getPriority()
        +Status getStatus()
        +String getTitle()
        +String getNotes()
        +Technician getTechnician()
        +User getUser()
    }

    Person <|-- User
    Person <|-- Technician
    Ticket o-- Priority
    Ticket o-- Status
    Ticket --> User
    Ticket --> Technician


</details>