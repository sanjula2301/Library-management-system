package com.example.demo_library.repository;

import com.example.demo_library.entity.LibraryMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryMemberRepository extends CrudRepository<LibraryMember, Long> {


}

