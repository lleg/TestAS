package ru.digitalspirit.asaka.config.enums;

public enum ParameterCode {

    BPM_SERVER_URL("ServerUrl"),
    BPM_SERVER_LOGIN("ServerLogin"),
    BPM_SERVER_PASSWORD("ServerPassword"),
    JASPER_PF_ADAPTER_URL("JasperPfUrl"),
    FRONT_BASE_URL("FrontBaseUrl"),
    FRONT_LE_URL("FrontLeUrl"),
    FRONT_FL_URL("FrontFlUrl"),
    CHANGE_STATUS_URL("ChangeStatusServiceUrl"),
    UPDATE_TASK_LIST_URL("UpdateTaskListServiceUrl"),
    OA_GET_CUSTOMER_ACCOUNTS_URL("AccountListServiceUrl"),
    OA_RESERVE_ACCOUNT_URL("ReserveAccountServiceUrl"),
    OA_GET_RESERVED_ACCOUNTS_URL("GetReservedAccountsServiceUrl"),
    OA_GET_CUSTOMER_DBO_PROFILE_URL("GetDBOProfileServiceUrl"),
    OA_GET_CUSTOMER_CARD_LIST_URL("GetRCustomerCardListServiceUrl"),
    OA_GET_PRODUCT_COMMISSION_URL("GetProductCommissionsServiceUrl"),
    OA_GET_CARD_SMS_PROFILE_URL("GetCardSMSProfileServiceUrl"),
    OA_CARD_COUNTER_URL("CardCounterServiceUrl"),
    OA_CRM_GET_LEGAL_URL("GetCRMLegalServiceUrl"),
    OA_CRM_GET_INDIVIDUAL_URL("GetCRMIndividualServiceUrl"),
    OA_CRM_GET_INDIVIDUAL_MATRIX_URL("GetCRMIndividualMatrixServiceUrl"),
    PBU_RESERVE_ACCOUNT_URL("PbuReserveAccountServiceUrl"),
    PBU_CRM_GET_INDIVIDUAL_URL("GetPBUCRMIndividualServiceUrl"),
    PBUM_CRM_GET_INDIVIDUAL_URL("GetPbumCrmIndividualServiceURL"),
    PBUM_RESERVE_ACCOUNT_URL("PBUMReserveAccountServiceUrl"),
    PBU_CALCULATOR_URL("PbuCalculatorUrl"),
    SMS_NOTIFICATION_URL("SendSMSNotificationUrl"),
    SMS_NOTIFICATION_FLAG("SendSMSNotificationFlag"),
    OD_STX4_URL("ODSTX4ServiceUrl"),
    DKB_RESERVE_ACCOUNT_URL("DKBReserveAccountServiceUrl"),
    OD_GET_CUSTOMER_ACCOUNTS_URL("ODGetCustomerAccountListServiceUrl"),
    SET_APPLICATION_STATUS_URl("SetStatusAppServiceUrl"),
    OD_CREATE_PAYMENT_ORDER_URL("OpenDepositCreatePaymentOrderServiceUrl"),
    PBUM_PROPORTAL_URL("PBUMProPortalServiceUrl"),
    GET_PAYROLL_PBU_FROM_PROPORTAL_FLAG("GetPayrollPBUFromProPortal"),
    PBUC_RESERVE_ACCOUNT_URL("PBUCReserveAccountServiceUrl"),
    OD_CREATEPAYMENTORDER_DATE("ODCreatePaymentOrderDate"),
    OD_CRM_GET_INDIVIDUAL_URL("GetOdCrmIndividualServiceURL"),
    LOAN_FRONT_URL("LoanFrontUrl");

    private final String code;

    ParameterCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ParameterCode getByCode(String code) {
        for (ParameterCode appType : ParameterCode.values()) {
            if (appType.getCode().equalsIgnoreCase(code)) {
                return appType;
            }
        }
        return null;
    }
}
