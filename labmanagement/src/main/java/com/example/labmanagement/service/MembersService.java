package com.example.labmanagement.service;

import com.example.labmanagement.model.Project;
import com.example.labmanagement.model.User;

import java.util.List;

public interface MembersService {
    List<User> getAllMembers();
    void saveMember(User member);
    void deleteMember(Long id);
    User getMemberById(long id);
}
