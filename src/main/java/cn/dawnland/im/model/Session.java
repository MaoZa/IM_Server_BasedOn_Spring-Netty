package cn.dawnland.im.model;

import lombok.Data;

@Data
public class Session {

    // 用户唯一性标识
    private String userId;
    
    private String nickname;

    public Session(String userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }

    public Session() {
    }
}
