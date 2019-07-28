package bitter.mybatis;

public enum EmpStatus {
    LOGIN(100,"登陆"),LOGOUT(200,""),REMOVED(300,"");

    private int code;
    private String msg;

    private EmpStatus(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public static EmpStatus getEmpStatusByCode(int code){
        switch (code) {
            case 100 :return LOGIN;
            case 200 :return LOGOUT;
            case 300 :return REMOVED;
            default: return LOGOUT;
        }



    }
}
