package kb04.team02.web.mvc;

import kb04.team02.web.mvc.common.entity.*;
import kb04.team02.web.mvc.exchange.entity.Bank;
import kb04.team02.web.mvc.exchange.entity.ExchangeRate;
import kb04.team02.web.mvc.exchange.repository.BankRepository;
import kb04.team02.web.mvc.exchange.repository.ExchangeRateRepository;
import kb04.team02.web.mvc.exchange.repository.OfflineReceiptRepository;
import kb04.team02.web.mvc.group.entity.*;
import kb04.team02.web.mvc.group.repository.*;
import kb04.team02.web.mvc.member.entity.Address;
import kb04.team02.web.mvc.member.entity.Member;
import kb04.team02.web.mvc.member.entity.Role;
import kb04.team02.web.mvc.member.repository.MemberRepository;
import kb04.team02.web.mvc.mypage.entity.CardIssuance;
import kb04.team02.web.mvc.mypage.entity.CardState;
import kb04.team02.web.mvc.mypage.repository.CardIssuanceRepository;
import kb04.team02.web.mvc.personal.entity.*;
import kb04.team02.web.mvc.personal.repository.*;
import kb04.team02.web.mvc.saving.entity.InstallmentSaving;
import kb04.team02.web.mvc.saving.entity.Saving;
import kb04.team02.web.mvc.saving.entity.SavingHistory;
import kb04.team02.web.mvc.saving.repository.InstallmentSavingRepository;
import kb04.team02.web.mvc.saving.repository.SavingHistoryRepository;
import kb04.team02.web.mvc.saving.repository.SavingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader {

    // Repository Dependency
    // 회원 관련 의존성
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CardIssuanceRepository cardIssuanceRepository;

    // 모임지갑 관련 의존성
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private GroupWalletRespository groupWalletRespository;
    @Autowired
    private GroupWalletTransferRepository groupWalletTransferRepository;
    @Autowired
    private GroupWalletExchangeRepository groupWalletExchangeRepository;
    @Autowired
    private GroupWalletPaymentRepository groupWalletPaymentRepository;
    @Autowired
    private GroupWalletForeignCurrencyBalanceRepository groupWalletForeignCurrencyBalanceRepository;
    @Autowired
    private DuePaymentRepository duePaymentRepository;

    // 개인지갑 관련 의존성
    @Autowired
    private PersonalWalletRepository personalWalletRepository;
    @Autowired
    private PersonalWalletTransferRepository personalWalletTransferRepository;
    @Autowired
    private PersonalWalletExchangeRepository personalWalletExchangeRepository;
    @Autowired
    private PersonalWalletPaymentRepository personalWalletPaymentRepository;
    @Autowired
    private PersonalWalletForeignCurrencyBalanceRepository personalForeignCurrencyBalanceRepository;


    // 적금 관련 의존성
    @Autowired
    private SavingRepository savingRepository;
    @Autowired
    private InstallmentSavingRepository installmentSavingRepository;
    @Autowired
    private SavingHistoryRepository savingHistoryRepository;

    // 은행 관련 의존성
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private OfflineReceiptRepository offlineReceiptRepository;
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;


    public void loadInitialData() {
        //======================== 회원 관련 Data 생성 ========================//

        /**
         * 회원 Data 생성
         * 총 회원: 6명 (김진형, 최예빈, 김철, 정지원, 김현지, 염혜정)
         * 유효회원: 2명 (김진형, 최예빈)
         * 아이디: 한글 이름 그대로 영문 타자
         *      (김진형 => rla{김}wls{진}gud{형})
         *      (최예빈 => chl{최}dp{에}qls{빈})
         * 로그인 비밀번호: qwer 통일
         * 결재 비밀번호: 1234 통일
         * 전화번호, 계좌는 임의생성
         */
        Address address = new Address("서울특별시", "강남구 선릉로 428", "06159");

        Member member1 = Member.builder()
                .id("rlawlsgud")
                .password("qwer")
                .name("김진형")
                .address(address)
                .phoneNumber("010-3525-1253")
                .email("rlawlsgud@example.com")
                .payPassword("1234")
                .bankAccount("110-232-532123")
                .build();

        Member member2 = Member.builder()
                .id("chldpqls")
                .password("qwer")
                .name("최예빈")
                .address(address)
                .phoneNumber("010-1353-9085")
                .email("chldpqls@example.com")
                .payPassword("1234")
                .bankAccount("110-232-532124")
                .build();

        Member member3 = Member.builder()
                .id("zxcv")
                .password("zxcv")
                .name("김철")
                .address(address)
                .phoneNumber("010-3333-3333")
                .email("zxcv@example.com")
                .payPassword("zxcv")
                .bankAccount("333-333-333333")
                .build();

        Member member4 = Member.builder()
                .id("wert")
                .password("wert")
                .name("김현지")
                .address(address)
                .phoneNumber("010-4444-4444")
                .email("wert@example.com")
                .payPassword("wert")
                .bankAccount("444-444-4444")
                .build();

        Member member5 = Member.builder()
                .id("sdfg")
                .password("sdfg")
                .name("염혜정")
                .address(address)
                .phoneNumber("010-5555-5555")
                .email("sdfg@example.com")
                .payPassword("sdfg")
                .bankAccount("555-555-555555")
                .build();

        Member member6 = Member.builder()
                .id("xcvb")
                .password("xcvb")
                .name("정지원")
                .address(address)
                .phoneNumber("010-6666-6666")
                .email("xcvb@example.com")
                .payPassword("xcvb")
                .bankAccount("666-666-666666")
                .build();
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);
        memberRepository.save(member6);

        /**
         * 카드 Data 생성
         * 유효카드: 2개 (김진형, 최예빈)
         * 카드번호: 유효카드 2개에 대해 랜덤으로 생성 (그 외의 데이터는 동일한 숫자 나열)
         */
        CardIssuance cardIssuance1 = CardIssuance.builder().cardNumber("4311-5214-2351-5232").cardState(CardState.OK).member(member1).walletId(member1.getMemberId()).build();
        CardIssuance cardIssuance2 = CardIssuance.builder().cardNumber("4312-5114-2151-5532").cardState(CardState.OK).member(member2).walletId(member2.getMemberId()).build();
        CardIssuance cardIssuance3 = CardIssuance.builder().cardNumber("3333-3333-3333-3333").cardState(CardState.OK).member(member2).build();
        CardIssuance cardIssuance4 = CardIssuance.builder().cardNumber("4444-4444-4444-4444").cardState(CardState.OK).member(member2).build();
        CardIssuance cardIssuance5 = CardIssuance.builder().cardNumber("5555-5555-5555-5555").cardState(CardState.OK).member(member2).build();
        CardIssuance cardIssuance6 = CardIssuance.builder().cardNumber("6666-6666-6666-6666").cardState(CardState.OK).member(member2).build();
        cardIssuanceRepository.save(cardIssuance1);
        cardIssuanceRepository.save(cardIssuance2);
        cardIssuanceRepository.save(cardIssuance3);
        cardIssuanceRepository.save(cardIssuance4);
        cardIssuanceRepository.save(cardIssuance5);
        cardIssuanceRepository.save(cardIssuance6);

        //======================== 개인지갑 Data 생성 ========================//

        /**
         * 개인지갑 Data 생성
         * 유효개인지갑: 2개 (김진형, 최예빈)
         * 초기 원화: 937,100원 동일 (그 외의 데이터는 0원)
         */
        PersonalWallet personalWallet1 = PersonalWallet.builder().balance(937100L).member(member1).build();
        PersonalWallet personalWallet2 = PersonalWallet.builder().balance(937100L).member(member2).build();
        PersonalWallet personalWallet3 = PersonalWallet.builder().balance(0L).member(member3).build();
        PersonalWallet personalWallet4 = PersonalWallet.builder().balance(0L).member(member4).build();
        PersonalWallet personalWallet5 = PersonalWallet.builder().balance(0L).member(member5).build();
        PersonalWallet personalWallet6 = PersonalWallet.builder().balance(0L).member(member6).build();
        personalWalletRepository.save(personalWallet1);
        personalWalletRepository.save(personalWallet2);
        personalWalletRepository.save(personalWallet3);
        personalWalletRepository.save(personalWallet4);
        personalWalletRepository.save(personalWallet5);
        personalWalletRepository.save(personalWallet6);



        /**
         * 개인지갑 환전내역 Data 생성 1 (김진형)
         * 환전 내역: 2개 (KRW=>CurrencyCode.USD, KRW=>JPY로 각 한번)
         * 이체 후 최종 잔액:
         *      KRW: 987,1000
         *      CurrencyCode.USD: 38
         *      CurrencyCode.JPY: 6988
         */
        PersonalWalletExchange personalWalletExchange1
                = PersonalWalletExchange.builder()
                .personalWallet(personalWallet1)
                .sellCurrencyCode(CurrencyCode.KRW)
                .sellAmount(50000L)
                .afterSellBalance(1050000L)
                .buyCurrencyCode(CurrencyCode.USD)
                .buyAmount(38L)
                .afterBuyBalance(38L)
                .exchangeRate(1300.0)
                .build();
        PersonalWalletExchange personalWalletExchange2
                = PersonalWalletExchange.builder()
                .personalWallet(personalWallet1)
                .sellCurrencyCode(CurrencyCode.KRW)
                .sellAmount(62900L)
                .afterSellBalance(987100L)
                .buyCurrencyCode(CurrencyCode.JPY)
                .buyAmount(6988L)
                .afterBuyBalance(6988L)
                .exchangeRate(9.0)
                .build();

        /**
         * 개인지갑 환전내역 Data 생성 2 (최예빈)
         * 환전 내역: 2개 (KRW=>CurrencyCode.USD, KRW=>JPY로 각 한번)
         * 이체 후 최종 잔액:
         *      KRW: 987,1000
         *      CurrencyCode.USD: 61
         *      CurrencyCode.JPY: 3655
         */
        PersonalWalletExchange personalWalletExchange3
                = PersonalWalletExchange.builder()
                .personalWallet(personalWallet2)
                .sellCurrencyCode(CurrencyCode.KRW)
                .sellAmount(80000L)
                .afterSellBalance(1020000L)
                .buyCurrencyCode(CurrencyCode.USD)
                .buyAmount(61L)
                .afterBuyBalance(61L)
                .exchangeRate(1300.0)
                .build();
        PersonalWalletExchange personalWalletExchange4
                = PersonalWalletExchange.builder()
                .personalWallet(personalWallet2)
                .sellCurrencyCode(CurrencyCode.KRW)
                .sellAmount(32900L)
                .afterSellBalance(987100L)
                .buyCurrencyCode(CurrencyCode.JPY)
                .buyAmount(3655L)
                .afterBuyBalance(3655L)
                .exchangeRate(9.0)
                .build();
        personalWalletExchangeRepository.save(personalWalletExchange1);
        personalWalletExchangeRepository.save(personalWalletExchange2);
        personalWalletExchangeRepository.save(personalWalletExchange3);
        personalWalletExchangeRepository.save(personalWalletExchange4);

        /**
         * 결제 Data 생성
         * 결제 내역: 각 1개 (음식점 결제 50,000원)
         * 결제 후 잔액: 937,100원
         */
        // 김진형 결제 내역
        PersonalWalletPayment personalWalletPayment1 =
                PersonalWalletPayment.builder()
                        .personalWallet(personalWallet1)
                        .currencyCode(CurrencyCode.KRW)
                        .paymentType(PaymentType.OK)
                        .paymentPlace("소공 순대국")
                        .paymentCategory(PaymentCategoryType.RESTAURANT)
                        .amount(50000L)
                        .afterPayBalance(937100L)
                        .build();
        // 최예빈 결제 내역
        PersonalWalletPayment personalWalletPayment2 =
                PersonalWalletPayment.builder()
                        .personalWallet(personalWallet2)
                        .currencyCode(CurrencyCode.KRW)
                        .paymentType(PaymentType.OK)
                        .paymentPlace("맛자랑")
                        .paymentCategory(PaymentCategoryType.RESTAURANT)
                        .amount(50000L)
                        .afterPayBalance(937100L)
                        .build();
        personalWalletPaymentRepository.save(personalWalletPayment1);
        personalWalletPaymentRepository.save(personalWalletPayment2);

        /**
         * 외화 잔액 Data 생성
         * 기존 잔액과 동일
         */
        PersonalWalletForeignCurrencyBalance personalWalletForeignCurrencyBalance1
                = PersonalWalletForeignCurrencyBalance.builder()
                .personalWallet(personalWallet1)
                .currencyCode(CurrencyCode.USD)
                .balance(38L)
                .build();

        PersonalWalletForeignCurrencyBalance personalWalletForeignCurrencyBalance2
                = PersonalWalletForeignCurrencyBalance.builder()
                .personalWallet(personalWallet1)
                .currencyCode(CurrencyCode.JPY)
                .balance(6988L)
                .build();

        PersonalWalletForeignCurrencyBalance personalWalletForeignCurrencyBalance3
                = PersonalWalletForeignCurrencyBalance.builder()
                .personalWallet(personalWallet2)
                .currencyCode(CurrencyCode.USD)
                .balance(61L)
                .build();

        PersonalWalletForeignCurrencyBalance personalWalletForeignCurrencyBalance4
                = PersonalWalletForeignCurrencyBalance.builder()
                .personalWallet(personalWallet2)
                .currencyCode(CurrencyCode.JPY)
                .balance(3655L)
                .build();
        personalForeignCurrencyBalanceRepository.save(personalWalletForeignCurrencyBalance1);
        personalForeignCurrencyBalanceRepository.save(personalWalletForeignCurrencyBalance2);
        personalForeignCurrencyBalanceRepository.save(personalWalletForeignCurrencyBalance3);
        personalForeignCurrencyBalanceRepository.save(personalWalletForeignCurrencyBalance4);

        //======================== 모임지갑 Data 생성 ========================//

        /**
         * 모임지갑 Data생성
         * 모임지갑: 2개 (미국, 일본 여행)
         * 모임장은 김진형, 최예빈
         *
         * 모임지갑 1 => 규칙 O
         * 모임지갑 2 => 규칙 X
         */
        GroupWallet groupWallet1 = GroupWallet.builder()
                .nickname("취업기념 미국여행")
                .dueCondition(true)
                .balance(250000L)
                .dueAccumulation(800000L)
                .dueDate(1)
                .due(200000L)
                .member(member1)
                .build();

        GroupWallet groupWallet2 = GroupWallet.builder()
                .nickname("일본 라면 투어")
                .dueCondition(false)
                .balance(500000L)
                .dueAccumulation(0L)
                .dueDate(0)
                .due(0L)
                .member(member2)
                .build();
        groupWalletRespository.save(groupWallet1);
        groupWalletRespository.save(groupWallet2);

        /**
         * 참여 Data 생성
         * 모임지갑 1(미국): 6명
         *      - 전원 참여 중
         *      - 모임장: 김진형
         *      - 공동모임장: 최예빈
         *
         * 모임지갑 2(일본): 4명
         *      - 김진형, 최예빈, 김철, 정지원
         *      - 모임장: 최예빈
         *      - 공동모임장: X
         */
        // 모임지갑 1(미국 여행)
        Participation participation1 = Participation.builder()
                .participationState(ParticipationState.PARTICIPATED)
                .role(Role.CHAIRMAN)
                .groupWallet(groupWallet1)
                .memberId(member1.getMemberId())
                .build();
        Participation participation2 = Participation.builder()
                .participationState(ParticipationState.PARTICIPATED)
                .role(Role.CO_CHAIRMAN)
                .groupWallet(groupWallet1)
                .memberId(member2.getMemberId())
                .build();
        Participation participation3 = Participation.builder()
                .participationState(ParticipationState.PARTICIPATED)
                .role(Role.GENERAL)
                .groupWallet(groupWallet1)
                .memberId(member3.getMemberId())
                .build();
        Participation participation4 = Participation.builder()
                .participationState(ParticipationState.PARTICIPATED)
                .role(Role.GENERAL)
                .groupWallet(groupWallet1)
                .memberId(member4.getMemberId())
                .build();
        Participation participation5 = Participation.builder()
                .participationState(ParticipationState.PARTICIPATED)
                .role(Role.GENERAL)
                .groupWallet(groupWallet1)
                .memberId(member5.getMemberId())
                .build();
        Participation participation6 = Participation.builder()
                .participationState(ParticipationState.PARTICIPATED)
                .role(Role.GENERAL)
                .groupWallet(groupWallet1)
                .memberId(member6.getMemberId())
                .build();

        // 모임지갑 2(일본 여행)
        Participation participation7 = Participation.builder()
                .participationState(ParticipationState.PARTICIPATED)
                .role(Role.CHAIRMAN)
                .groupWallet(groupWallet2)
                .memberId(member2.getMemberId())
                .build();
        Participation participation8 = Participation.builder()
                .participationState(ParticipationState.PARTICIPATED)
                .role(Role.GENERAL)
                .groupWallet(groupWallet2)
                .memberId(member1.getMemberId())
                .build();
        Participation participation9 = Participation.builder()
                .participationState(ParticipationState.PARTICIPATED)
                .role(Role.GENERAL)
                .groupWallet(groupWallet2)
                .memberId(member3.getMemberId())
                .build();
        Participation participation10 = Participation.builder()
                .participationState(ParticipationState.PARTICIPATED)
                .role(Role.GENERAL)
                .groupWallet(groupWallet2)
                .memberId(member4.getMemberId())
                .build();
        participationRepository.save(participation1);
        participationRepository.save(participation2);
        participationRepository.save(participation3);
        participationRepository.save(participation4);
        participationRepository.save(participation5);
        participationRepository.save(participation6);
        participationRepository.save(participation7);
        participationRepository.save(participation8);
        participationRepository.save(participation9);
        participationRepository.save(participation10);

        /**
         * 회비 납부내역 Data 추가
         * 회비가 존재하는 모임지갑 1에 대해서 생성
         * 이번 달 총 6명의 모임원 중 4명이 납부 완료, 2명은 미납
         */
        DuePayment duePayment1 = DuePayment.builder()
                .member(member1)
                .groupWallet(groupWallet1)
                .build();
        DuePayment duePayment2 = DuePayment.builder()
                .member(member2)
                .groupWallet(groupWallet1)
                .build();
        DuePayment duePayment3 = DuePayment.builder()
                .member(member3)
                .groupWallet(groupWallet1)
                .build();
        DuePayment duePayment4 = DuePayment.builder()
                .member(member4)
                .groupWallet(groupWallet1)
                .build();
        duePaymentRepository.save(duePayment1);
        duePaymentRepository.save(duePayment2);
        duePaymentRepository.save(duePayment3);
        duePaymentRepository.save(duePayment4);

        /**
         * 모임지갑 이체 내역 Data 생성
         * 4명의 회비 이체 내역
         */
        GroupWalletTransfer groupWalletTransfer1 = GroupWalletTransfer.builder()
                .groupWallet(groupWallet1)
                .transferType(TransferType.DEPOSIT)
                .fromType(TargetType.PERSONAL_WALLET)
                .toType(TargetType.GROUP_WALLET)
                .src(member1.getName())
                .dest(groupWallet1.getNickname())
                .amount(200000L)
                .afterBalance(200000L)
                .build();
        GroupWalletTransfer groupWalletTransfer2 = GroupWalletTransfer.builder()
                .groupWallet(groupWallet1)
                .transferType(TransferType.DEPOSIT)
                .fromType(TargetType.PERSONAL_WALLET)
                .toType(TargetType.GROUP_WALLET)
                .src(member2.getName())
                .dest(groupWallet1.getNickname())
                .amount(200000L)
                .afterBalance(400000L)
                .build();
        GroupWalletTransfer groupWalletTransfer3 = GroupWalletTransfer.builder()
                .groupWallet(groupWallet1)
                .transferType(TransferType.DEPOSIT)
                .fromType(TargetType.PERSONAL_WALLET)
                .toType(TargetType.GROUP_WALLET)
                .src(member3.getName())
                .dest(groupWallet1.getNickname())
                .amount(200000L)
                .afterBalance(600000L)
                .build();
        GroupWalletTransfer groupWalletTransfer4 = GroupWalletTransfer.builder()
                .groupWallet(groupWallet1)
                .transferType(TransferType.DEPOSIT)
                .fromType(TargetType.PERSONAL_WALLET)
                .toType(TargetType.GROUP_WALLET)
                .src(member4.getName())
                .dest(groupWallet1.getNickname())
                .amount(200000L)
                .afterBalance(800000L)
                .build();

        GroupWalletTransfer groupWalletTransfer6 = GroupWalletTransfer.builder()
                .groupWallet(groupWallet1)
                .transferType(TransferType.WITHDRAW)
                .fromType(TargetType.GROUP_WALLET)
                .toType(TargetType.ACCOUNT)
                .src(groupWallet1.getNickname())
                .dest("KB적금")
                .amount(100000L)
                .afterBalance(700000L)
                .build();

        GroupWalletTransfer groupWalletTransfer5 = GroupWalletTransfer.builder()
                .groupWallet(groupWallet2)
                .transferType(TransferType.DEPOSIT)
                .fromType(TargetType.PERSONAL_WALLET)
                .toType(TargetType.GROUP_WALLET)
                .src(member3.getName())
                .dest(groupWallet1.getNickname())
                .amount(1000000L)
                .afterBalance(1000000L)
                .build();

        groupWalletTransferRepository.save(groupWalletTransfer1);
        groupWalletTransferRepository.save(groupWalletTransfer2);
        groupWalletTransferRepository.save(groupWalletTransfer3);
        groupWalletTransferRepository.save(groupWalletTransfer4);
        groupWalletTransferRepository.save(groupWalletTransfer5);




        /**
         * 개인지갑 이체내역 Data 생성
         * 이체 내역: 3개 (각 채우기 2번, 꺼내기 1번)
         * 이체 후 최종 잔액: 1,100,000원
         */
        // 김진형의 개인지갑
        PersonalWalletTransfer personalWalletTransfer1
                = PersonalWalletTransfer.builder()
                .personalWallet(personalWallet1)
                .transferType(TransferType.DEPOSIT)
                .fromType(TargetType.ACCOUNT)
                .toType(TargetType.PERSONAL_WALLET)
                .src(personalWallet1.getMember().getBankAccount())
                .dest(personalWallet1.getMember().getName())
                .amount(500000L)
                .afterBalance(500000L)
                .currencyCode(CurrencyCode.KRW)
                .build();

        PersonalWalletTransfer personalWalletTransfer2
                = PersonalWalletTransfer.builder()
                .personalWallet(personalWallet1)
                .transferType(TransferType.WITHDRAW)
                .fromType(TargetType.PERSONAL_WALLET)
                .toType(TargetType.ACCOUNT)
                .src(personalWallet1.getMember().getName())
                .dest(personalWallet1.getMember().getBankAccount())
                .amount(150000L)
                .afterBalance(350000L)
                .currencyCode(CurrencyCode.KRW)
                .build();

        PersonalWalletTransfer personalWalletTransfer3
                = PersonalWalletTransfer.builder()
                .personalWallet(personalWallet1)
                .transferType(TransferType.DEPOSIT)
                .fromType(TargetType.ACCOUNT)
                .toType(TargetType.PERSONAL_WALLET)
                .src(personalWallet1.getMember().getBankAccount())
                .dest(personalWallet1.getMember().getName())
                .amount(950000L)
                .afterBalance(1300000L)
                .currencyCode(CurrencyCode.KRW)
                .build();

        PersonalWalletTransfer personalWalletTransfer7
                = PersonalWalletTransfer.builder()
                .personalWallet(personalWallet1)
                .transferType(TransferType.WITHDRAW)
                .fromType(TargetType.PERSONAL_WALLET)
                .toType(TargetType.GROUP_WALLET)
                .src(personalWallet1.getMember().getName())
                .dest(groupWallet1.getNickname())
                .amount(200000L)
                .afterBalance(1100000L)
                .currencyCode(CurrencyCode.KRW)
                .build();

        // 최예빈의 개인지갑
        PersonalWalletTransfer personalWalletTransfer4
                = PersonalWalletTransfer.builder()
                .personalWallet(personalWallet2)
                .transferType(TransferType.DEPOSIT)
                .fromType(TargetType.ACCOUNT)
                .toType(TargetType.PERSONAL_WALLET)
                .src(personalWallet2.getMember().getBankAccount())
                .dest(personalWallet2.getMember().getName())
                .amount(500000L)
                .afterBalance(500000L)
                .currencyCode(CurrencyCode.KRW)
                .build();

        PersonalWalletTransfer personalWalletTransfer5
                = PersonalWalletTransfer.builder()
                .personalWallet(personalWallet2)
                .transferType(TransferType.WITHDRAW)
                .fromType(TargetType.PERSONAL_WALLET)
                .toType(TargetType.ACCOUNT)
                .src(personalWallet2.getMember().getName())
                .dest(personalWallet2.getMember().getBankAccount())
                .amount(150000L)
                .afterBalance(350000L)
                .currencyCode(CurrencyCode.KRW)
                .build();

        PersonalWalletTransfer personalWalletTransfer6
                = PersonalWalletTransfer.builder()
                .personalWallet(personalWallet2)
                .transferType(TransferType.DEPOSIT)
                .fromType(TargetType.ACCOUNT)
                .toType(TargetType.PERSONAL_WALLET)
                .src(personalWallet2.getMember().getBankAccount())
                .dest(personalWallet2.getMember().getName())
                .amount(950000L)
                .afterBalance(1300000L)
                .currencyCode(CurrencyCode.KRW)
                .build();

        PersonalWalletTransfer personalWalletTransfer8
                = PersonalWalletTransfer.builder()
                .personalWallet(personalWallet2)
                .transferType(TransferType.WITHDRAW)
                .fromType(TargetType.PERSONAL_WALLET)
                .toType(TargetType.GROUP_WALLET)
                .src(personalWallet2.getMember().getName())
                .dest(groupWallet1.getNickname())
                .amount(200000L)
                .afterBalance(1100000L)
                .currencyCode(CurrencyCode.KRW)
                .build();
        personalWalletTransferRepository.save(personalWalletTransfer1);
        personalWalletTransferRepository.save(personalWalletTransfer2);
        personalWalletTransferRepository.save(personalWalletTransfer3);
        personalWalletTransferRepository.save(personalWalletTransfer4);
        personalWalletTransferRepository.save(personalWalletTransfer5);
        personalWalletTransferRepository.save(personalWalletTransfer6);
        personalWalletTransferRepository.save(personalWalletTransfer7);
        personalWalletTransferRepository.save(personalWalletTransfer8);

        GroupWalletExchange groupWalletExchange1 = GroupWalletExchange.builder()
                .groupWallet(groupWallet1)
                .sellCurrencyCode(CurrencyCode.KRW)
                .sellAmount(400000L)
                .afterSellBalance(400000L)
                .buyCurrencyCode(CurrencyCode.USD)
                .buyAmount(320L)
                .afterBuyBalance(320L)
                .exchangeRate(1250.0)
                .build();
        GroupWalletExchange groupWalletExchange2 = GroupWalletExchange.builder()
                .groupWallet(groupWallet2)
                .sellCurrencyCode(CurrencyCode.KRW)
                .sellAmount(500000L)
                .afterSellBalance(500000L)
                .buyCurrencyCode(CurrencyCode.JPY)
                .buyAmount(58823L)
                .afterBuyBalance(58823L)
                .exchangeRate(8.5)
                .build();
        groupWalletExchangeRepository.save(groupWalletExchange1);
        groupWalletExchangeRepository.save(groupWalletExchange2);

        GroupWalletPayment groupWalletPayment1 = GroupWalletPayment.builder()
                .groupWallet(groupWallet1)
                .currencyCode(CurrencyCode.KRW)
                .paymentType(PaymentType.OK)
                .paymentPlace("바나프레소 선릉 위워크점")
                .paymentCategory(PaymentCategoryType.RESTAURANT)
                .amount(50000L)
                .afterPayBalance(250000L)
                .build();
        groupWalletPaymentRepository.save(groupWalletPayment1);

        GroupWalletForeignCurrencyBalance groupWalletForeignCurrencyBalance1 =
                GroupWalletForeignCurrencyBalance.builder()
                        .groupWallet(groupWallet1)
                        .currencyCode(CurrencyCode.USD)
                        .balance(320L)
                        .build();

        GroupWalletForeignCurrencyBalance groupWalletForeignCurrencyBalance2 =
                GroupWalletForeignCurrencyBalance.builder()
                        .groupWallet(groupWallet1)
                        .currencyCode(CurrencyCode.JPY)
                        .balance(0L)
                        .build();

        GroupWalletForeignCurrencyBalance groupWalletForeignCurrencyBalance3 =
                GroupWalletForeignCurrencyBalance.builder()
                        .groupWallet(groupWallet2)
                        .currencyCode(CurrencyCode.USD)
                        .balance(0L)
                        .build();

        GroupWalletForeignCurrencyBalance groupWalletForeignCurrencyBalance4 =
                GroupWalletForeignCurrencyBalance.builder()
                        .groupWallet(groupWallet2)
                        .currencyCode(CurrencyCode.JPY)
                        .balance(58823L)
                        .build();
        groupWalletForeignCurrencyBalanceRepository.save(groupWalletForeignCurrencyBalance1);
        groupWalletForeignCurrencyBalanceRepository.save(groupWalletForeignCurrencyBalance2);
        groupWalletForeignCurrencyBalanceRepository.save(groupWalletForeignCurrencyBalance3);
        groupWalletForeignCurrencyBalanceRepository.save(groupWalletForeignCurrencyBalance4);


        Saving save1 = savingRepository.save(Saving.builder()
                .name("KB 두근두근 여행 적금")
                .savingComment("\n" +
                        "여행준비의 혜택과 설렘이 있는 적금으로, 노랑풍선 최대 3만원 & 4% 외화적금")
                .interestRate(3.9) // Example: Increment interest rate for each saving
                .period(6) // Example: Increment period for each saving
                .amountLimit(1_000_000L) // Example: Increment amount limit for each saving
                .build());
        Saving save2 = savingRepository.save(Saving.builder()
                .name("KB WISE 외화정기적금")
                .savingComment("내가 선택한 금리적용주기에 따라 복리로 이자를 불리는 외화적금")
                .interestRate(4.5) // Example: Increment interest rate for each saving
                .period(36) // Example: Increment period for each saving
                .amountLimit(5_000_000L) // Example: Increment amount limit for each saving
                .build());
        Saving save3 = savingRepository.save(Saving.builder()
                .name("KB 적립식 외화정기적금")
                .savingComment("외화를 매월 자동이체하거나 자유롭게 저축 가능한 스마트한 외화적금")
                .interestRate(3.75) // Example: Increment interest rate for each saving
                .period(12) // Example: Increment period for each saving
                .amountLimit(3_000_000L) // Example: Increment amount limit for each saving
                .build());
        Saving save4 = savingRepository.save(Saving.builder()
                .name("KB 특★한 적금")
                .savingComment("내가 원하는 특별한 날을 만기일로 지정하고 변경할 수 있는 단기 적금")
                .interestRate(6.0) // Example: Increment interest rate for each saving
                .period(1) // Example: Increment period for each saving
                .amountLimit(300_000L) // Example: Increment amount limit for each saving
                .build());
//        Saving save5 = savingRepository.save(Saving.builder()
//                .name("KB청년도약계좌")
//                .savingComment("힘찬 미래 높은 도약")
//                .interestRate(6.0) // Example: Increment interest rate for each saving
//                .period(3) // Example: Increment period for each saving
//                .amountLimit(700_000L) // Example: Increment amount limit for each saving
//                .build());
        Saving save6 = savingRepository.save(Saving.builder()
                .name("KB 맑은하늘적금")
                .savingComment("맑은하늘을 위한 생활 속 작은 실천에 대해 우대금리를 제공하고, 대중교통/자전거상해 관련 무료 보험서비스(최대 2억원 보장)를 제공하는 친환경 특화 상품")
                .interestRate(3.35) // Example: Increment interest rate for each saving
                .period(36) // Example: Increment period for each saving
                .amountLimit(1_000_000L) // Example: Increment amount limit for each saving
                .build());
//        Saving save7 = savingRepository.save(Saving.builder()
//                .name("온국민 건강적금-골든라이프")
//                .savingComment("시니어 고객의 건강관리와 금융 혜택을 결합한 앱테크형 상품으로, 저소득층 대상 특별 우대이율을 제공하는 적금")
//                .interestRate(10.0) // Example: Increment interest rate for each saving
//                .period(6) // Example: Increment period for each saving
//                .amountLimit(200_000L) // Example: Increment amount limit for each saving
//                .build());
        Saving save8 = savingRepository.save(Saving.builder()
                .name("KB 맑은바다적금")
                .savingComment("해양쓰레기 줄이기 활동에 동참할 경우 친환경 실천 우대이율을 제공하고,\n" +
                        "맑은바다의 소중함에 대한 공감대를 형성하는 친환경 특화 상품")
                .interestRate(3.05) // Example: Increment interest rate for each saving
                .period(12) // Example: Increment period for each saving
                .amountLimit(1_000_000L) // Example: Increment amount limit for each saving
                .build());
//        Saving save9 = savingRepository.save(Saving.builder()
//                .name("KB Young Youth 적금")
//                .savingComment("자녀가 성년이 될 때까지 장기거래가 가능하며, 어린이/청소년을 위한 무료 보험가입서비스를 제공하는 적금")
//                .interestRate(3.65) // Example: Increment interest rate for each saving
//                .period(12) // Example: Increment period for each saving
//                .amountLimit(3_000_000L) // Example: Increment amount limit for each saving
//                .build());
//        Saving save10 = savingRepository.save(Saving.builder()
//                .name("KB상호부금(자유적립식)")
//                .savingComment("목돈을 마련하는 국민은행의 대표 적립식예금")
//                .interestRate(3.55) // Example: Increment interest rate for each saving
//                .period(36) // Example: Increment period for each saving
//                .amountLimit(5_000_000L) // Example: Increment amount limit for each saving
//                .build());

        InstallmentSaving installmentSaving1 = InstallmentSaving.builder()
                .maturityDate(LocalDateTime.of(2023, 12, 7, 0, 5))
                .done(false)
                .totalAmount(600_0000L) // Example: Set a fixed total amount
                .savingDate(10) // Example: Set a fixed saving date
                .savingAmount(100_000L) // Example: Set a fixed saving amount
                .saving(save4)
                .groupWallet(groupWallet1)
                .build();
        installmentSavingRepository.save(installmentSaving1);

        SavingHistory savingHistory1 = SavingHistory.builder()
                .installmentSaving(installmentSaving1)
                .insertDate(LocalDateTime.of(2023, 9, 10, 0, 5))
                .amount(100000L)
                .accumulatedAmount(100_000L)
                .installmentSaving(installmentSaving1)
                .build();
        savingHistoryRepository.save(savingHistory1);

        Address bankAddr1 = new Address("서울특별시", "영등포로 194", "07301");
        Address bankAddr2 = new Address("경기 고양시", "중앙로 1167", "10414");
        Address bankAddr3 = new Address("서울특별시", "신림로 137", "08812");
        Address bankAddr4 = new Address("경기 수원시", "덕영대로 924", "16622");
        Address bankAddr5 = new Address("경기 시흥시", "장현능곡로 178", "14995");
        Address bankAddr6 = new Address("경기 안양시", "시민대로 196", "14072");

        Bank bank1 = bankRepository.save(Bank.builder()
                .name("KB 국민은행 영등포")
                .address(bankAddr1)
                .build());

        Bank bank2 = bankRepository.save(Bank.builder()
                .name("KB국민은행 마두역종합금융센터")
                .address(bankAddr2)
                .build());

        Bank bank3 = bankRepository.save(Bank.builder()
                .name("KB국민은행 신림남부")
                .address(bankAddr3)
                .build());

        Bank bank4 = bankRepository.save(Bank.builder()
                .name("KB국민은행 수원역")
                .address(bankAddr4)
                .build());

        Bank bank5 = bankRepository.save(Bank.builder()
                .name("KB국민은행 시흥능곡")
                .address(bankAddr5)
                .build());

        Bank bank6 = bankRepository.save(Bank.builder()
                .name("KB국민은행 평촌범계종합금융센터")
                .address(bankAddr6)
                .build());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T07:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(897.730000).telegraphicTransferBuyingRate(906.430000).telegraphicTransferSellingRate(889.030000).buyingRate(913.440000).sellingRate(882.020000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T07:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1325.500000).telegraphicTransferBuyingRate(1338.300000).telegraphicTransferSellingRate(1312.700000).buyingRate(1348.690000).sellingRate(1302.310000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T06:11", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(896.420000).telegraphicTransferBuyingRate(905.110000).telegraphicTransferSellingRate(887.730000).buyingRate(912.100000).sellingRate(880.740000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T06:11", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1323.300000).telegraphicTransferBuyingRate(1336.100000).telegraphicTransferSellingRate(1310.500000).buyingRate(1346.450000).sellingRate(1300.150000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T05:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(897.180000).telegraphicTransferBuyingRate(905.880000).telegraphicTransferSellingRate(888.480000).buyingRate(912.880000).sellingRate(881.480000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T05:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1324.600000).telegraphicTransferBuyingRate(1337.400000).telegraphicTransferSellingRate(1311.800000).buyingRate(1347.780000).sellingRate(1301.420000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T04:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(897.990000).telegraphicTransferBuyingRate(906.700000).telegraphicTransferSellingRate(889.280000).buyingRate(913.700000).sellingRate(882.280000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T04:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.600000).telegraphicTransferBuyingRate(1339.400000).telegraphicTransferSellingRate(1313.800000).buyingRate(1349.810000).sellingRate(1303.390000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T03:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(898.060000).telegraphicTransferBuyingRate(906.770000).telegraphicTransferSellingRate(889.350000).buyingRate(913.770000).sellingRate(882.350000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T03:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.700000).telegraphicTransferBuyingRate(1339.500000).telegraphicTransferSellingRate(1313.900000).buyingRate(1349.910000).sellingRate(1303.490000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T02:15", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(898.270000).telegraphicTransferBuyingRate(906.980000).telegraphicTransferSellingRate(889.560000).buyingRate(913.980000).sellingRate(882.560000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T02:15", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.100000).telegraphicTransferBuyingRate(1339.900000).telegraphicTransferSellingRate(1314.300000).buyingRate(1350.320000).sellingRate(1303.880000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T01:42", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(898.370000).telegraphicTransferBuyingRate(907.080000).telegraphicTransferSellingRate(889.660000).buyingRate(914.090000).sellingRate(882.650000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T01:42", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.700000).telegraphicTransferBuyingRate(1340.500000).telegraphicTransferSellingRate(1314.900000).buyingRate(1350.930000).sellingRate(1304.470000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T00:37", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(897.920000).telegraphicTransferBuyingRate(906.620000).telegraphicTransferSellingRate(889.220000).buyingRate(913.630000).sellingRate(882.210000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-18T00:37", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.300000).telegraphicTransferBuyingRate(1340.100000).telegraphicTransferSellingRate(1314.500000).buyingRate(1350.520000).sellingRate(1304.080000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T23:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T23:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T22:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T22:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T21:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T21:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T20:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T20:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T19:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T19:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T18:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T18:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T17:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T17:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T16:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T16:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T15:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T15:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T14:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T14:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T13:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T13:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T12:13", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T12:13", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T11:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T11:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T10:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T10:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T09:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T09:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T08:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T08:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T07:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T07:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T06:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T06:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T05:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T05:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T04:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T04:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T03:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T03:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T02:16", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T02:16", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T01:44", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T01:44", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T00:39", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-17T00:39", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T23:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T23:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T22:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T22:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T21:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T21:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T20:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T20:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T19:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T19:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T18:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T18:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T17:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T17:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T16:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T16:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T15:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T15:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T14:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T14:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T13:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T13:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T12:13", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T12:13", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T11:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T11:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T10:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T10:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T09:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T09:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T08:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T08:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T07:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T07:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T06:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T06:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T05:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T05:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T04:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T04:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T03:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T03:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T02:12", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T02:12", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T01:39", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T01:39", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T00:36", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-16T00:36", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T23:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T23:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T22:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T22:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T21:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T21:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T20:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T20:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T19:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T19:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T18:10", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.130000).telegraphicTransferBuyingRate(907.850000).telegraphicTransferSellingRate(890.410000).buyingRate(914.860000).sellingRate(883.400000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T18:10", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T17:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(898.760000).telegraphicTransferBuyingRate(907.470000).telegraphicTransferSellingRate(890.050000).buyingRate(914.480000).sellingRate(883.040000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T17:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T16:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(898.760000).telegraphicTransferBuyingRate(907.470000).telegraphicTransferSellingRate(890.050000).buyingRate(914.480000).sellingRate(883.040000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T16:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T15:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(898.760000).telegraphicTransferBuyingRate(907.470000).telegraphicTransferSellingRate(890.050000).buyingRate(914.480000).sellingRate(883.040000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T15:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T14:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(898.280000).telegraphicTransferBuyingRate(906.990000).telegraphicTransferSellingRate(889.570000).buyingRate(913.990000).sellingRate(882.570000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T14:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.400000).telegraphicTransferBuyingRate(1339.200000).telegraphicTransferSellingRate(1313.600000).buyingRate(1349.610000).sellingRate(1303.190000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T13:10", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.220000).telegraphicTransferBuyingRate(907.940000).telegraphicTransferSellingRate(890.500000).buyingRate(914.950000).sellingRate(883.490000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T13:10", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.500000).telegraphicTransferBuyingRate(1342.300000).telegraphicTransferSellingRate(1316.700000).buyingRate(1352.760000).sellingRate(1306.240000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T12:15", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.370000).telegraphicTransferBuyingRate(908.090000).telegraphicTransferSellingRate(890.650000).buyingRate(915.100000).sellingRate(883.640000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T12:15", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T11:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.340000).telegraphicTransferBuyingRate(908.060000).telegraphicTransferSellingRate(890.620000).buyingRate(915.070000).sellingRate(883.610000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T11:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1330.400000).telegraphicTransferBuyingRate(1343.300000).telegraphicTransferSellingRate(1317.500000).buyingRate(1353.680000).sellingRate(1307.120000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T10:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.610000).telegraphicTransferBuyingRate(908.330000).telegraphicTransferSellingRate(890.890000).buyingRate(915.350000).sellingRate(883.870000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T10:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.800000).telegraphicTransferBuyingRate(1342.600000).telegraphicTransferSellingRate(1317.000000).buyingRate(1353.070000).sellingRate(1306.530000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T09:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.960000).telegraphicTransferBuyingRate(908.680000).telegraphicTransferSellingRate(891.240000).buyingRate(915.700000).sellingRate(884.220000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T09:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.600000).telegraphicTransferBuyingRate(1342.400000).telegraphicTransferSellingRate(1316.800000).buyingRate(1352.860000).sellingRate(1306.340000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T08:10", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(898.540000).telegraphicTransferBuyingRate(907.250000).telegraphicTransferSellingRate(889.830000).buyingRate(914.260000).sellingRate(882.820000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T08:10", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.500000).telegraphicTransferBuyingRate(1340.300000).telegraphicTransferSellingRate(1314.700000).buyingRate(1350.730000).sellingRate(1304.270000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T07:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.700000).telegraphicTransferBuyingRate(908.420000).telegraphicTransferSellingRate(890.980000).buyingRate(915.440000).sellingRate(883.960000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T07:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.500000).telegraphicTransferBuyingRate(1341.300000).telegraphicTransferSellingRate(1315.700000).buyingRate(1351.740000).sellingRate(1305.260000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T06:11", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.730000).telegraphicTransferBuyingRate(908.450000).telegraphicTransferSellingRate(891.010000).buyingRate(915.470000).sellingRate(883.990000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T06:11", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.200000).telegraphicTransferBuyingRate(1339.000000).telegraphicTransferSellingRate(1313.400000).buyingRate(1349.400000).sellingRate(1303.000000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T05:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(898.420000).telegraphicTransferBuyingRate(907.130000).telegraphicTransferSellingRate(889.710000).buyingRate(914.140000).sellingRate(882.700000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T05:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.600000).telegraphicTransferBuyingRate(1339.400000).telegraphicTransferSellingRate(1313.800000).buyingRate(1349.810000).sellingRate(1303.390000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T04:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.050000).telegraphicTransferBuyingRate(908.780000).telegraphicTransferSellingRate(891.320000).buyingRate(915.800000).sellingRate(884.300000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T04:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.400000).telegraphicTransferBuyingRate(1340.200000).telegraphicTransferSellingRate(1314.600000).buyingRate(1350.620000).sellingRate(1304.180000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T03:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.860000).telegraphicTransferBuyingRate(908.580000).telegraphicTransferSellingRate(891.140000).buyingRate(915.600000).sellingRate(884.120000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T03:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.200000).telegraphicTransferBuyingRate(1340.000000).telegraphicTransferSellingRate(1314.400000).buyingRate(1350.420000).sellingRate(1303.980000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T02:16", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.490000).telegraphicTransferBuyingRate(909.220000).telegraphicTransferSellingRate(891.760000).buyingRate(916.240000).sellingRate(884.740000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T02:16", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.500000).telegraphicTransferBuyingRate(1340.300000).telegraphicTransferSellingRate(1314.700000).buyingRate(1350.730000).sellingRate(1304.270000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T01:42", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.480000).telegraphicTransferBuyingRate(911.230000).telegraphicTransferSellingRate(893.730000).buyingRate(918.270000).sellingRate(886.690000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T01:42", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.900000).telegraphicTransferBuyingRate(1342.800000).telegraphicTransferSellingRate(1317.000000).buyingRate(1353.170000).sellingRate(1306.630000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T00:37", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.870000).telegraphicTransferBuyingRate(910.610000).telegraphicTransferSellingRate(893.130000).buyingRate(917.650000).sellingRate(886.090000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-15T00:37", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.900000).telegraphicTransferBuyingRate(1342.800000).telegraphicTransferSellingRate(1317.000000).buyingRate(1353.170000).sellingRate(1306.630000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T23:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.590000).telegraphicTransferBuyingRate(912.350000).telegraphicTransferSellingRate(894.830000).buyingRate(919.400000).sellingRate(887.780000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T23:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T22:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.590000).telegraphicTransferBuyingRate(912.350000).telegraphicTransferSellingRate(894.830000).buyingRate(919.400000).sellingRate(887.780000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T22:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T21:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.590000).telegraphicTransferBuyingRate(912.350000).telegraphicTransferSellingRate(894.830000).buyingRate(919.400000).sellingRate(887.780000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T21:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T20:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.590000).telegraphicTransferBuyingRate(912.350000).telegraphicTransferSellingRate(894.830000).buyingRate(919.400000).sellingRate(887.780000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T20:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T19:05", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.590000).telegraphicTransferBuyingRate(912.350000).telegraphicTransferSellingRate(894.830000).buyingRate(919.400000).sellingRate(887.780000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T19:05", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T18:11", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.590000).telegraphicTransferBuyingRate(912.350000).telegraphicTransferSellingRate(894.830000).buyingRate(919.400000).sellingRate(887.780000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T18:11", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T17:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.690000).telegraphicTransferBuyingRate(911.440000).telegraphicTransferSellingRate(893.940000).buyingRate(918.480000).sellingRate(886.900000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T17:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.500000).telegraphicTransferBuyingRate(1340.300000).telegraphicTransferSellingRate(1314.700000).buyingRate(1350.730000).sellingRate(1304.270000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T16:10", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.690000).telegraphicTransferBuyingRate(911.440000).telegraphicTransferSellingRate(893.940000).buyingRate(918.480000).sellingRate(886.900000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T16:10", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.500000).telegraphicTransferBuyingRate(1340.300000).telegraphicTransferSellingRate(1314.700000).buyingRate(1350.730000).sellingRate(1304.270000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T15:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.970000).telegraphicTransferBuyingRate(911.720000).telegraphicTransferSellingRate(894.220000).buyingRate(918.770000).sellingRate(887.170000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T15:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.000000).telegraphicTransferBuyingRate(1340.800000).telegraphicTransferSellingRate(1315.200000).buyingRate(1351.240000).sellingRate(1304.760000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T14:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.480000).telegraphicTransferBuyingRate(910.220000).telegraphicTransferSellingRate(892.740000).buyingRate(917.250000).sellingRate(885.710000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T14:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.600000).telegraphicTransferBuyingRate(1341.400000).telegraphicTransferSellingRate(1315.800000).buyingRate(1351.850000).sellingRate(1305.350000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T13:11", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.080000).telegraphicTransferBuyingRate(909.820000).telegraphicTransferSellingRate(892.340000).buyingRate(916.840000).sellingRate(885.320000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T13:11", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.300000).telegraphicTransferBuyingRate(1339.100000).telegraphicTransferSellingRate(1313.500000).buyingRate(1349.510000).sellingRate(1303.090000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T12:15", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.720000).telegraphicTransferBuyingRate(908.440000).telegraphicTransferSellingRate(891.000000).buyingRate(915.460000).sellingRate(883.980000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T12:15", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.000000).telegraphicTransferBuyingRate(1338.800000).telegraphicTransferSellingRate(1313.200000).buyingRate(1349.200000).sellingRate(1302.800000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T11:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(899.760000).telegraphicTransferBuyingRate(908.480000).telegraphicTransferSellingRate(891.040000).buyingRate(915.500000).sellingRate(884.020000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T11:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1325.800000).telegraphicTransferBuyingRate(1338.600000).telegraphicTransferSellingRate(1313.000000).buyingRate(1349.000000).sellingRate(1302.600000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T10:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.240000).telegraphicTransferBuyingRate(908.970000).telegraphicTransferSellingRate(891.510000).buyingRate(915.990000).sellingRate(884.490000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T10:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.600000).telegraphicTransferBuyingRate(1339.400000).telegraphicTransferSellingRate(1313.800000).buyingRate(1349.810000).sellingRate(1303.390000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T09:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.350000).telegraphicTransferBuyingRate(909.080000).telegraphicTransferSellingRate(891.620000).buyingRate(916.100000).sellingRate(884.600000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T09:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.300000).telegraphicTransferBuyingRate(1339.100000).telegraphicTransferSellingRate(1313.500000).buyingRate(1349.510000).sellingRate(1303.090000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T08:10", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.880000).telegraphicTransferBuyingRate(909.610000).telegraphicTransferSellingRate(892.150000).buyingRate(916.640000).sellingRate(885.120000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T08:10", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.000000).telegraphicTransferBuyingRate(1338.800000).telegraphicTransferSellingRate(1313.200000).buyingRate(1349.200000).sellingRate(1302.800000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T07:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.520000).telegraphicTransferBuyingRate(910.260000).telegraphicTransferSellingRate(892.780000).buyingRate(917.290000).sellingRate(885.750000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T07:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.500000).telegraphicTransferBuyingRate(1339.300000).telegraphicTransferSellingRate(1313.700000).buyingRate(1349.710000).sellingRate(1303.290000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T06:11", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.010000).telegraphicTransferBuyingRate(909.740000).telegraphicTransferSellingRate(892.280000).buyingRate(916.770000).sellingRate(885.250000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T06:11", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1325.300000).telegraphicTransferBuyingRate(1338.100000).telegraphicTransferSellingRate(1312.500000).buyingRate(1348.490000).sellingRate(1302.110000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T05:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.140000).telegraphicTransferBuyingRate(909.880000).telegraphicTransferSellingRate(892.400000).buyingRate(916.900000).sellingRate(885.380000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T05:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1325.400000).telegraphicTransferBuyingRate(1338.200000).telegraphicTransferSellingRate(1312.600000).buyingRate(1348.590000).sellingRate(1302.210000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T04:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.670000).telegraphicTransferBuyingRate(909.400000).telegraphicTransferSellingRate(891.940000).buyingRate(916.430000).sellingRate(884.910000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T04:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1324.700000).telegraphicTransferBuyingRate(1337.500000).telegraphicTransferSellingRate(1311.900000).buyingRate(1347.880000).sellingRate(1301.520000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T03:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.660000).telegraphicTransferBuyingRate(910.400000).telegraphicTransferSellingRate(892.920000).buyingRate(917.430000).sellingRate(885.890000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T03:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.700000).telegraphicTransferBuyingRate(1339.500000).telegraphicTransferSellingRate(1313.900000).buyingRate(1349.910000).sellingRate(1303.490000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T02:14", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.130000).telegraphicTransferBuyingRate(910.880000).telegraphicTransferSellingRate(893.380000).buyingRate(917.910000).sellingRate(886.350000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T02:14", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.300000).telegraphicTransferBuyingRate(1340.100000).telegraphicTransferSellingRate(1314.500000).buyingRate(1350.520000).sellingRate(1304.080000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T01:40", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.070000).telegraphicTransferBuyingRate(910.820000).telegraphicTransferSellingRate(893.320000).buyingRate(917.850000).sellingRate(886.290000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T01:40", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.400000).telegraphicTransferBuyingRate(1340.200000).telegraphicTransferSellingRate(1314.600000).buyingRate(1350.620000).sellingRate(1304.180000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T00:37", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.940000).telegraphicTransferBuyingRate(910.680000).telegraphicTransferSellingRate(893.200000).buyingRate(917.720000).sellingRate(886.160000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-14T00:37", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.100000).telegraphicTransferBuyingRate(1340.900000).telegraphicTransferSellingRate(1315.300000).buyingRate(1351.340000).sellingRate(1304.860000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T23:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.960000).telegraphicTransferBuyingRate(910.700000).telegraphicTransferSellingRate(893.220000).buyingRate(917.740000).sellingRate(886.180000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T23:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.500000).telegraphicTransferBuyingRate(1341.300000).telegraphicTransferSellingRate(1315.700000).buyingRate(1351.740000).sellingRate(1305.260000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T22:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.960000).telegraphicTransferBuyingRate(910.700000).telegraphicTransferSellingRate(893.220000).buyingRate(917.740000).sellingRate(886.180000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T22:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.500000).telegraphicTransferBuyingRate(1341.300000).telegraphicTransferSellingRate(1315.700000).buyingRate(1351.740000).sellingRate(1305.260000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T21:05", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.960000).telegraphicTransferBuyingRate(910.700000).telegraphicTransferSellingRate(893.220000).buyingRate(917.740000).sellingRate(886.180000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T21:05", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.500000).telegraphicTransferBuyingRate(1341.300000).telegraphicTransferSellingRate(1315.700000).buyingRate(1351.740000).sellingRate(1305.260000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T20:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.960000).telegraphicTransferBuyingRate(910.700000).telegraphicTransferSellingRate(893.220000).buyingRate(917.740000).sellingRate(886.180000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T20:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.500000).telegraphicTransferBuyingRate(1341.300000).telegraphicTransferSellingRate(1315.700000).buyingRate(1351.740000).sellingRate(1305.260000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T19:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.260000).telegraphicTransferBuyingRate(908.990000).telegraphicTransferSellingRate(891.530000).buyingRate(916.010000).sellingRate(884.510000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T19:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.000000).telegraphicTransferBuyingRate(1338.800000).telegraphicTransferSellingRate(1313.200000).buyingRate(1349.200000).sellingRate(1302.800000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T18:10", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.260000).telegraphicTransferBuyingRate(908.990000).telegraphicTransferSellingRate(891.530000).buyingRate(916.010000).sellingRate(884.510000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T18:10", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1326.000000).telegraphicTransferBuyingRate(1338.800000).telegraphicTransferSellingRate(1313.200000).buyingRate(1349.200000).sellingRate(1302.800000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T17:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.520000).telegraphicTransferBuyingRate(909.250000).telegraphicTransferSellingRate(891.790000).buyingRate(916.270000).sellingRate(884.770000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T17:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.000000).telegraphicTransferBuyingRate(1340.800000).telegraphicTransferSellingRate(1315.200000).buyingRate(1351.240000).sellingRate(1304.760000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T16:10", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.520000).telegraphicTransferBuyingRate(909.250000).telegraphicTransferSellingRate(891.790000).buyingRate(916.270000).sellingRate(884.770000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T16:10", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.000000).telegraphicTransferBuyingRate(1340.800000).telegraphicTransferSellingRate(1315.200000).buyingRate(1351.240000).sellingRate(1304.760000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T15:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.280000).telegraphicTransferBuyingRate(909.010000).telegraphicTransferSellingRate(891.550000).buyingRate(916.030000).sellingRate(884.530000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T15:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.000000).telegraphicTransferBuyingRate(1340.800000).telegraphicTransferSellingRate(1315.200000).buyingRate(1351.240000).sellingRate(1304.760000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T14:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.400000).telegraphicTransferBuyingRate(909.130000).telegraphicTransferSellingRate(891.670000).buyingRate(916.150000).sellingRate(884.650000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T14:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.000000).telegraphicTransferBuyingRate(1340.800000).telegraphicTransferSellingRate(1315.200000).buyingRate(1351.240000).sellingRate(1304.760000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T13:11", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.270000).telegraphicTransferBuyingRate(910.010000).telegraphicTransferSellingRate(892.530000).buyingRate(917.040000).sellingRate(885.500000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T13:11", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.200000).telegraphicTransferBuyingRate(1342.000000).telegraphicTransferSellingRate(1316.400000).buyingRate(1352.460000).sellingRate(1305.940000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T12:15", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.550000).telegraphicTransferBuyingRate(910.290000).telegraphicTransferSellingRate(892.810000).buyingRate(917.320000).sellingRate(885.780000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T12:15", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.600000).telegraphicTransferBuyingRate(1342.400000).telegraphicTransferSellingRate(1316.800000).buyingRate(1352.860000).sellingRate(1306.340000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T11:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.080000).telegraphicTransferBuyingRate(910.830000).telegraphicTransferSellingRate(893.330000).buyingRate(917.860000).sellingRate(886.300000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T11:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.300000).telegraphicTransferBuyingRate(1342.100000).telegraphicTransferSellingRate(1316.500000).buyingRate(1352.560000).sellingRate(1306.040000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T10:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.100000).telegraphicTransferBuyingRate(910.850000).telegraphicTransferSellingRate(893.350000).buyingRate(917.880000).sellingRate(886.320000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T10:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.700000).telegraphicTransferBuyingRate(1342.500000).telegraphicTransferSellingRate(1316.900000).buyingRate(1352.960000).sellingRate(1306.440000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T09:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.640000).telegraphicTransferBuyingRate(911.390000).telegraphicTransferSellingRate(893.890000).buyingRate(918.430000).sellingRate(886.850000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T09:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.500000).telegraphicTransferBuyingRate(1342.300000).telegraphicTransferSellingRate(1316.700000).buyingRate(1352.760000).sellingRate(1306.240000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T08:11", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.080000).telegraphicTransferBuyingRate(910.830000).telegraphicTransferSellingRate(893.330000).buyingRate(917.860000).sellingRate(886.300000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T08:11", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.500000).telegraphicTransferBuyingRate(1341.300000).telegraphicTransferSellingRate(1315.700000).buyingRate(1351.740000).sellingRate(1305.260000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T07:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.160000).telegraphicTransferBuyingRate(911.920000).telegraphicTransferSellingRate(894.400000).buyingRate(918.960000).sellingRate(887.360000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T07:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1330.000000).telegraphicTransferBuyingRate(1342.900000).telegraphicTransferSellingRate(1317.100000).buyingRate(1353.270000).sellingRate(1306.730000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T06:11", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.440000).telegraphicTransferBuyingRate(911.190000).telegraphicTransferSellingRate(893.690000).buyingRate(918.230000).sellingRate(886.650000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T05:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.190000).telegraphicTransferBuyingRate(909.930000).telegraphicTransferSellingRate(892.450000).buyingRate(916.960000).sellingRate(885.420000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T05:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.900000).telegraphicTransferBuyingRate(1340.700000).telegraphicTransferSellingRate(1315.100000).buyingRate(1351.130000).sellingRate(1304.670000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T04:09", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.530000).telegraphicTransferBuyingRate(910.270000).telegraphicTransferSellingRate(892.790000).buyingRate(917.300000).sellingRate(885.760000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T04:09", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.400000).telegraphicTransferBuyingRate(1341.200000).telegraphicTransferSellingRate(1315.600000).buyingRate(1351.640000).sellingRate(1305.160000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T03:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(901.360000).telegraphicTransferBuyingRate(910.100000).telegraphicTransferSellingRate(892.620000).buyingRate(917.130000).sellingRate(885.590000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T03:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.700000).telegraphicTransferBuyingRate(1341.500000).telegraphicTransferSellingRate(1315.900000).buyingRate(1351.950000).sellingRate(1305.450000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T02:15", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.780000).telegraphicTransferBuyingRate(909.510000).telegraphicTransferSellingRate(892.050000).buyingRate(916.540000).sellingRate(885.020000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T02:15", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.300000).telegraphicTransferBuyingRate(1340.100000).telegraphicTransferSellingRate(1314.500000).buyingRate(1350.520000).sellingRate(1304.080000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T01:42", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.860000).telegraphicTransferBuyingRate(909.590000).telegraphicTransferSellingRate(892.130000).buyingRate(916.620000).sellingRate(885.100000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T01:42", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1327.600000).telegraphicTransferBuyingRate(1340.400000).telegraphicTransferSellingRate(1314.800000).buyingRate(1350.830000).sellingRate(1304.370000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T00:37", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(900.990000).telegraphicTransferBuyingRate(909.720000).telegraphicTransferSellingRate(892.260000).buyingRate(916.750000).sellingRate(885.230000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-13T00:37", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1325.900000).telegraphicTransferBuyingRate(1338.700000).telegraphicTransferSellingRate(1313.100000).buyingRate(1349.100000).sellingRate(1302.700000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T23:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.360000).telegraphicTransferBuyingRate(911.110000).telegraphicTransferSellingRate(893.610000).buyingRate(918.150000).sellingRate(886.570000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T23:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.000000).telegraphicTransferBuyingRate(1340.800000).telegraphicTransferSellingRate(1315.200000).buyingRate(1351.240000).sellingRate(1304.760000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T22:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.360000).telegraphicTransferBuyingRate(911.110000).telegraphicTransferSellingRate(893.610000).buyingRate(918.150000).sellingRate(886.570000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T22:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.000000).telegraphicTransferBuyingRate(1340.800000).telegraphicTransferSellingRate(1315.200000).buyingRate(1351.240000).sellingRate(1304.760000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T21:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.360000).telegraphicTransferBuyingRate(911.110000).telegraphicTransferSellingRate(893.610000).buyingRate(918.150000).sellingRate(886.570000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T21:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.000000).telegraphicTransferBuyingRate(1340.800000).telegraphicTransferSellingRate(1315.200000).buyingRate(1351.240000).sellingRate(1304.760000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T20:08", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.360000).telegraphicTransferBuyingRate(911.110000).telegraphicTransferSellingRate(893.610000).buyingRate(918.150000).sellingRate(886.570000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T20:08", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.000000).telegraphicTransferBuyingRate(1340.800000).telegraphicTransferSellingRate(1315.200000).buyingRate(1351.240000).sellingRate(1304.760000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T19:06", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.360000).telegraphicTransferBuyingRate(911.110000).telegraphicTransferSellingRate(893.610000).buyingRate(918.150000).sellingRate(886.570000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T19:06", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1328.000000).telegraphicTransferBuyingRate(1340.800000).telegraphicTransferSellingRate(1315.200000).buyingRate(1351.240000).sellingRate(1304.760000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T18:10", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.040000).telegraphicTransferBuyingRate(911.790000).telegraphicTransferSellingRate(894.290000).buyingRate(918.840000).sellingRate(887.240000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T18:10", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.100000).telegraphicTransferBuyingRate(1341.900000).telegraphicTransferSellingRate(1316.300000).buyingRate(1352.350000).sellingRate(1305.850000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T17:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.040000).telegraphicTransferBuyingRate(911.790000).telegraphicTransferSellingRate(894.290000).buyingRate(918.840000).sellingRate(887.240000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T17:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.100000).telegraphicTransferBuyingRate(1341.900000).telegraphicTransferSellingRate(1316.300000).buyingRate(1352.350000).sellingRate(1305.850000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T16:29", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.040000).telegraphicTransferBuyingRate(911.790000).telegraphicTransferSellingRate(894.290000).buyingRate(918.840000).sellingRate(887.240000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T16:29", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.100000).telegraphicTransferBuyingRate(1341.900000).telegraphicTransferSellingRate(1316.300000).buyingRate(1352.350000).sellingRate(1305.850000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T16:07", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.040000).telegraphicTransferBuyingRate(911.790000).telegraphicTransferSellingRate(894.290000).buyingRate(918.840000).sellingRate(887.240000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T16:07", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.100000).telegraphicTransferBuyingRate(1341.900000).telegraphicTransferSellingRate(1316.300000).buyingRate(1352.350000).sellingRate(1305.850000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T15:49", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.040000).telegraphicTransferBuyingRate(911.790000).telegraphicTransferSellingRate(894.290000).buyingRate(918.840000).sellingRate(887.240000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T15:49", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.100000).telegraphicTransferBuyingRate(1341.900000).telegraphicTransferSellingRate(1316.300000).buyingRate(1352.350000).sellingRate(1305.850000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T15:37", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.040000).telegraphicTransferBuyingRate(911.790000).telegraphicTransferSellingRate(894.290000).buyingRate(918.840000).sellingRate(887.240000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T15:37", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.100000).telegraphicTransferBuyingRate(1341.900000).telegraphicTransferSellingRate(1316.300000).buyingRate(1352.350000).sellingRate(1305.850000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T15:24", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.040000).telegraphicTransferBuyingRate(911.790000).telegraphicTransferSellingRate(894.290000).buyingRate(918.840000).sellingRate(887.240000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T15:24", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.100000).telegraphicTransferBuyingRate(1341.900000).telegraphicTransferSellingRate(1316.300000).buyingRate(1352.350000).sellingRate(1305.850000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T15:10", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.040000).telegraphicTransferBuyingRate(911.790000).telegraphicTransferSellingRate(894.290000).buyingRate(918.840000).sellingRate(887.240000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T15:10", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.100000).telegraphicTransferBuyingRate(1341.900000).telegraphicTransferSellingRate(1316.300000).buyingRate(1352.350000).sellingRate(1305.850000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T14:51", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.920000).telegraphicTransferBuyingRate(911.670000).telegraphicTransferSellingRate(894.170000).buyingRate(918.720000).sellingRate(887.120000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T14:51", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.100000).telegraphicTransferBuyingRate(1341.900000).telegraphicTransferSellingRate(1316.300000).buyingRate(1352.350000).sellingRate(1305.850000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T14:39", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(903.170000).telegraphicTransferBuyingRate(911.930000).telegraphicTransferSellingRate(894.410000).buyingRate(918.970000).sellingRate(887.370000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T14:39", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.100000).telegraphicTransferBuyingRate(1341.900000).telegraphicTransferSellingRate(1316.300000).buyingRate(1352.350000).sellingRate(1305.850000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T14:27", formatter)).currencyCode(CurrencyCode.JPY).tradingBaseRate(902.790000).telegraphicTransferBuyingRate(911.540000).telegraphicTransferSellingRate(894.040000).buyingRate(918.580000).sellingRate(887.000000).build());
        exchangeRateRepository.save(ExchangeRate.builder().insertDate(LocalDateTime.parse("2023-09-12T14:27", formatter)).currencyCode(CurrencyCode.USD).tradingBaseRate(1329.000000).telegraphicTransferBuyingRate(1341.800000).telegraphicTransferSellingRate(1316.200000).buyingRate(1352.250000).sellingRate(1305.750000).build());
    }
}