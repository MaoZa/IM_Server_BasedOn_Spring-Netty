package cn.dawnland.im.service.impl;

import cn.dawnland.im.mapper.UserMapper;
import cn.dawnland.im.model.User;
import cn.dawnland.im.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cap_Sub
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByParams(Long uid, String email, String nickname) {
        return userMapper.selectByParams(uid, email, nickname);
    }
}
