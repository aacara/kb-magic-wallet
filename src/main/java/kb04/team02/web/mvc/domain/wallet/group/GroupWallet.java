package kb04.team02.web.mvc.domain.wallet.group;

import kb04.team02.web.mvc.domain.bank.OfflineReceipt;
import kb04.team02.web.mvc.domain.common.BooleanToYNConverter;
import kb04.team02.web.mvc.domain.member.Member;
import kb04.team02.web.mvc.domain.saving.InstallmentSaving;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GroupWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_seq")
    @SequenceGenerator(name = "wallet_seq", allocationSize = 1, sequenceName = "wallet_seq")
    @Column(name = "group_wallet_id")
    private Long groupWalletId;

    @Column(nullable = false, length = 20)
    private String nickname;

    @CreationTimestamp
    private LocalDateTime insertDate;

    @ColumnDefault("0")
    private Long balance;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean dueCondition;

    private Long dueAccumulation;
    private int dueDate;
    private Long due;

    //== 연관관계 설정 START==//
    @ManyToOne
    @JoinColumn(name = "member_id")

    private Member member;

    @OneToMany(mappedBy = "groupWallet")
    private List<DuePayment> duePayments = new ArrayList<>();

    @OneToMany(mappedBy = "groupWallet")
    private List<Participation> participations = new ArrayList<>();

    @OneToMany(mappedBy = "groupWallet")
    private List<GroupWalletTransfer> groupWalletTransfers = new ArrayList<>();

    @OneToMany(mappedBy = "groupWallet")
    private List<GroupWalletExchange> groupWalletExchanges = new ArrayList<>();

    @OneToMany(mappedBy = "groupWallet")
    private List<GroupWalletPayment> groupWalletPayments = new ArrayList<>();

    @OneToMany(mappedBy = "groupWallet")
    private List<GroupWalletForeignCurrencyBalance> foreignCurrencyBalances = new ArrayList<>();

    @OneToMany(mappedBy = "groupWallet")
    private List<InstallmentSaving> installmentSavings = new ArrayList<>();

    @OneToMany(mappedBy = "groupWallet")
    private List<OfflineReceipt> offlineReceipts = new ArrayList<>();
    //== 연관관계 설정 END==//
}
