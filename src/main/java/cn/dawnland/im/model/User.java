package cn.dawnland.im.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Cap_Sub
 */
@Data
public class User {

    private Long uid;

    private String email;

    private String nickname;

    private Integer score;

    private Integer avatar;

    private String password;

    private String ip;

    private Integer permission;

    private Date lastSignAt;

    private Date registerAt;

    private Integer verified;

    private String verificationToken;

}
