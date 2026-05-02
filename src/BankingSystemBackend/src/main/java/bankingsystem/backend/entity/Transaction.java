package bankingsystem.backend.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "transactions")
public class Transaction implements Comparable<Transaction> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transferFrom;
    private String transferTo;
    private Long amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Override
    public int compareTo(Transaction transaction) {
        return this.date.compareTo(transaction.getDate());
    }
}