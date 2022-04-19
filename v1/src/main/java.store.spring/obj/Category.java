package obj;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Указывает, что данный бин (класс) является сущностью.
@Table(name = "categories")
public class Category {
    @Id
    private int id = 0;
    private String name;

}
