package cn.dawnland.im.service;

import cn.dawnland.im.model.User;

/**
 * @author Cap_Sub
 */
public interface UserService {

    User selectByParams(Long uid,
                        String email,
                        String nickname);

}
