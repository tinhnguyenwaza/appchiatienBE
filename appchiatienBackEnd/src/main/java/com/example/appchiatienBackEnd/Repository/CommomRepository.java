package com.example.appchiatienBackEnd.Repository;

import com.example.appchiatienBackEnd.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommomRepository extends JpaRepository<Member, Long> {
}


