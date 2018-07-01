package models.setting;


/**
 *  提供API用の共通設定
 */
public enum CheckYouStatusSetting {

    OK(20, "ok"),
    NO_RESULT(50, "no results");

    CheckYouStatusSetting(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
