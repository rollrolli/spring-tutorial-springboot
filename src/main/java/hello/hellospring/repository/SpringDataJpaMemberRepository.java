package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Spring Data Jpa가 자동으로 구현체를 만들어서 빈으로 등록해준다.
//                                                                   <Entity, Entity의 식별자 타입>
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}