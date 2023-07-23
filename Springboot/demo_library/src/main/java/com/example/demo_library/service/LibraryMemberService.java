package com.example.demo_library.service;

import com.example.demo_library.entity.LibraryMember;
import com.example.demo_library.repository.LibraryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryMemberService {
    private final LibraryMemberRepository memberRepository;

    @Autowired
    public LibraryMemberService(LibraryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(LibraryMember member) {
        memberRepository.save(member);
    }

    public List<LibraryMember> getAllMembers() {
        return (List<LibraryMember>) memberRepository.findAll();
    }

    public void updateMember(LibraryMember member){
        memberRepository.save(member);
    }

    public void deleteMember(Long memberID){
        memberRepository.deleteById(memberID);
    }
}
