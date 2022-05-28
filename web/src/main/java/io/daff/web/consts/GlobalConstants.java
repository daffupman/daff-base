package io.daff.web.consts;

/**
 * 系统级别的常量
 *
 * @author daffupman
 * @since 2020/7/12
 */
public class GlobalConstants {

    public static final String AUTHORIZATION = "authorization";

    public static final String TOKEN = "token";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";

    public static final String CURRENT_LOGIN_USER = "currentLoginUser";
    public static final String LOGIN_USER_ID = "loginUserId";
    public static final String LOGIN_NICK_NAME = "loginNickName";
    public static final String LOGIN_REAL_NAME = "loginRealName";
    public static final String LOGIN_BELONG_HIERARCHY_ID = "loginHierarchyId";
    public static final String LOGIN_BELONG_ORG_HIERARCHY_ID = "loginOrgHierarchyId";
    public static final Object LOGIN_ERROR_MSG = "loginErrorMsg";

    public static final String LOGIN_ERROR_RESPONSE = "login_error_response";

    public static final String ACCESS_TOKEN_PREFIX = "access_token:";
    public static final String REFRESH_TOKEN_PREFIX = "refresh_token:";

    /**
     * 授权码有效时间（min）
     */
    public static final Long CODE_EXPIRE_TIME = 10L;

    /**
     * 存储在redis中的授权码前缀
     */
    public static final String AUTHORIZE_CODE_PREFIX = "authorize_code:";
    public static final String AUTHORIZE_CODE_SCOPE_MAP_PREFIX = "authorize_code_scope_map:";

    public static final String DEFAULT_PASSWORD = "123456";

    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";

}
