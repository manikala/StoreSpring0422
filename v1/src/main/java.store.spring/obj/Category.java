package obj;

import jakarta.persistence.*;

@Entity // Указывает, что данный бин (класс) является сущностью.
@Table(name = "categories")
public class Category {
    private static final String SEQ_NAME = "category_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private int id = 0;
    private String name;

}
