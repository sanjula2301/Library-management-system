package com.example.demo_library.controller;


import com.example.demo_library.entity.LibraryMember;
import com.example.demo_library.service.LibraryMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class LibraryMemberController {
    private final LibraryMemberService memberService;

    @Autowired
    public LibraryMemberController(LibraryMemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/postMember")
    public void createMember(@RequestBody LibraryMember member) {
        memberService.createMember(member);
    }

    @GetMapping("/getMember")
    public List<LibraryMember> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PutMapping("/updateMember")
    public void updateMember(@RequestBody LibraryMember updatedMember) {
        memberService.updateMember(updatedMember);
    }


    @DeleteMapping("/deleteMember/{memberId}")
    public void deleteBook(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }



}


