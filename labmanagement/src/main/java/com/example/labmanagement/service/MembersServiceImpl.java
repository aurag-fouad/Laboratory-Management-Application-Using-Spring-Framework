package com.example.labmanagement.service;

import com.example.labmanagement.model.Project;
import com.example.labmanagement.model.User;
import com.example.labmanagement.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembersServiceImpl implements MembersService{

    private final MembersRepository membersRepository;

    @Autowired
    public MembersServiceImpl(MembersRepository membersRepository) {
        this.membersRepository = membersRepository;
    }


    @Override
    public List<User> getAllMembers() {
        return membersRepository.findAll();
    }

    @Override
    public void saveMember(User member) {
        this.membersRepository.save(member);
    }

    @Override
    public void deleteMember(Long id) {
        this.membersRepository.deleteById(id);
    }

    @Override
    public User getMemberById(long id) {
        Optional<User> optional = membersRepository.findById(id);
        User user = null;
        if(optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User not found for id :: " +id);
        }
        return user;
    }
}
