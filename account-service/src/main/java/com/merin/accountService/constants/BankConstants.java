package com.merin.accountService.constants;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;


public class BankConstants {

    public final static String ACCOUNT_BANK_NAME = "MERIN Bank";

    public final static double SAVINGS_ACCOUNT_INTEREST_RATE = 4.0;

    public final static double CURRENT_ACCOUNT_INTEREST_RATE = 0.0;

    public final static double FIXED_DEPOSIT_INTEREST_RATE = 6.0;

    public final static double OVER_DRAFT_LIMIT = 25000;

    public final static String ACCOUNT_BRANCH_NAME = "BAKER STREET";

    public final static String ACCOUNT_BRANCH_CODE = "7447";

    public final static String IFSC_CODE = "MERIN0007447";

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime accountCreationDate;

    public static final String ACCOUNT_STATUS_NEW = "NEW";

    public static final String ACCOUNT_STATUS_ACTIVE = "ACTIVE";

    public static final String ACCOUNT_STATUS_INACTIVE = "INACTIVE";

    public static final String ACCOUNT_STATUS_DORMANT = "DORMANT";

    public static final String SAVINGS_ACCOUNT = "SAVINGS-ACCOUNT";

    public static final String CURRENT_ACCOUNT = "CURRENT-ACCOUNT";

    public static final String FIXED_DEPOSIT = "FIXED-DEPOSIT";


}
