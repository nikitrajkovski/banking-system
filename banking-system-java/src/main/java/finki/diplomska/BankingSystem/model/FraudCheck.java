package finki.diplomska.BankingSystem.model;

import finki.diplomska.BankingSystem.model.enums.FraudDecision;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

import static jakarta.persistence.GenerationType.*;

@Entity
@Data
@Table(name = "fraud_checks")
public class FraudCheck {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal riskScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FraudDecision decision;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;
}
